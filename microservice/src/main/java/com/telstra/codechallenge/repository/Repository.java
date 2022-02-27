package com.telstra.codechallenge.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repository {

    @JsonProperty("name")
    private String name;
    @JsonProperty("html_url")
    private String html_url;
    @JsonProperty("watchers_count")
    private String watchers_count;
    @JsonProperty("language")
    private String language;
    @JsonProperty("description")
    private String description;

}
