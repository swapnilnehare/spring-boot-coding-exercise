package com.telstra.codechallenge.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SpringBootUserService {

    @Value("${git.base.url}")
    private String baseUrl;

    public static final String SORT_KEY = "sort";
    public static final String QUERY = "q";
    public static final String ORDER_KEY = "order";
    public static final String ASC = "ASC";
    public static final String JOINED = "joined";
    public static final String PER_PAGE_LIMIT = "per_page";
    public static final String FOLLOWERS = "followers:";

    private RestTemplate restTemplate;

    public SpringBootUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Users getOldestUserWithZeroFollower(int noOfRepos) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, "application/vnd.github.preview");

        UriComponentsBuilder builder = getUriComponentsBuilder(baseUrl + "search/users", noOfRepos);

        return this.restTemplate.exchange(
                builder.build().toString(), HttpMethod.GET, new HttpEntity<>(headers),
                Users.class).getBody();
    }

    private UriComponentsBuilder getUriComponentsBuilder(String url, int noOfRepos) {
        return UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(QUERY, FOLLOWERS + 0)
                .queryParam(SORT_KEY, JOINED)
                .queryParam(ORDER_KEY, ASC)
                .queryParam(PER_PAGE_LIMIT, noOfRepos);
    }
}



