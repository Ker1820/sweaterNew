package com.example.sweater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import static freemarker.template.utility.Collections12.singletonList;

@SpringBootApplication
public class Application {

    public Application() {
    }
    @Autowired
    public Application(FreeMarkerConfigurer freeMarkerConfigurer) {
        freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(singletonList("/META-INF/security.tld"));
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
