package com.weatherprediction.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class WeatherDataException.
 *
 * @author Rantidev Singh
 * @version 1.0
 * @since 2021-04-18
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BadWeatherException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new bad weather data exception.
     *
     * @param message the message
     */
    public BadWeatherException(String message) {
        super(message);
    }

    /**
     * Instantiates a new bad weather data exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public BadWeatherException(String message, Throwable cause) {
        super(message, cause);
    }
}
