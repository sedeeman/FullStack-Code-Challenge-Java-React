package com.sedeeman.citzimbproductcatalogservice.config;

import com.sedeeman.citzimbproductcatalogservice.util.ReadJsonFileToJsonObject;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@OpenAPIDefinition
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI baseOpenAPI() {

        ReadJsonFileToJsonObject readJsonObject = new ReadJsonFileToJsonObject();

        ApiResponse getAllSuccessAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value(readJsonObject.read().get("getAllSuccessResponse").toString()))
                )).description("Ok(Success)");

        ApiResponse getSuccessAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value(readJsonObject.read().get("getSuccessResponse").toString()))
                )).description("Ok(Success)");

        ApiResponse postSuccessAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value(readJsonObject.read().get("postSuccessResponse").toString()))
                )).description("Created");

        ApiResponse putSuccessAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value(readJsonObject.read().get("putSuccessResponse").toString()))
                )).description("OK(Success)");

        ApiResponse deleteSuccessAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value(readJsonObject.read().get("deleteSuccessResponse").toString()))
                )).description("No Content");

        ApiResponse noContentAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value(readJsonObject.read().get("noContentResponse").toString()))
                )).description("No Content");

        ApiResponse badRequestAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value(readJsonObject.read().get("badRequestResponse").toString()))
                )).description("Bad Request");

        ApiResponse notFoundAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value(readJsonObject.read().get("notFoundResponse").toString()))
                )).description("Not Found");

        ApiResponse internalServerErrorAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value(readJsonObject.read().get("internalServerErrorResponse").toString()))
                )).description("Internal Server Error");

        Components components = new Components();
        components.addResponses("getAllSuccessAPI", getAllSuccessAPI);
        components.addResponses("getSuccessAPI", getSuccessAPI);
        components.addResponses("postSuccessAPI", postSuccessAPI);
        components.addResponses("putSuccessAPI", putSuccessAPI);
        components.addResponses("deleteSuccessAPI", deleteSuccessAPI);
        components.addResponses("noContentAPI", noContentAPI);
        components.addResponses("notFoundAPI", notFoundAPI);
        components.addResponses("badRequestAPI", badRequestAPI);
        components.addResponses("internalServerErrorAPI", internalServerErrorAPI);

        return new OpenAPI()
                .components(components)
                .info(new Info().title("Product Catalog Service Doc").version("1.0.0").description("Spring Boot RESTful APIs Implementation for CITZ IMB Product Catalog"));
    }

}
