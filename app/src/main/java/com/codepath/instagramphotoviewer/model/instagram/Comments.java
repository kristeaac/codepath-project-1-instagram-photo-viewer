package com.codepath.instagramphotoviewer.model.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comments {
    @JsonProperty("data")
    private List<Comment> comments;
    private int count;

    public List<Comment> getComments() {
        return comments == null ? Collections.<Comment>emptyList() : comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
