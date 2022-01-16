package com.mall.common;

import com.mall.exception.MyExceptionHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * 参数检查工具
 *
 * @author 王威
 * @version 1.0
 * @since 2021-12-30
 */
public final class NrosPreconditions {

    public static void isNull(Object object, String errorCode, Object... params) {
        if (object != null) {
            throwException(errorCode, params);
        }
    }


    private static void throwException(String errorCode, Object... params) {
        int length = params == null ? 0 : params.length;

        if (length > 0) {
            List<String> message = new ArrayList<>(length);
            for (Object obj : params) {
                message.add(obj.toString());
            }

            MyExceptionHandler.publish(errorCode, null, message.toArray(new String[length]));
        }
        else {
            MyExceptionHandler.publish(errorCode, null);
        }
    }
}
