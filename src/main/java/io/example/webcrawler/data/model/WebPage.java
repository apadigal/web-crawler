/*
 * BBC.
 *
 * WebPage.java
 *
 * © 2018 BBC. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package io.example.webcrawler.data.model;
// ---- Import Statements -----------------------------------------------------

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.jsoup.select.Elements;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
public class WebPage implements Serializable {

    private String   title;
    private String   url;
    private Elements links;

}