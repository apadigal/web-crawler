package io.example.webcrawler.rest.controller;

import io.example.webcrawler.WebCrawlerApplicationIntegrationTest;
import io.example.webcrawler.data.model.WebPage;
import io.example.webcrawler.data.model.WebPageTree;
import io.example.webcrawler.service.WebCrawlerService;
import io.example.webcrawler.util.WebCrawlerTestUtil;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebCrawlerApplicationIntegrationTest
public class WebCrawlerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private WebCrawlerService webCrawlerService;

    WebPage webPage;
    WebPageTree webPageTree;
    String  htmlContent;
    final String hostName = "iamfortest.com";

    @Before
    public void setUp() throws Exception {
        webPageTree = new WebPageTree("TesPortal");
        htmlContent = WebCrawlerTestUtil.readContentAsString("data/test.html");
        final Elements elements = Jsoup.parse(htmlContent).select("a[href]");
        webPage = new WebPage("Test Web Page", "http://iamfortest.com", elements);
    }

    @Test
    public void getWebCrawlTree() throws Exception {

        //Validate BAD Request
        mockMvc.perform(get("/crawl").contentType(MediaTypes.HAL_JSON_VALUE)
                .accept(MediaTypes.HAL_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.when(webCrawlerService.getWebContent(Mockito.anyString())).thenReturn(Optional.of(webPage));

        //Validate nothing returns for invalid portal
        MvcResult mvcResult = mockMvc
                .perform(get("/crawl")
                        .param("portal", "nothing.com")
                        .param("deep", "3")
                        )
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).isEmpty();

        Mockito.when(webCrawlerService.crawl(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(webPageTree);
        mvcResult = mockMvc
                .perform(get("/crawl?portal=https://iamfortest.com&deep=1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty().contains("TesPortal");

    }
}