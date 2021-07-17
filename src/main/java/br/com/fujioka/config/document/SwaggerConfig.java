package br.com.fujioka.config.document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Api(value = "Controle de pessoas")
public class SwaggerConfig {
	@Value("${app.name}")
	private String nome;
	
	@Value("${app.version}")
	private String versao;
	
	@Value("${app.description}")
	private String descricao;
	
	@Value("${app.organization}")
	private String empresa;	

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.fujioka"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,responseMessageForGET())
				.globalResponseMessage(RequestMethod.POST,responseMessageForPost())
				.globalResponseMessage(RequestMethod.DELETE,responseMessageForDelete());
	}
	
	private ApiInfo apiInfo() {

		return new ApiInfo(
				nome, 
				descricao, 
				versao, 
				empresa,
				new Contact("Desenvolvimento", "", "fujiokades@fujioka.com.br"), 
				"", 
				"", 
				Collections.emptyList());
		
	}
	
	private List<ResponseMessage> responseMessageForGET()
	{
	    return new ArrayList<ResponseMessage>() {{
	        add(new ResponseMessageBuilder()
	            .code(204)
	            .message("Retorno vazio")
	            .responseModel(new ModelRef(""))
	            .build());
	        add(new ResponseMessageBuilder()
	            .code(404)
	            .message("Busca não retornou resultado")
	            .build());
	        add(new ResponseMessageBuilder()
		            .code(200)
		            .message("Retorno OK")
		            .build());
	        add(new ResponseMessageBuilder()
		            .code(400)
		            .message("Problema na requisição, caracterizando muitas vezes em validações ")
		            .build());
	        add(new ResponseMessageBuilder()
		            .code(500)
		            .message("Erro interno do servidor")
		            .build());
	    }};
	}
	
	private List<ResponseMessage> responseMessageForPost()
	{
	    return new ArrayList<ResponseMessage>() {{
	        add(new ResponseMessageBuilder()
	            .code(201)
	            .message("Retorno vazio")
	            .responseModel(new ModelRef(""))
	            .build());
	        add(new ResponseMessageBuilder()
		            .code(400)
		            .message("Problema na requisição, caracterizando muitas vezes em validações ")
		            .build());
	        add(new ResponseMessageBuilder()
		            .code(500)
		            .message("Erro interno do servidor")
		            .build());
	    }};
	}
	
	private List<ResponseMessage> responseMessageForPut()
	{
	    return new ArrayList<ResponseMessage>() {{
	        add(new ResponseMessageBuilder()
	            .code(200)
	            .message("Retorno Ok")
	            .responseModel(new ModelRef(""))
	            .build());
	        add(new ResponseMessageBuilder()
		            .code(400)
		            .message("Problema na requisição, caracterizando muitas vezes em validações ")
		            .build());
	        add(new ResponseMessageBuilder()
		            .code(500)
		            .message("Erro interno do servidor")
		            .build());
	    }};
	}
	private List<ResponseMessage> responseMessageForDelete()
	{
	    return new ArrayList<ResponseMessage>() {{
	        add(new ResponseMessageBuilder()
	            .code(204)
	            .message("Retorno vazio")
	            .responseModel(new ModelRef(""))
	            .build());
	        add(new ResponseMessageBuilder()
		            .code(400)
		            .message("Problema na requisição")
		            .build());
	        add(new ResponseMessageBuilder()
		            .code(500)
		            .message("Erro interno do servidor")
		            .build());
	    }};
	}
}
