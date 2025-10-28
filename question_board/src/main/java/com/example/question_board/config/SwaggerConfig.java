package com.example.question_board.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("질문 게시판 API")
                .description("질문 게시판 REST API 문서입니다. " +
                        "게시판, 게시글, 댓글 관리 기능을 제공합니다.")
                .version("1.0.0")
                .contact(new Contact()
                        .name("개발팀")
                        .email("dev@example.com"));
    }
}
