package com.jflow.api.client.response;

import lombok.Data;

import java.util.UUID;

/**
 * Http rest json response data wrapper.
 *
 * @author neason
 * @since 0.0.1
 */
@Data
public class Json<T> {

    public static final String SUCCESS = "SUCCESS";
    private static final String DEFAULT_ERROR_CODE = "SYSTEM_ERROR";
    private static final String DEFAULT_ERROR_MESSAGE = "There is no detail message of this error.";

    /**
     * The unique id for this request-response.
     */
    private String requestId = UUID.randomUUID().toString().replace("-", "");

    /**
     * The code of any error, 'SUCCESS' would be return if no error.
     */
    private String errorCode;

    /**
     * The description of the error.
     */
    private String errorMessage;

    /**
     * The response data.
     */
    private T data;

    public static <T> Json<T> success(T data) {
        Json<T> json = new Json<>();
        json.setErrorCode(SUCCESS);
        json.setData(data);
        return json;
    }

    public static <T> Json<T> error() {
        return Json.error(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE);
    }

    public static <T> Json<T> error(String message) {
        return Json.error(DEFAULT_ERROR_CODE, message);
    }

    public static <T> Json<T> error(String code, String message) {
        Json<T> json = new Json<>();
        json.setErrorCode(code);
        json.setErrorMessage(message);
        return json;
    }

}
