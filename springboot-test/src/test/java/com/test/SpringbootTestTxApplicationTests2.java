package com.test;

import com.test.service.ChildService;
import com.test.service.MainService;
import com.test.service.UserService;
import org.assertj.core.util.Lists;
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
    public void testGapLockException_Step1() throws Exception {
        userService.updateIdBetween(1L, 6L);

    }

    @Test
    public void testGapLockException_Step2() throws Exception {
        childService.childTestUpdate(2L);
    }

    /**
     * 测试事务的提交时间，字段的update_time为ON UPDATE CURRENT_TIMESTAMP
     * 事务A：00:00:00 时开始，00:00:49 结束，事务刚开始时更新数据 id=1 -> testTransactionCommitTime_Step1
     * 流程B（无事务）：待事务A开始，立即批量更新数据（假如00:00:00开始），包含与事务A更新的数据 id=[1,2] -> testTransactionCommitTime_Step2
     *
     * 观察id=2的更新时间. 结果为00:00:00，说明当字段为设置为ON UPDATE CURRENT_TIMESTAMP
     * 时，不是事务提交时触发更新时间，而是产生数据更新时就触发了
     *
     *
     */
    @Test
    public void testTransactionCommitTime_Step1() throws Exception {
        mainService.updateA(1L, 40000);
    }

    @Test
    public void testTransactionCommitTime_Step2() throws Exception {
        userService.updateAgeBatch(Lists.newArrayList(1L, 2L), 13);
    }

    @Test
    public void testMainUpdatePK() throws Exception {
        mainService.updateTransactionThenSleep(1L, 2L);
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
