package com.freshworks.virtualdoctor.payload;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Collection<? extends GrantedAuthority> getRole() {
        return role;
    }

    public void setRole(Collection<? extends GrantedAuthority> role) {
        this.role = role;
    }

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public JwtAuthenticationResponse(String accessToken, String username, Collection<? extends GrantedAuthority> role) {
        this.accessToken = accessToken;
        this.username = username;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
