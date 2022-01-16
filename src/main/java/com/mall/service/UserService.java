package com.mall.service;

import com.mall.exception.MallException;
import com.mall.model.pojo.User;

/**
 * 描述：     UserService
 */
public interface UserService {

    User getUser();

    void register(String userName, String password) throws MallException;

    User login(String userName, String password) throws MallException;

    void updateInformation(User user) throws MallException;

    boolean checkAdminRole(User user);
}
