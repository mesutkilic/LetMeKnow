package com.vsahin.letmeknow.Entity;

/**
 * Created by volkansahin on 17.03.2018.
 */

public class Event {
    private long id;
    private String title;
    private String content;
    private String group;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
