package cn.com.wind.controller;

import cn.com.wind.pojo.Result;
import cn.com.wind.pojo.User;
import cn.com.wind.service.UserService;
import cn.com.wind.util.JwtUtil;
import cn.com.wind.util.Md5Util;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王叶盛
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 查询用户是否存在
        User user = userService.findUserByUsername(username);
        if (user!= null) {
            return Result.error("用户名已存在");
        }else {
            // 注册用户
            userService.register(username, password);
            return Result.success();
        }
    }

    @RequestMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 查询用户是否存在
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return Result.error("用户名不存在");
        }else {
            // 校验密码
            password = Md5Util.getMD5String(password);
            if (StrUtil.equals(password, user.getPassword())) {
                // 生成jwt
                Map<String, Object> claims = new HashMap<>();
                claims.put("username", user.getUsername());
                claims.put("id", user.getId());
                String jwt = JwtUtil.genToken(claims);
                return Result.success(jwt);
            }else {
                return Result.error("密码错误");
            }
        }
    }
}
