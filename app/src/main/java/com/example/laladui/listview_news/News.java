package com.example.laladui.listview_news;

public class News {
    private String uri,title;
    public News(String uri, String title){
        this.uri=uri;
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
