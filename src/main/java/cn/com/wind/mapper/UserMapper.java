package cn.com.wind.mapper;

import cn.com.wind.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 王叶盛
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    User findUserByUsername(String username);

    /**
     * 插入用户
     *
     * @param username 用户名
     * @param password 密码
     */
    void insertUser(String username, String password);

    /**
     * 更新用户
     *
     * @param user 用户
     */
    void updateUser(User user);

    /**
     * 更新用户头像
     *
     * @param id         用户id
     * @param avatarUrl 头像url
     */
    void updateAvatar(Integer id, String avatarUrl);

    /**
     * 更新用户密码
     *
     * @param password md5加密后的密码
     * @param id        用户id
     */
    void updatePassword(String password, Integer id);
}
