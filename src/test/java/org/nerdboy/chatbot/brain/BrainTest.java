package org.nerdboy.chatbot.brain;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xsank.mz on 2016/5/19.
 */
public class BrainTest {

    private static Brain brain;

    @BeforeClass
    public static void setUp() throws Exception {
        brain=new Brain();
        brain.initAbilities("None,Datetime,Weather");
        brain.initHandler("FilterHandler,MoodHandler,AccountHandler");
    }

    @After
    public void testDown() throws Exception {

    }

    @Test
    public void testResponse() throws Exception {
        System.out.println(brain.response("你好"));
        System.out.println(brain.response("你好"));
        System.out.println(brain.response("你好"));
        System.out.println(brain.response("你好吗"));
        System.out.println(brain.response("你多大了"));
        System.out.println(brain.response("今天天气怎么样"));
        System.out.println(brain.response("北京天气怎么样"));
        System.out.println(brain.response("现在时间是"));
    }

    @After
    public void testDie() throws Exception {
        brain.die();
    }
}