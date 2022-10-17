package com.zara.prices.infraestructure;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class ErrorResponseDto {
    private final Integer status;
    private final String timeStamp;
    private String message;
    private String trace;

    public ErrorResponseDto(Integer status, Map<String, Object> errorAttributes, String profile) {
        this.status = status;
        message = errorAttributes.get("message") + "";
        timeStamp = (String) (errorAttributes.get("timeStamp") == null ? new Date().toString() : errorAttributes.get("timeStamp"));
        if (profile.compareTo("production") == 0) {
            message = "Sorry will be fixed soon!!.";
        }
        trace = (String) (errorAttributes.get("trace") == null ? "Resource Not Available" : errorAttributes.get("trace"));
        if (message.isEmpty()) {
            message = trace.substring(0, trace.indexOf("at "));
        }
        log.error("Trace: " + errorAttributes.get("trace"));
    }

    @Override
    public String toString() {
        return "ErrorResponse{" + "status=" + status + ", message='" + message
                + '\'' + ", timeStamp='" + timeStamp + '\'' + ", trace='" + trace + '\'' + '}';
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}