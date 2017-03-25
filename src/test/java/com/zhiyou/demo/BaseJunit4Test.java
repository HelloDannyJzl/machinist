package com.zhiyou.demo;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试用例基础类
 * @author jzl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "/applicationContext.xml",
        "/applicationServlet.xml"})
public class BaseJunit4Test {
}
