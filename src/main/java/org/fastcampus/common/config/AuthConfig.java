package org.fastcampus.common.config;

import java.util.List;
import org.fastcampus.auth.domain.TokenProvider;
import org.fastcampus.common.principal.AuthPrincipalArguResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {

    private final TokenProvider tokenProvider;

    public AuthConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void addArgumentResolvers(List argumentResolvers) {
        argumentResolvers.add(new AuthPrincipalArguResolver(tokenProvider));
    }
}
