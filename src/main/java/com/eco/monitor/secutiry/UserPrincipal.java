package com.eco.monitor.secutiry;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Custom {@link org.springframework.security.core.userdetails.UserDetails} implementation.
 *
 * @author Vladyslav Hnes
 */
public class UserPrincipal extends User {

    private final Long userId;
    private final List<String> scopes = new ArrayList<>();

    public UserPrincipal(Long userId, List<String> scopes) {
        super(" ", " ", scopes.stream().map(SimpleGrantedAuthority::new).collect(toList()));
        this.userId = userId;
        this.scopes.addAll(scopes);
    }

    public Long getUserId() {
        return userId;
    }

    public List<String> getScopes() {
        return scopes;
    }
}
