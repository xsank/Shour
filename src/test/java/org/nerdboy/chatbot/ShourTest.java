package org.nerdboy.chatbot;

import org.junit.*;

/**
 * Created by xsank.mz on 2016/5/19.
 */
public class ShourTest {

    public static String property = "Shour.properties";
    private static Shour shour;

    @BeforeClass
    public static void setUp() throws Exception {
        shour = new Shour(property);
    }

    @Test
    public void testServe(){
        shour.serve();
    }

    @Test
    public void testResponse() {
        System.out.println(shour.response("你好"));
        System.out.println(shour.response("你是谁"));
        System.out.println(shour.response("我喜欢你"));
        System.out.println(shour.response("我不开心"));
        System.out.println(shour.response("你猜"));
        System.out.println(shour.response("你们几点上班？"));
        System.out.println(shour.response("杭州天气怎么样"));
        System.out.println(shour.response("你真笨"));
        System.out.println(shour.response("哈哈"));
        System.out.println(shour.response("哈哈"));
        System.out.println(shour.response("哈哈"));
    }
}