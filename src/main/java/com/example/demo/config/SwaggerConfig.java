package com.example.demo.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.show}")
    private boolean swaggerShow;
    
    @SuppressWarnings("unchecked")
    @Bean
    public Docket api() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authorization").description("Token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        
        return new Docket(DocumentationType.SWAGGER_2).enable(swaggerShow)
//                .groupName("rest")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false).forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(Predicates.or(PathSelectors.regex("/api/.*")))// 过滤的接口
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("钰翔银企直连服务")
                .description("api").termsOfServiceUrl("")
                .version("1.0").build();
    }


    @Bean
    public WebMvcConfigurer webMvcConfig () {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                //资源文件是否扫描
                if (swaggerShow) {
                    registry.addResourceHandler("swagger-ui.html")
                            .addResourceLocations("classpath:/META-INF/resources/");

                    registry.addResourceHandler("/webjars/**")
                            .addResourceLocations("classpath:/META-INF/resources/webjars/");
                }
            }
        };
    }

}
