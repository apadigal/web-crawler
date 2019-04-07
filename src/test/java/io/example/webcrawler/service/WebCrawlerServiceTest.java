package io.example.webcrawler.service;

import io.example.webcrawler.WebCrawlerApplicationIntegrationTest;
import io.example.webcrawler.data.model.WebPage;
import io.example.webcrawler.data.model.WebPageTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebCrawlerApplicationIntegrationTest
public class WebCrawlerServiceTest {

    @Autowired
    WebCrawlerService webCrawlerService;

    @Test
    public void testDeepercrawl() {
        WebPageTree webPageTree = webCrawlerService.crawl("https://www.babylonhealth.com/", 1);
        assertThat(webPageTree).isNotNull().satisfies(treeInfo -> {
            assertThat(treeInfo.getTitle()).contains("Babylon Health");
            assertThat(treeInfo.getUrl()).contains("https://www.babylonhealth.com/");
            assertThat(treeInfo.getChildPages().size()).isGreaterThan(10);
        });
    }


    @Test
    public void testGetWebContent() {
        final Optional<WebPage> info = webCrawlerService.getWebContent("https://www.babylonhealth.com");
        assertThat(info).isPresent();
        assertThat(info.get().getTitle()).contains("Babylon Health");
        assertThat(info.get().getUrl()).contains("https://www.babylonhealth.com");
        assertThat(info.get().getLinks().size()).isGreaterThan(10);
    }

}