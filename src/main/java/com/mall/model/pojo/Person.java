package com.mall.model.pojo;

/**
 * @Description:
 * @Author: Bentao She
 * @Email: harrypotterandsbt@gmail.com
 * @Date: 2021/9/3 20:31
 * @Version: V1.0
 **/

public class Person {

    private String name;

    private String birthdayDate;

    public Person(String name, String birthdayDate) {
        this.name = name;
        this.birthdayDate = birthdayDate;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }
}
