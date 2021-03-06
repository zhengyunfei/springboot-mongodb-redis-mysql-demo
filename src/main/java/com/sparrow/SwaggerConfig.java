package com.sparrow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

/**
 * Swagger 配置文件
 *
 * @author 郑云飞
 *
 */

public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("v1").select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/api/v1/**")).build().apiInfo(apiInfo());
	}

	@Bean
	public Docket api2() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("v2").select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/api/v2/**")).build().apiInfo(apiInfo());
	}

	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Sparrow API 手册", "API手册", "0.1.0", "Terms of service", "support@sparrow.com",
				"Apache 2.0", "");
		return apiInfo;
	}

}
