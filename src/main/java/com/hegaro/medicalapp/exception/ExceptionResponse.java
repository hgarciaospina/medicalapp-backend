package com.hegaro.medicalapp.exception;

import java.util.Date;
import java.util.List;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private List<String> details;
    private String detail;

    public ExceptionResponse(Date timestamp, String message, List<String> details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public ExceptionResponse(Date timestamp, String message, String detail) {
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}