/*
 * Swash Tech Ltd..
 *
 * WebCrawlerService.java
 *
 * © 2018 Swash Tech Ltd.. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package io.example.webcrawler.service;
// ---- Import Statements -----------------------------------------------------

import io.example.webcrawler.data.model.WebPage;
import io.example.webcrawler.data.model.WebPageTree;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WebCrawlerService {
    public WebPageTree crawl(final String webPage, final Integer deep){
        return crawl(getURL(webPage).get().getHost(), webPage, deep, null);
    }

    public WebPageTree crawl(final String hostName, final String webPage, final Integer deep, final List<String> processedUrls) {

        log.info("====> Begin crawler for web page {} with deep {}", webPage, deep);
        if (deep < 0) {
            log.info("<<<<<<  Crawl reached maximum deep, exiting for web page {}", webPage);
            return null;
        }

        final List<String> updatedProcessedUrls = Optional.ofNullable(processedUrls).orElse(new ArrayList<>());
        if (!updatedProcessedUrls.contains(webPage)) {
            updatedProcessedUrls.add(webPage);
            final WebPageTree pageTreeInfo = WebPageTree.builder().url(webPage).build();
            getWebContent(webPage).ifPresent(pageInfo -> {
                pageTreeInfo.setTitle(pageInfo.getTitle());
                log.info("Found {} links on the web page: {}", pageInfo.getLinks().size(), webPage);
                pageInfo.getLinks().forEach(link -> {
                    if(isValidHostToCrawl(getURL(link.attr("abs:href")))) {
                        WebPageTree webPageTree = crawl(hostName, link.attr("abs:href"), deep - 1, updatedProcessedUrls);
                        pageTreeInfo.addChildPage(webPageTree);
                    }
                });
            });
            return pageTreeInfo;
        } else {
            return null;
        }
    }


    public Optional<WebPage> getWebContent(final String webPage) {

        log.info("Web crawling for : {}", webPage);
        try {
            //This config to ignore following redirects to external web pages like
            //        NHS, Facebook etc.
            final Document document = Jsoup.connect(webPage).timeout(5000)
                    .followRedirects(false).get();

            // This returns the links of the web page
            final Elements links = document.select("a[href]");
            final String title = document.title();

//            log.debug("Web page title: {}, links[{}] in web-page : {}", title, links.nextAll(), webPage);
            return Optional.of(new WebPage(title, webPage, links));
        } catch (final IOException | IllegalArgumentException e) {
            log.error(String.format("Error getting data from url %s", webPage), e);
            return Optional.empty();
        }

    }

    private Optional<URL> getURL(String webURL){
        try{
            if(webURL.matches("^http(s)?://.*")) {
                return Optional.of(new URL(webURL));
            }
            else
                return Optional.empty();

        }
        catch (MalformedURLException ex)
        {
            return Optional.empty();
        }
    }

    private Boolean isValidHostToCrawl(Optional<URL> webURL)
    {
        if(webURL.isPresent())
            return webURL.get().getHost().matches(".*(\\.)?babylonhealth\\.com$");
        else
            return false;
    }

}