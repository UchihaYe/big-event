package cn.com.wind.controller;

import cn.com.wind.pojo.Result;
import cn.com.wind.pojo.User;
import cn.com.wind.service.UserService;
import cn.com.wind.util.JwtUtil;
import cn.com.wind.util.Md5Util;
import cn.com.wind.util.ThreadLocalUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 注册新用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 注册结果
     */
    @RequestMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 查询用户是否存在
        User user = userService.findUserByUsername(username);
        if (user != null) {
            return Result.error("用户名已存在");
        } else {
            // 注册用户
            userService.register(username, password);
            return Result.success();
        }
    }


    /**
     * 登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    @RequestMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 查询用户是否存在
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return Result.error("用户名不存在");
        } else {
            // 校验密码
            password = Md5Util.getMD5String(password);
            if (StrUtil.equals(password, user.getPassword())) {
                // 生成jwt
                Map<String, Object> claims = new HashMap<>();
                claims.put("username", user.getUsername());
                claims.put("id", user.getId());
                String jwt = JwtUtil.genToken(claims);
                return Result.success(jwt);
            } else {
                return Result.error("密码错误");
            }
        }
    }

    /**
     * 获取用户信息
     * 请求路径：/userInfo
     * 请求方法：GET
     * 请求参数：
     * - token: String类型，Authorization请求头中携带的令牌
     * 返回结果：Result<User>类型，用户信息
     */
    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        // 根据用户名查询用户
        User user = userService.findUserByUsername(username);
        // 返回用户信息
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        // 校验密码
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (StrUtil.isEmpty(oldPwd) || StrUtil.isEmpty(newPwd) || StrUtil.isEmpty(rePwd)){
            return Result.error("参数不能为空");
        }
        // 原密码是否正确
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findUserByUsername(username);
        if (!user.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码错误");
        }
        if (!newPwd.equals(rePwd)){
            return Result.error("两次密码不一致");
        }
        userService.updatePassword(newPwd);
        return Result.success();
    }
}
