package com.learn.security.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

class RobotAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 4796261071241381380L;
	private final String password;

    private RobotAuthenticationToken() {
        super(AuthorityUtils.createAuthorityList("ROLE_robot"));
        super.setAuthenticated(true);
        this.password = null;
    }

    private RobotAuthenticationToken(String password) {
        super(AuthorityUtils.NO_AUTHORITIES);
        super.setAuthenticated(false);
        this.password = password;
    }

    public static RobotAuthenticationToken authenticated() {
        return new RobotAuthenticationToken();
    }

    public static RobotAuthenticationToken unauthenticated(String password) {
        return new RobotAuthenticationToken(password);
    }

    @Override
    public Object getCredentials() {
        return this.getPassword();
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public Object getPrincipal() {
        return "Ms Robot 🤖";
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        throw new RuntimeException("DON'T CHANGE THE AUTH STATUS 😱");
    }
}