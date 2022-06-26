package com.jflow.app.endpoint.http.config;

import com.jflow.app.endpoint.http.AbstractController;
import com.jflow.app.main.Application;
import com.jflow.app.main.JFlow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

        Contact author = new Contact(JFlow.AUTHOR_NAME, JFlow.AUTHOR_URL, JFlow.AUTHOR_EMAIL);
        ApiInfo apiInfo = new ApiInfo(JFlow.PROJECT_NAME, JFlow.PROJECT_DOC_DESC, JFlow.PROJECT_VERSION, JFlow.PROJECT_URL, author, JFlow.LICENSE, JFlow.LICENSE_URL, new ArrayList<>());

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo)
                .groupName(JFlow.PROJECT_NAME)
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.basePackage(AbstractController.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build();
    }

}
