package com.weatherprediction.service;

import com.weatherprediction.exception.ValidationException;
import com.weatherprediction.service.impl.WeatherServiceImpl;
import com.weatherprediction.util.WeatherUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceImplTest {

    @Spy
    @InjectMocks
    private static WeatherServiceImpl weatherServiceImpl;

    @Spy
    WeatherUtil weatherForecast = new WeatherUtil();

    @BeforeClass
    public static void setUp() {
        weatherServiceImpl = new WeatherServiceImpl();
    }

    @Test
    public void testCityIsNotProvided() {
        String city = "";
        ValidationException exception = assertThrows(ValidationException.class,
                () -> weatherServiceImpl.getWeather(city));

        String expectedMessage = "Value of city should not be blank";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }
}
