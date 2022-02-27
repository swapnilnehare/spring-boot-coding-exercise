package com.telstra.codechallenge.user;

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
public class SpringBootUserControllerTest {


    @Mock
    SpringBootUserService springBootUserService;

    @InjectMocks
    SpringBootUserController springBootUserController;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testController_GetUser_WhenSuccess() {
        Users users = getUserMockData();
        when(springBootUserService.getOldestUserWithZeroFollower(2)).thenReturn(users);
        Users actual = springBootUserController.getHottest(2);
        assertEquals(actual, users);
    }

    private Users getUserMockData() {
        User user1 = new User("100473919",
                "UN20070813",
                "https://github.com/UN20070813");
        User user2 = new User("100473926",
                "cbgnvxhcty",
                "https://github.com/cbgnvxhcty");
        Users users = new Users();
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        users.setUsers(userList);
        return users;
    }
}