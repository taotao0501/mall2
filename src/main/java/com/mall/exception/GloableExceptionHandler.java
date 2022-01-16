package com.mall.exception;

import com.mall.common.enums.ResultEnum;
import com.mall.common.utils.ResponseResult;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice("com.mall.controller")
public class GloableExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GloableExceptionHandler.class);

    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseResult parameterMissingExceptionHandler(MissingServletRequestParameterException e) {
        logger.error("", e);
        return new ResponseResult(ResultEnum.PARAMETER_ERROR.getCode(),"请求参数 " + e.getParameterName() +" 不能为空");
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        logger.error("", e);
        return new ResponseResult(ResultEnum.PARAMETER_ERROR.getCode(),"参数体不能为空");
    }

    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     * @return ResponseInfo
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult parameterExceptionHandler(MethodArgumentNotValidException e) {
        logger.error("", e);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (CollectionUtils.isNotEmpty(errors)) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return new ResponseResult(ResultEnum.PARAMETER_ERROR.getCode(), fieldError.getDefaultMessage());
            }
        }
        return new ResponseResult(ResultEnum.PARAMETER_ERROR);
    }

    /**
     * 运行时异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({RuntimeException.class})
    public ResponseResult RuntimeExceptionHandler(ErrorCodeException e) {
        logger.error("其他异常", e);
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (StringUtils.isNotEmpty(e.getMessage())) {
            return new ResponseResult(ResultEnum.UNKNOWN_ERROR.getCode(), e.getMessage()+":"+e.getErrorMessage());
        }
        return new ResponseResult(ResultEnum.UNKNOWN_ERROR);
    }

    /**
     * 其他异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Exception.class})
    public ResponseResult otherExceptionHandler(Exception e) {
        logger.error("其他异常", e);
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (StringUtils.isNotEmpty(e.getMessage())) {
            return new ResponseResult(ResultEnum.UNKNOWN_ERROR.getCode(), e.getMessage());
        }
        return new ResponseResult(ResultEnum.UNKNOWN_ERROR);
    }
}