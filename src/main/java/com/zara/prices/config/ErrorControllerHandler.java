package com.zara.prices.config;

import com.zara.prices.infrastructure.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ErrorControllerHandler {
    @Autowired
    private ErrorAttributes errorAttributes;
    @Autowired
    private Environment environment;


    @RequestMapping(value = "/error")
    public ErrorResponseDto error(WebRequest webRequest, HttpServletResponse response) {
        return new ErrorResponseDto(
                response.getStatus(),
                getErrorAttributes(webRequest),
                String.join("", environment.getActiveProfiles()));
    }

    @ExceptionHandler({ Exception.class})
    public ResponseEntity<ErrorResponseDto> runTimeExceptionHandle(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        getErrorAttributes(webRequest),
                        String.join("", environment.getActiveProfiles())),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        return errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE));
    }
}
