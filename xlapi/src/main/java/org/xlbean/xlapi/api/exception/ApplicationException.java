package org.xlbean.xlapi.api.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class ApplicationException extends RuntimeException {
    private String field;
    private String detail;

    public ApplicationException(String message, String field, String detail) {
        super(message);
        this.field = field;
        this.detail = detail;
    }
}
