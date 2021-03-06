package com.neweducation.enrollment;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

        @Value (value = "${auth0.apiAudience}")
        private String audience;
        @Value (value = "${auth0.issuer}")
        private String issuer;

        @Bean
        JwtDecoder jwtDecoder() {
                NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
                        JwtDecoders.fromOidcIssuerLocation(issuer);

                OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
                OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
                OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer,
                                                                                              audienceValidator);

                jwtDecoder.setJwtValidator(withAudience);

                return jwtDecoder;
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests().anyRequest().authenticated()
                    .and().cors()
                    .and().oauth2ResourceServer().jwt();
        }
}