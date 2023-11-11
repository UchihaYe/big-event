package cn.com.wind;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    void testGenerateToken() {
        // 生成jwt的代码
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "wind");
        String token = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256("wind"));
        System.out.println(token);
    }

    @Test
    void testParseToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTk3MjU3MjgsInVzZXIiOnsiaWQiOjEsInVzZXJuYW1lIjoid2luZCJ9fQ.4-FAryVB5w10Fq9SjOGZRLf96Y0nq_X1AW4K3pneimg";
        try {
            // 解析jwt的代码
            Algorithm algorithm = Algorithm.HMAC256("wind");
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(jwt.getClaim("user").asMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
