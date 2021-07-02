package ru.jpol.vocabot.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.jpol.vocabot.security.jwt.JwtConfigurer;
import ru.jpol.vocabot.security.jwt.JwtProvider;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN_ENDPOINT = "/admin/**";
    private static final String LOGIN_ENDPOINT = "/auth/signup";
    private static final String HOME_ENDPOINT = "/";

    private final JwtProvider jwtProvider;

    @Autowired
    public SecurityConfig(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HOME_ENDPOINT).anonymous()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtProvider));
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/favicon.ico", "/index.html", "/static/**");
    }
}