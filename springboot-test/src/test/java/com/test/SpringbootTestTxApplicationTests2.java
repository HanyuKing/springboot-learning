package com.test;

import com.test.service.ChildService;
import com.test.service.MainService;
import com.test.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTestTxApplicationTests2 {

    @Autowired
    private UserService userService;

    @Autowired
    private MainService mainService;

    @Autowired
    private ChildService childService;

    @Test
    public void contextLoads() {
        userService.insertName("json");
    }

    @Test
    public void testMainUpdatePK() throws Exception {
        mainService.updateTest(1L, 2L);
    }

    @Test
    public void testChildUpdatePK() throws Exception {
        System.out.println("testChildUpdatePK start: " + System.currentTimeMillis());

        try {
            childService.childTestUpdate(2L);
        } finally {
            System.out.println("testChildUpdatePK send : " + System.currentTimeMillis());
        }
    }


    @Test
    public void testMainUpdatePKChildHasException() throws Exception {
        mainService.updateTest2(1L, 2L);
    }

}
