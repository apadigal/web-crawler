/*
 * Swash Tech Ltd..
 *
 * HomeController.java
 *
 * © 2018 Swash Tech Ltd.. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package io.example.webcrawler.rest.controller;
// ---- Import Statements -----------------------------------------------------

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private EntityLinks entityLinks;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public Resource home()
    {
        HashMap hashMap = new HashMap();

        hashMap.put("Welcome", "Welcome to WebCrawler api");
        Resource resource = new Resource(hashMap);
        resource.add(linkTo(methodOn(WebCrawlerController.class).getWebCrawlTree("https://www.babylonhealth.com", 2)).withRel("crawl"));
        Link link = linkTo(methodOn(this.getClass()).home()).withSelfRel();
        resource.add(link);

        return resource;
    }

}