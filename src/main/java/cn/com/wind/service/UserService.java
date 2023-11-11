package cn.com.wind.service;

import cn.com.wind.pojo.User;

/**
 * @author 王叶盛
 */
public interface UserService {


    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 注册用户
     * @param username
     * @param password
     */
    void register(String username, String password);
}
