package com.security.Service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by WFX1024 on 2019/2/25.
 */
public interface RbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
