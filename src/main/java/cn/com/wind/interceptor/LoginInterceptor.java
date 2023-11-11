package cn.com.wind.interceptor;

import cn.com.wind.util.JwtUtil;
import cn.com.wind.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author 王叶盛
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的Authorization字段
        String token = request.getHeader("Authorization");
        try {
            // 解析token获取claims
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 将claims放入ThreadLocal
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e) {
            // 发生异常，返回401未授权
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
