package com.portalSekolah.config;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import com.portalSekolah.entity.User;

public class PortalMethodSecurityExpressionRoot extends SecurityExpressionRoot
		implements MethodSecurityExpressionOperations {

	public PortalMethodSecurityExpressionRoot(Authentication authentication) {
		super(authentication);
	}

	private Object filterObject;
	private Object returnObject;

	public boolean hasUserRole(String role) {
		User user = getSecurityUser();
		return user.getRoles().stream().anyMatch(r -> r.getRoleCode().equals(role));
	}

	@Override
	public void setFilterObject(Object filterObject) {
		this.filterObject = filterObject;
	}

	@Override
	public Object getFilterObject() {
		return this.filterObject;
	}

	@Override
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	@Override
	public Object getReturnObject() {
		return this.returnObject;
	}

	@Override
	public Object getThis() {
		return this;
	}

	public User getSecurityUser() {
		return (User) authentication.getPrincipal();
	}

}
