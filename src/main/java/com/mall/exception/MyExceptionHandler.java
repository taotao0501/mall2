/**
 * [Product]
 *     nros-base
 * [Copyright]
 *     Copyright © 2019 ZTESoft All Rights Reserved.
 * [FileName]
 *     ExceptionHandler.java
 * [History]
 *     Version  Date      Author     Content
 *     -------- --------- ---------- ------------------------
 *     1.0.0    2019年3月27日   zhouyl5    最初版本
 */
package com.mall.exception;

import com.mall.common.utils.I18nUtils;
import com.mall.common.utils.StringUtil;

/**
 * <b>Summary: </b> 异常处理类
 */
public class MyExceptionHandler {

    private MyExceptionHandler(){}

    public static void publish(String errorCode, String errorMsg)  {
        publish(errorCode, errorMsg, null, null);
    }

    public static void publish(String errorCode, String errorMsg, String... params)  {
        publish(errorCode, errorMsg, null, params);
    }

    public static void publish(String errorCode, String errorMsg, Throwable t) {
        publish(errorCode, errorMsg, t, null);
    }

    public static void publish(String errorCode) {
        publish(errorCode,  null);
    }

    public static void publish(String errorCode, String errorMsg, Throwable t, Object[] params){
        if (t instanceof BusiException) {
            throw new ErrorCodeException(errorCode,t);
        }
        StringBuilder build = new StringBuilder();
        if(StringUtil.isNotNull(errorMsg)){
            build.append(errorMsg);
        }
        String i18nErrorMsg = I18nUtils.getMessage(errorCode, params);
        if (StringUtil.isNotNull(i18nErrorMsg) && !i18nErrorMsg.equals(errorCode)) {
            if(build.length()>0){
                build.append("-");
            }
            build.append(i18nErrorMsg);
        }

        throw new ErrorCodeException(errorCode,build.toString());
    }
}
