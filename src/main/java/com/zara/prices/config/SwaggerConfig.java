
package com.zara.prices.config;



import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2).select()
                    .apis(RequestHandlerSelectors.basePackage("com.zara.prices"))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(metaData())
                    .useDefaultResponseMessages(false);
    }

    private ApiInfo metaData() {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model;
        try {
            model = reader.read(new FileReader("pom.xml"));
            return new ApiInfo(model.getArtifactId(), model.getDescription(), model.getParent().getVersion(), "",
                    new Contact("Inditex", "http://inditex.com", ""), "", "", new ArrayList<>());
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }

    }
}

