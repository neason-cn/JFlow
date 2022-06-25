package com.jflow.app.endpoint.http.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author neason
 * @since 0.0.1
 */
@Configuration
@EnableSwagger2
public class ApiDocConfiguration {

    @Value("${jflow.swagger.enable:false}")
    private boolean enableSwagger;

    @Bean
    public Docket docket() {

        Contact author = new Contact("neason-cn","https://github.com/neason-cn/","neason.cn@outlook.com");
        ApiInfo apiInfo = new ApiInfo("JFlow", "JFlow Api Document","0.0.1", "https://github.com/neason-cn/JFlow", author, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0.html", new ArrayList<>());

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo)
                .groupName("JFlow")
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jflow.app.endpoint.http"))
                .paths(PathSelectors.any())
                .build();
    }

}
