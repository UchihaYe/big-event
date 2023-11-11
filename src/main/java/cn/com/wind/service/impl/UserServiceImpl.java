package cn.com.wind.service.impl;

import cn.com.wind.mapper.UserMapper;
import cn.com.wind.pojo.User;
import cn.com.wind.service.UserService;
import cn.com.wind.util.Md5Util;
import cn.com.wind.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 王叶盛
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    /**
     * 注册用户
     *
     * @param username
     * @param password
     */
    @Override
    public void register(String username, String password) {
        // 加密密码
        String passwordMd5 = Md5Util.getMD5String(password);
        userMapper.insertUser(username, passwordMd5);
    }

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     */
    @Override
    public void update(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 更新用户头像
     *
     * @param avatarUrl
     */
    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(id, avatarUrl);
    }

    /**
     * 更新用户密码
     *
     * @param newPwd
     */
    @Override
    public void updatePassword(String newPwd) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePassword(Md5Util.getMD5String(newPwd),id);
    }
}
