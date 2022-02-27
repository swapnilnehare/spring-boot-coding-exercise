package com.telstra.codechallenge.repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public class SpringBootRepoControllerTest {

    @Mock
    SpringBootRepoService springBootRepoService;

    @InjectMocks
    SpringBootRepoController springBootRepoController;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testController_GetRepo_WhenSuccess() {
        Repositories repositories = getRepositories();
        when(springBootRepoService.getHottest(2)).thenReturn(repositories);
        Repositories actual = springBootRepoController.getHottest(2);
        assertEquals(actual, repositories);
    }

    private Repositories getRepositories() {
            Repositories repositories=new Repositories();
            Repository repository=new Repository("financial_advice",
               "https://github.com/emilepetrone/financial_advice",
                "586",
                null,
                 "What financial info would I have wanted to know when I was 22 and jumping into tech?");
            Repository repository1=new Repository( "awesome-cicd-security",
                   "https://github.com/myugan/awesome-cicd-security",
                 "249",
               null,
                 ":books: A curated list of awesome CI CD security resources");
            List<Repository> repositoryList=new ArrayList<>();
            repositoryList.add(repository);
            repositoryList.add(repository1);
            repositories.setRepositories(repositoryList);
            return repositories;
    }


}