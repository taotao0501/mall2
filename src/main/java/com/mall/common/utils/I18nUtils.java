package com.mall.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 国际化工具类
 *
 * @author wangwei
 * @create 2021-12-18 17:15
 */
public class I18nUtils {

    private static final Logger logger = LoggerFactory.getLogger(com.mall.common.utils.I18nUtils.class);

    public I18nUtils() {
    }

    public static String getMessage(String errorCode) {
        String localeMessage = "";

        try {
            localeMessage = getMessage(errorCode, (Object[]) null);
        }
        catch (Exception var3) {
            logger.error("No code named " + errorCode + " found in I18n file");
        }

        return localeMessage;
    }

    public static String getMessage(String errorCode, Object[] args) {
        String localeMessage = "网络异常";
        try {
            MessageSource messageSource = SpringContextUtils.getBean(ResourceBundleMessageSource.class);
            localeMessage = messageSource.getMessage(errorCode, args, LocaleContextHolder.getLocale());
        }
        catch (Exception var4) {
            logger.error("No code named " + errorCode + " found in I18n file");
        }

        return localeMessage;
    }
}
