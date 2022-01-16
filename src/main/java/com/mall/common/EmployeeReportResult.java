package com.mall.common;

import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Bentao She
 * @Email: harrypotterandsbt@gmail.com
 * @Date: 2021/9/6 17:21
 * @Version: V1.0
 **/

@Component

public class EmployeeReportResult {

    private Integer userId;
    private String userName;
    private String mobile;


    public EmployeeReportResult() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public EmployeeReportResult(Integer userId, String userName, String mobile) {
        this.userId = userId;
        this.userName = userName;
        this.mobile = mobile;
    }
}
