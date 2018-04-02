package org.xlbean.xlapi.api.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class BaseExceptionHandler {

    public enum ErrorCause {
        UNKNOWN, FORMAT_EXCEPTION, DECIMAL_MIN, DECIMAL_MAX, STRING_PATTERN, STRING_SIZE,
    };

    private static final Map<String, ErrorCause> ERROR_MAP = new HashMap<>();
    static {
        ERROR_MAP.put("DecimalMin", ErrorCause.DECIMAL_MIN);
        ERROR_MAP.put("DecimalMax", ErrorCause.DECIMAL_MAX);
        ERROR_MAP.put("Pattern", ErrorCause.STRING_PATTERN);
        ERROR_MAP.put("Size", ErrorCause.STRING_SIZE);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            ApplicationException.class,
            MethodArgumentTypeMismatchException.class,
            IllegalArgumentException.class,
            MethodArgumentNotValidException.class, })
    @ResponseBody
    public Map<String, Object> handleValidationError(Exception e) {
        ErrorMessageBuilder builder = new ErrorMessageBuilder();
        if (e instanceof ApplicationException) {
            ApplicationException appExp = (ApplicationException) e;
            builder.message(e.getMessage());
            builder.error(appExp.getField(), ErrorCause.FORMAT_EXCEPTION, appExp.getDetail());
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            builder.message("Invalid Request");
            MethodArgumentTypeMismatchException exp = (MethodArgumentTypeMismatchException) e;
            if (exp.getCause() instanceof NumberFormatException) {
                builder.error(exp.getName(), ErrorCause.FORMAT_EXCEPTION, exp.getMessage());
            } else {
                builder.error(exp.getName(), ErrorCause.UNKNOWN, exp.getMessage());
            }
        } else if (e instanceof MethodArgumentNotValidException) {
            builder.message("Invalid Request");
            MethodArgumentNotValidException exp = (MethodArgumentNotValidException) e;
            exp.getBindingResult().getAllErrors().forEach(elem -> {
                if (elem instanceof FieldError) {
                    FieldError fieldError = (FieldError) elem;
                    builder.error(
                        fieldError.getField(),
                        ERROR_MAP.get(elem.getCode()),
                        elem.getDefaultMessage());
                } else {
                    builder.error(
                        elem.getObjectName(),
                        ERROR_MAP.get(elem.getCode()),
                        elem.getDefaultMessage());
                }
            });
        } else {
            e.printStackTrace();
        }
        return builder.build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public Map<String, Object> handleError(Exception e) {
        e.printStackTrace();
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "Server Error");
        return errorMap;
    }

}
