package org.xlbean.xlapi.api.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xlbean.xlapi.api.exception.BaseExceptionHandler.ErrorCause;

public class ErrorMessageBuilder {

    private String message;
    private List<Map<String, Object>> errors = new ArrayList<>();

    public ErrorMessageBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ErrorMessageBuilder error(String field, ErrorCause cause, String detail) {
        Map<String, Object> error = new HashMap<>();
        error.put("field", field);
        error.put("cause", cause);
        error.put("detail", detail);
        errors.add(error);
        return this;
    }

    public Map<String, Object> build() {
        Map<String, Object> error = new HashMap<>();
        error.put("message", message);
        error.put("errors", errors);
        return error;
    }
}
