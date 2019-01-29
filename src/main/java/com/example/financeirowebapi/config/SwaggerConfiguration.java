package com.example.financeirowebapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurerAdapter{
	
	//http://localhost:8080/swagger-ui.html

	@Bean
	public Docket userApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("financeirowebapi").select().paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.example.financeirowebapi.resource")).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		final springfox.documentation.service.ApiInfo apiInfo = new ApiInfoBuilder().title("Angular7 REST API")
				.description("Financeiro Web - API of financial").version("Version API 1.0").build();
		return apiInfo;
	}
	

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addRedirectViewController("/docApi/v2/api-docs", "/v2/api-docs");
		registry.addRedirectViewController("/docApi/swagger-resources/configuration/ui",
				"/swagger-resources/configuration/ui");
		registry.addRedirectViewController("/docApi/swagger-resources/configuration/security",
				"/swagger-resources/configuration/security");
		registry.addRedirectViewController("/docApi/swagger-resources", "/swagger-resources");
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/docApi/swagger-ui.html**")
				.addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		registry.addResourceHandler("/docApi/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
