package com.telstra.codechallenge.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
public class SpringBootRepoService {

    @Value("${git.base.url}")
    private String baseUrl;

    public static final String SORT_KEY = "sort";
    public static final String QUERY = "q";
    public static final String ORDER_KEY = "order";
    public static final String PER_PAGE_LIMIT = "per_page";
    public static final String DESC = "desc";
    public static final String STARS = "stars";
    public static final String CREATED_DATE = "created:";
    public static final Integer LAST_DAYS=7;

    private RestTemplate restTemplate;

    @Autowired
    public SpringBootRepoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Repositories getHottest(int noOfRepos) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, "application/vnd.github.preview");

        UriComponentsBuilder builder = getUriComponentsBuilder(baseUrl + "search/repositories", noOfRepos);

        return this.restTemplate.exchange(
                builder.build().toString(), HttpMethod.GET, new HttpEntity<>(headers),
                Repositories.class).getBody();
    }

    private UriComponentsBuilder getUriComponentsBuilder(String url, int noOfRepos) {
        return UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(QUERY, CREATED_DATE + getYYYYMMDD(getLastLocalDate(LAST_DAYS)))
                .queryParam(SORT_KEY, STARS)
                .queryParam(ORDER_KEY, DESC)
                .queryParam(PER_PAGE_LIMIT, noOfRepos);
    }

    public static LocalDate getLastLocalDate(int days) {
        LocalDate now = LocalDate.now();
        return now.minusDays(days);
    }

    public static String getYYYYMMDD(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }

}
