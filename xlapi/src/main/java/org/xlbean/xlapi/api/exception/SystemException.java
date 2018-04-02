package org.xlbean.xlapi.api.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemException extends RuntimeException {
    public SystemException(String message) {
        super(message);
    }
}
