package com.postech.techchallengefase1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("may.veloso.lima@gmail.com");
        contact.setName("Mayara Lima");

        Info info = new Info()
                .title("Tech Challenge Fase 2")
                .version("2.0")
                .contact(contact)
                .description("Nesta segunda fase, o objetivo era alterar a API desenvolvida na fase anterior para adicionar a persistência das informações em banco de dados e criar as APIs de leitura, alteração e exclusão dos dados");

        return new OpenAPI().info(info);
    }
}
