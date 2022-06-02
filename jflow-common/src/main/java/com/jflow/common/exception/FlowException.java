package com.jflow.common.exception;

import cn.hutool.core.text.StrFormatter;
import com.jflow.common.error.FlowError;
import lombok.Data;

/**
 * @author neason
 * @since 0.0.1
 */
@Data
public class FlowException extends RuntimeException {

    private static final long serialVersionUID = -2022001;

    private String code;
    private String message;
    private Throwable throwable;

    public FlowException(FlowError error, Object... args) {
        super(StrFormatter.format(error.errorMessage(), args));
        this.code = StrFormatter.format(error.errorMessage(), args);
        this.message = error.errorMessage();
    }

    public FlowException(FlowError error, Throwable throwable, Object... args) {
        super(StrFormatter.format(error.errorMessage(), args), throwable);
        this.code = error.errorCode();
        this.message = StrFormatter.format(error.errorMessage(), args);
        this.throwable = throwable;
    }

}
