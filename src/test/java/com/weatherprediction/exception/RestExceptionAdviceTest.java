package com.weatherprediction.exception;

import com.weatherprediction.controller.WeatherController;
import com.weatherprediction.dto.response.WeatherPredictionData;
import com.weatherprediction.service.impl.WeatherServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(WeatherController.class)
@RunWith(MockitoJUnitRunner.class)
public class RestExceptionAdviceTest {

    @Spy
    @InjectMocks
    private static WeatherController weatherController;
    private static MockMvc mockMvc;
    @Mock
    WeatherServiceImpl weatherService;

    @BeforeClass
    public static void setup() {
        weatherController = new WeatherController();
    }

    @Test
    public void testOkResponseWhenValidDataReceived() throws ApiException {
        String city = "Pune";
        List<WeatherPredictionData> fakeResults = new ArrayList<>();
        WeatherPredictionData wp = new WeatherPredictionData();
        wp.setRain(true);
        fakeResults.add(wp);
        Assertions.assertEquals(HttpStatus.OK, weatherController.getWeatherByCity(city).getStatusCode());
    }
}
