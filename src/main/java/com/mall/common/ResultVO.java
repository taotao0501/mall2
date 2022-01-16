package com.mall.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@ApiModel("返回结果")
public class ResultVO<T> {
    @ApiModelProperty(value = "响应状态码(10000成功,10001失败)",dataType = "int")
    public int code;
    @ApiModelProperty(value = "响应提示信息")
    private String msg;
    @ApiModelProperty(value = "响应数据")
    private T data;


    public ResultVO<T> success(T data){
        return new ResultVO<T>(ResStatus.YES,"success",data);
    }
    public ResultVO<T> success(String msg, T data){
        return new ResultVO<T>(ResStatus.YES,msg,data);
    }
    public ResultVO<T> success(){
        return new ResultVO<T>(ResStatus.YES,"success",null);
    }
    public ResultVO<T> fail(){
        return new ResultVO<T>(ResStatus.NO,"fail",null);
    }
    public ResultVO<T> fail(T data){
        return new ResultVO<T>(ResStatus.NO,"fail",data);
    }
    public ResultVO<T> failMSG(T data){
        return new ResultVO<T>(ResStatus.MSG,"fail",data);
    }
    public ResultVO<T> fail(String msg, T data){
        return new ResultVO<T>(ResStatus.NO,msg,data);
    }
    public ResultVO<T> msg(String msg){
        return new ResultVO<T>(ResStatus.MSG,msg,null);
    }

}
