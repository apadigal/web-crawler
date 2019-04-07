package io.example.webcrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableEntityLinks
@ServletComponentScan
public class WebCrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebCrawlerApplication.class, args);
    }

}
