package com.lineate.traineeship.junit.clients;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

public class NasaClientTest extends WireMockTestBase {

    @Test
    public void testGetApod_curDate_success() throws Exception {
        try (final CloseableWireMockServer server = startWireMock("cur_date_success")) {
            final NasaApiClient nasaApiClient = getNasaApiClinet(server);
            final LocalDate localDate = LocalDate.now(getClock());

            final NasaApod nasaApod = nasaApiClient.getApod(localDate, true);

            // If service may return null in any case, we have to check it
            // because NotNull assert is better the NPE in test results
            Assert.assertNotNull("We expected got APOD but something gone wrong", nasaApod);
            Assert.assertEquals(nasaApod.getTitle(), "A Complete Solar Cycle from SOHO");
            Assert.assertEquals(nasaApod.getExplanation(), "Every eleven years... activity of the Sun.");
            Assert.assertEquals(nasaApod.getHdUrl(), "https://apod.nasa.gov/apod/image/0712/solarcycle_soho_big.jpg");
            Assert.assertEquals(nasaApod.getUrl(), "https://apod.nasa.gov/apod/image/0712/solarcycle_soho.jpg");
            Assert.assertEquals(nasaApod.getMediaType(), "image");
            Assert.assertEquals(nasaApod.getServiceVersion(), "v1");
            Assert.assertEquals(nasaApod.getDate(), localDate);
        }
    }

    private NasaApiClient getNasaApiClinet(CloseableWireMockServer server) {
        return new NasaApiClient("testApiKey", "http://localhost:" + server.port(), new RestTemplate());
    }

}
