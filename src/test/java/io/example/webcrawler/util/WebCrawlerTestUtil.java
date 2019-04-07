/*
 * BBC.
 *
 * WebCrawlerTestUtil.java
 *
 * © 2018 BBC. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package io.example.webcrawler.util;
// ---- Import Statements -----------------------------------------------------

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WebCrawlerTestUtil {

    public static String readContentAsString(final String uri) throws IOException {
        File resource = new ClassPathResource(uri).getFile();
        return new String(
                Files.readAllBytes(resource.toPath()));
    }

}