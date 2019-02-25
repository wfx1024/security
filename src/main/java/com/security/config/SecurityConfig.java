package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Created by WFX1024 on 2019/2/25.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyAuthenticationProvider provider;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //自动创建数据库表，使用一次后注释掉，不然会报错
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/index","/user/login").permitAll()
                .anyRequest().authenticated()
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")    //必须经过认证以后才能访问
                .and()
                .rememberMe().userDetailsService(userDetailService).tokenRepository(persistentTokenRepository()).tokenValiditySeconds(600)
                .and()
                .formLogin().loginProcessingUrl("/user/auth").loginPage("/user/login").defaultSuccessUrl("/user/index",true).failureUrl("/error").permitAll()
                .and()
                .logout().logoutUrl("/user/logout").logoutSuccessUrl("/user/login").permitAll()
                .permitAll();
                http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(provider);
        //这样，页面提交时候，密码以明文的方式进行匹配。
        //auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("wfx").password("wfx").roles("ADMIN");
    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被SpringSecurity拦截的问题
        web.ignoring().antMatchers("/static/**");
    }
}
