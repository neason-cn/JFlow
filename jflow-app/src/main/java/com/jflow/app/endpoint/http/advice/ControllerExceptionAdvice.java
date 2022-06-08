package com.jflow.app.endpoint.http.advice;

import com.jflow.api.client.response.Json;
import com.jflow.common.exception.FlowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author neason
 * @since 0.0.1
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Json<Void> flowExceptionHandler(Exception e) {
        if (e instanceof FlowException) {
            FlowException fe = (FlowException) e;
            log.error("Known Exception: ", e);
            return Json.error(fe.getCode(), fe.getMessage());
        }
        log.error("Unknown Exception: ", e);
        return Json.error("SYSTEM_ERROR", e.getMessage());
    }

}
