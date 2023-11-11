package cn.com.wind.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

/**
 * @author 王叶盛
 */
public class JwtUtil {

    private static final String KEY = "wind";


    /**
     * 生成JWT Token
     *
     * @param claims JWT的声明部分，包含claims信息
     * @return 生成的Token
     */
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256(KEY));
    }

    /**
     * 解析令牌
     *
     * @param token 令牌字符串
     * @return 包含在令牌中的claims声明的Map对象
     */
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
