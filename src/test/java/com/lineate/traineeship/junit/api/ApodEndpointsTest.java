package com.lineate.traineeship.junit.api;

import com.lineate.traineeship.junit.dto.ApodDto;
import com.lineate.traineeship.junit.dto.ResponseDto;
import com.lineate.traineeship.junit.dto.ResponseListDto;
import com.lineate.traineeship.junit.dto.ResponseStatus;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Examples of Spring MVC tests.
 */
public class ApodEndpointsTest extends RestControllerTest {
    private static final String APOD_API_URI = "/api/apod/";

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testGetDailyApod_apodNotExists_returnEmptyContent() throws Exception {
        final String uri = APOD_API_URI + "/";

        // Call REST API
        final MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.get(uri))
                .andReturn();

        // Check HTTP status
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        // Check HTTP content is not blank
        final String contentString = mvcResult.getResponse().getContentAsString();
        Assert.assertFalse(StringUtils.isBlank(contentString));

        // Check response has status NOT_FOUND and empty content
        final ResponseDto response = mapFromJson(contentString, ResponseDto.class);
        Assert.assertEquals(ResponseStatus.NOT_FOUND, response.getStatus());
        Assert.assertNull(response.getContent());
    }

    @Test
    public void testGetDailyApod_apodExists_returnApod() throws Exception {
        final String uri = APOD_API_URI + "/";

        // Prepare data
        createApod();

        // Call REST API
        final MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.get(uri))
                .andReturn();

        // Check HTTP status
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        // Check HTTP content is not blank
        final String contentString = mvcResult.getResponse().getContentAsString();
        Assert.assertFalse(StringUtils.isBlank(contentString));

        // Check response has status SUCCESS and empty content
        final ResponseDto response = mapFromJson(contentString, ResponseDto.class);
        Assert.assertEquals(ResponseStatus.SUCCESS, response.getStatus());
        Assert.assertNotNull(response.getContent());

        // Check the apod is today apod
        final ApodDto apodDto = response.getContent();
        Assert.assertEquals(LocalDate.now(clock), apodDto.getDate());
    }

    @Test
    public void testGetApodHistory_apodNotExists_returnEmptyContent() throws Exception {
        final LocalDate from = LocalDate.now(clock).minusDays(5);
        final LocalDate to = LocalDate.now(clock).minusDays(1);
        final String uri = APOD_API_URI + "/history?from=" + from + "&to=" + to;

        // Call REST API
        final MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.get(uri))
                .andReturn();

        // Check HTTP status
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        // Check HTTP content is not blank
        final String contentString = mvcResult.getResponse().getContentAsString();
        Assert.assertFalse(StringUtils.isBlank(contentString));

        // Check response has status NOT_FOUND and empty content
        final ResponseListDto response = mapFromJson(contentString, ResponseListDto.class);
        Assert.assertEquals(ResponseStatus.NOT_FOUND, response.getStatus());
        Assert.assertTrue(response.getContent().isEmpty());
    }

    @Test
    public void testGetApodHistory_apodExists_returnApod() throws Exception {
        final LocalDate from = LocalDate.now(clock).minusDays(5);
        final LocalDate to = LocalDate.now(clock).minusDays(1);
        final String uri = APOD_API_URI + "/history?from=" + from + "&to=" + to;

        // Prepare data
        createApod(LocalDate.now(clock).minusDays(3));

        // Call REST API
        final MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.get(uri))
                .andReturn();

        // Check HTTP status
        final int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);

        // Check HTTP content is not blank
        final String contentString = mvcResult.getResponse().getContentAsString();
        Assert.assertFalse(StringUtils.isBlank(contentString));

        // Check response has status SUCCESS and empty content
        final ResponseListDto response = mapFromJson(contentString, ResponseListDto.class);
        Assert.assertEquals(ResponseStatus.SUCCESS, response.getStatus());
        Assert.assertNotNull(response.getContent());

        // Check the apod history is not empty
        final List<ApodDto> apodList = response.getContent();
        Assert.assertFalse(apodList.isEmpty());

        // Check that all apod's are in date interval
        for (final ApodDto apodDto: apodList) {
            Assert.assertTrue(apodDto.getDate().isAfter(from.minusDays(1)));
            Assert.assertTrue(apodDto.getDate().isBefore(to.plusDays(1)));
        }

        // Let's check again with  AssertJ
        Assertions.assertThat(response)
                .extracting(ResponseListDto::getStatus)
                .isEqualTo(ResponseStatus.SUCCESS);
        Assertions.assertThat(response)
                .extracting(ResponseListDto::getContent)
                .isNotNull();
        Assertions.assertThat(apodList)
                .isNotEmpty()
                .extracting(ApodDto::getDate)
                .filteredOn(date -> date.isBefore(from))
                .filteredOn(date -> date.isAfter(to))
                .isEmpty();
    }
}
