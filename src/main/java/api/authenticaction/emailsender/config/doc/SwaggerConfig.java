package api.authenticaction.emailsender.config.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private Contact contact() {
        return new Contact(
                "Luis Felipe Mota",
                "https://www.github.com/Lupesms97",
                "lupesms97@gmail.com");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Email Sender API")
                .description("API para envio de email com autenticação de usuário")
                .version("1.0.1")
                .termsOfServiceUrl("Termos de Uso - Privado")
                .license("Licença - Grupo Sels")
                .licenseUrl("https://www.github.com/Lupesms97")
                .contact(contact())
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("api.authenticaction.emailsender.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
}