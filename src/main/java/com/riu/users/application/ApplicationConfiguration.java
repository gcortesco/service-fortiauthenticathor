package com.riu.users.application;

import com.riu.users.domain.repository.UserRepository;
import com.riu.users.domain.service.UserService;
import com.riu.users.domain.service.impl.UserListAndCreator;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//@SecuritySchemes(
//        {
//                @SecurityScheme(name = "FortiAuthenticator", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER),
//                @SecurityScheme(name = "FortiAuthenticator", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)
//        }
//        )
public class ApplicationConfiguration {

    @Bean
    UserService userService(UserRepository repository) {
        return new UserListAndCreator(repository);
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("FortiAuthenticator",new SecurityScheme().type(SecurityScheme.Type.HTTP).in(SecurityScheme.In.HEADER).name("token").scheme("bearer"))
                );
    }
}
