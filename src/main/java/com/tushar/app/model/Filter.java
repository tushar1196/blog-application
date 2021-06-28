package com.tushar.app.model;

import java.util.List;
import java.util.Set;

public class Filter {

    private List<Tag> tags;
    private Set<String> authors;

    public Set<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Filter() {
    }

    public Filter(Set<String> authors) {
        this.authors = authors;
    }

    public Filter(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "tags=" + tags +
                '}';
    }
}
