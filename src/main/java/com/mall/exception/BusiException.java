/**
 * [Product]
 *     nros-base
 * [Copyright]
 *     Copyright © 2019 ZTESoft All Rights Reserved.
 * [FileName]
 *     BusiException.java
 * [History]
 *     Version  Date      Author     Content
 *     -------- --------- ---------- ------------------------
 *     1.0.0    2019年3月27日   zhouyl5    最初版本
 */
package com.mall.exception;

import java.io.Serializable;

/**
 * <b>Summary: </b> 自定义业务异常
 */
@SuppressWarnings("serial")
public class BusiException extends RuntimeException implements Serializable {
    /** 异常编码 **/
    private String errorCode;

    /** 异常描述 **/
    private String errorMsg;

    public BusiException() {
        super();
    }

    public BusiException(String message) {
        this(null, message, null);
    }

    public BusiException(String errorCode, String message) {
        this(errorCode, message, null);
    }

    public BusiException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        errorMsg = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (errorCode != null) {
            sb.append("errorCode = [");
            sb.append(errorCode);
            sb.append("]");
            if (errorMsg != null) {
                sb.append("  errorDesc= [");
                sb.append(errorMsg);
                sb.append("]");
            }
        }
        for (int i = 0; i < getStackTrace().length; i++) {
            sb.append("\r\n\tat ");
            sb.append(getStackTrace()[i]);
        }
        Throwable cause = getCause();
        while (cause != null) {
            sb.append("\r\nCause by: ");
            sb.append(cause.toString());
            for (int i = 0; i < cause.getStackTrace().length; i++) {
                sb.append("\r\n\tat ");
                sb.append(cause.getStackTrace()[i]);
            }
            cause = cause.getCause();
            if (cause != null) {
                sb.append("\r\nCaused by: ");
            }
        }
        return sb.toString();
    }
}
