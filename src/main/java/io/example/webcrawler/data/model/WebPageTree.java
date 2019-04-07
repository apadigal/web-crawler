/*
 * BBC.
 *
 * WebPageTree.java
 *
 * © 2018 BBC. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package io.example.webcrawler.data.model;
// ---- Import Statements -----------------------------------------------------

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@ToString
public class WebPageTree implements Serializable {

    private static final long serialVersionUID = 6139674265725600084L;

    private String   title;
    private String   url;

    private List<WebPageTree> childPages;

    public WebPageTree(String title) {
        this.title = title;
    }

    public WebPageTree(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public WebPageTree(String title, String url, List<WebPageTree> childPages) {
        this.title = title;
        this.url = url;
        this.childPages = childPages;
    }

    public void addChildPage(WebPageTree webPageTree){
        if(childPages == null)
            childPages = new ArrayList<>();

        if(webPageTree != null)
        childPages.add(webPageTree);
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public List<WebPageTree> getChildPages() {
        return childPages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setChildPages(List<WebPageTree> childPages) {
        this.childPages = childPages;
    }
}