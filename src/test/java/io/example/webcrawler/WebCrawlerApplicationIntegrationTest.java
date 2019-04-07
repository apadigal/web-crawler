/*
 * Swash Tech Ltd.
 *
 * WebCrawlerApplicationIntegrationTest.java
 *
 * © 2018 Swash Tech Ltd. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package io.example.webcrawler;
// ---- Import Statements -----------------------------------------------------

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = WebCrawlerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("local")
@ContextConfiguration
public @interface WebCrawlerApplicationIntegrationTest {

}