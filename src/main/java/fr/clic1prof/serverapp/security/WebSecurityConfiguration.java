package fr.clic1prof.serverapp.security;

import fr.clic1prof.serverapp.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Configuration class.
@EnableWebSecurity // Let Spring know that this is about security.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired // Handled by Spring.
    private UserDetailsService service;

    @Autowired
    private AuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtRequestFilter filter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(this.service).passwordEncoder(this.encoder);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.service); // Use our custom service.
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.csrf().disable()
                // Authorize requests to this URI without authentication.
                .authorizeRequests().antMatchers("/login", "/register").permitAll()
                // All the other requests need an authentication.
                .anyRequest().authenticated().and()
                // Allow exception handling. Enabled by default.
                // Using jwt authentication.
                .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint).and()
                // Using stateless session. Each request is independent.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Adding a filter which will be used before the ones that exist.
        security.addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
