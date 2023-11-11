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

    /**
     * 更新用户信息
     * @param user 用户对象
     */
    void update(User user);

    /**
     * 更新用户头像
     * @param avatarUrl
     */
    void updateAvatar(String avatarUrl);

    /**
     * 更新用户密码
     * @param newPwd
     */
    void updatePassword(String newPwd);
}
