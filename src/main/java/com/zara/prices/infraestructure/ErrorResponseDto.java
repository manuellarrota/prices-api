package com.zara.prices.infraestructure;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class ErrorResponseDto {
    private final Integer status;
    private final String timeStamp;
    private String message;
    private String error;
    private String trace;

    public ErrorResponseDto(Integer status, Map<String, Object> errorAttributes, String profile) {

        this.status = status;
        this.message = errorAttributes.get("message") + "";
        this.timeStamp = (String) (errorAttributes.get("timeStamp") == null ? new Date().toString() : errorAttributes.get("timeStamp"));
        if (profile.compareTo("production") != 0) {
            this.trace = (String) (errorAttributes.get("trace") == null ? "Resource Not Available" : errorAttributes.get("trace"));
            if (this.message.isEmpty()) {
                this.message = trace.substring(0, trace.indexOf("at "));
            }
        } else {
            this.trace = "Error 500";
            this.message = "Sorry will be fixed soon!!.";
        }
        this.error = (String) (errorAttributes.get("exception") == null ? "Internal error Server" : errorAttributes.get("exception"));
        log.error(message);
        log.error("Trace: " + errorAttributes.get("trace"));
    }

    @Override
    public String toString() {
        return "ErrorResponse{" + "status=" + status + ", error='" + error + '\'' + ", message='" + message
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
