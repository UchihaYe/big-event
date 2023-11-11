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
}
