package nl.mtbrental.eindproject.config;

import nl.mtbrental.eindproject.filter.JwtRequestFilter;
import nl.mtbrental.eindproject.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //JWT token authentication
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/users/register").permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/authenticated").authenticated()
//                .anyRequest().permitAll()
                .antMatchers(HttpMethod.GET,"/users/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/users/{username}").authenticated()
                .antMatchers(HttpMethod.POST,"/users/{username}").authenticated()
                .antMatchers(HttpMethod.PUT,"/users/{username}").authenticated()
                .antMatchers(HttpMethod.DELETE,"/users/{username}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/users/{username}/password").authenticated()
                .antMatchers(HttpMethod.GET,"/users/{username}/authorities").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/users/{username}/authorities").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/users/{username}/authorities/{authority}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/bookings/?username={username}").authenticated()
                .antMatchers(HttpMethod.GET,"/bookings/").authenticated()
                .antMatchers(HttpMethod.POST,"/bookings/").authenticated()
                .antMatchers(HttpMethod.GET,"/bookings/{id}").authenticated()
                .antMatchers(HttpMethod.DELETE,"/bookings/{id}").hasRole("ADMIN")
                .antMatchers("/bikes").hasRole("ADMIN")
                .antMatchers("/bikes/{id}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/bookings/?date=").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/bookings/?bikeId=").hasRole("ADMIN")
                .and()
                .cors().and()
//                .cors(withDefaults())
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}