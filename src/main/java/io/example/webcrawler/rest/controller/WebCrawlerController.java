/*
 * Swash Tech Ltd..
 *
 * WebCrawlerController.java
 *
 * © 2018 Swash Tech Ltd.. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package io.example.webcrawler.rest.controller;
// ---- Import Statements -----------------------------------------------------

import io.example.webcrawler.data.model.WebPageTree;
import io.example.webcrawler.service.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class WebCrawlerController {

    @Autowired
    WebCrawlerService webCrawlerService;

    @RequestMapping("/crawl")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public Resource getWebCrawlTree(@NotNull @RequestParam(value = "portal") String portal,
                                    @RequestParam(value = "deep") Integer deep){
        WebPageTree webPageTree = webCrawlerService.crawl(portal, deep);
        if(webPageTree != null) {
            Resource resource = new Resource(webPageTree);
            resource.add(linkTo(methodOn(this.getClass()).getWebCrawlTree(portal, deep)).withSelfRel());
            Link link = linkTo(methodOn(HomeController.class).home()).withRel("home");
            resource.add(link);
            return resource;
        }
        return  null;
    }

}