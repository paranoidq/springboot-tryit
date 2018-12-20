package me.test.springboottryit.security.iplogin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * filter负责取用户输入的认证等信息，并封装成没有验证的AuthenticationToken实例
 * 然后将认证过程代理给AuthenticationManager，AuthenticationManager则依次调用Provider来验证
 * 只要一个验证通过了，就通过（验证的过程会检查是否需要调用该验证provider）
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class IpAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    IpAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/ipVerify"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // 获取host信息
        String host = request.getRemoteHost();
        // 交给内部AuthenticationManager去认证，实现解耦
        return getAuthenticationManager().authenticate(
            new IpAuthenticationToken(host));
    }
}
