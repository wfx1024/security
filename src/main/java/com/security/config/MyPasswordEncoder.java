package com.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by WFX1024 on 2019/2/25.
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
