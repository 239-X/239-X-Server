package com.minimal.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author linzhiqiang
 */

@Configuration
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class})
public class SwaggerConfig {
    @Bean
    public Docket docketUser() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("minimal系统网关")
                        .description("前台REST接口文档")
                        .contact(new Contact("@LinZhiQiang", "", "1913045515@qq.com"))
                        .version("1.0.0")
                        .build())
                .groupName("接口文档")
                .select().apis(RequestHandlerSelectors.basePackage("com.minimal.controller.swagger")).paths(PathSelectors.any())
                .build();
    }
}
