package me.test.springboottryit.security.iplogin;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Data
public class IpAuthenticationToken extends AbstractAuthenticationToken {

    private String ip;


    /**
     * 此构造方法是认证时使用
     *
     * @param ip
     */
    public IpAuthenticationToken(String ip) {
        super(null);
        this.ip = ip;
        super.setAuthenticated(false);
    }

    /**
     * 此构造方法是认证成功后使用
     *
     * @param authorities
     * @param ip
     */
    public IpAuthenticationToken(String ip, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.ip = ip;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.ip;
    }
}
