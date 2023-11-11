package cn.com.wind.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户类
 * @author 王叶盛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用户ID
     */
    @NotNull
    private Integer id;

    /**
     * 用户名
     */
    @NotEmpty
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 昵称
     */
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;

    /**
     * 邮箱
     */
    @Email
    @NotEmpty
    private String email;

    /**
     * 用户头像地址
     */
    private String userPic;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}

