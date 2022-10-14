package com.weatherprediction;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherPridictionApplicationTests {

    @Autowired
    private MockMvc mvc;

    /** The web application context. */
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before("mvc")
    public void setup() throws Exception {
        this.mvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getWeatherByCityTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v1/weather/prediction?city=london").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
    }

    /**
     * @throws Exception the exception
     */
    @Test
    public void getWeatherByCityNoContentTest() throws Exception {
        String city = " ";
        mvc.perform(MockMvcRequestBuilders.get("/v1/weather/prediction").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
    }


}
