package com.telstra.codechallenge.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @JsonProperty("id")
    private String id;
    @JsonProperty("login")
    private String login;
    @JsonProperty("html_url")
    private String htmlUrl;

}
