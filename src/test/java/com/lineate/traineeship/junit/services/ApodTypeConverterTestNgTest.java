package com.lineate.traineeship.junit.services;

import com.lineate.traineeship.junit.clients.NasaApod;
import com.lineate.traineeship.junit.dto.ApodType;
import com.lineate.traineeship.junit.entities.ApodEntity;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.lineate.traineeship.junit.dto.ApodType.*;

/**
 * Example of JUnit parameterized test.
 */
@RunWith(Parameterized.class)
public class ApodTypeConverterTestNgTest {
    @DataProvider(name = "apodTypes")
    public Object[][] createData1() {
        return new Object[][] {
                { "Sun", Sun },
                { "Earth", Earth },
                { "Moon", Moon },
                { "spacecraft", spacecraft },
                { "galaxy", galaxy },
                { "nebula", nebula },
                { "star", star },
                { "planet", planet },
                { "oops", undefined },
                { "Star(t) with key word.", star },
                { "Key word planet in the middle of sentence.", planet },
                { "Key word at the end of galaxy.", galaxy },
                { "Sun has higher priority than Earth.", Sun },
                { "Case insensitive sPaCeCrAfT.", spacecraft }
        };
    }

    @Test(dataProvider = "apodTypes")
    public void testApodTypeConverter(String explanation, ApodType type) {
        final NasaApod nasaApod = new NasaApod();
        nasaApod.setExplanation(explanation);

        final ApodEntity apodEntity = ApodConverter.nasaApodToEntity(nasaApod, false);

        Assert.assertEquals(type.name(), apodEntity.getType());
    }
}
