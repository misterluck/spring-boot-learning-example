package com.ai.shiro.authc;


import org.apache.shiro.authc.AuthenticationToken;

/**
 * 获取 JWT Token
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
