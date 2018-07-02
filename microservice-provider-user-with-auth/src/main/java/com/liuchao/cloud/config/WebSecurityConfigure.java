package com.liuchao.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author liuchao
 * @date 2018/6/30 21:45
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 所有的请求, 都需要经过HTTP basic认证
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 明文编码器. 这是一个不做任何操作的密码编码器, 是Spring提供给我们做明文测试的
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
    }

    @Component
    class CustomUserDetailsService implements UserDetailsService {
        /**
         * 模拟两个账户:
         * ①账号是user, 密码是password1, 角色是user-role
         * ②账号是admin, 密码是password2, 角色是admin-user
         */
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            if ("user".equals(username)) {
                return new SecurityUser("user", "password1", "user-role");
            }else if ("admin".equals(username)) {
                return new SecurityUser("admin", "password2", "admin-role");
            } else {
                return null;
            }
        }
    }

    class SecurityUser implements UserDetails {

        private Long id;
        private String username;
        private String password;
        private String role;

        public SecurityUser() {}

        public SecurityUser(String username, String password, String role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role);
            authorities.add(authority);
            return  authorities;
        }

        @Override
        public String getPassword() {
            return this.password;
        }

        @Override
        public String getUsername() {
            return this.username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return false;
        }

        @Override
        public boolean isAccountNonLocked() {
            return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return false;
        }

        @Override
        public boolean isEnabled() {
            return false;
        }
    }

}

