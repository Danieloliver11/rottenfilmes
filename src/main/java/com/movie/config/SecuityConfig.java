package com.movie.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/*
 * Libera acesso do backend que esta hospedado em um servidor com o frontand que esta em outro servidor
 * */

@Configuration
@EnableWebSecurity
public class SecuityConfig  extends WebSecurityConfigurerAdapter{
	
//	@Autowired
//	private Environment env;

//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		
//		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
//			http.headers().frameOptions().disable();
//		}
//		
//		http.cors().and().cors().disable();
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.authorizeRequests().anyRequest().permitAll();
//		
//	}
//	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource(){
//		
//		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//		corsConfiguration.setAllowedMethods(Arrays.asList("POST","GET","PUT","DELETE"));
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", corsConfiguration);
//		
//		return source;
//	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
}
