package com.sphinx.project.ultis.exception;
import lombok.Data;
import java.util.List;

@Data
public class CommonDomainException extends Exception {
    private List<CommonError> errors;
    private int errorCode;

    public CommonDomainException() {
    }

    public CommonDomainException(String message) {
        super(message);
    }

    public CommonDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonDomainException(Throwable cause) {
        super(cause);
    }

    public CommonDomainException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CommonDomainException(int code, String message) {
        super(message);
        this.errorCode = code;
    }

    public CommonDomainException(List<CommonError> errors) {
        super();
        this.errors = errors;
    }
    @Data
    public class CommonError {

        private final String code;
        private final String field;
        private final String description;

        public CommonError(String code, String field, String description) {
            this.code = code;
            this.field = field;
            this.description = description;
        }

    }
}


