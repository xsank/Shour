package org.nerdboy.chatbot.brain.ability;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xsank.mz on 2016/5/28.
 */
public class WeatherTest {
    private Weather weather;

    @Before
    public void setUp(){
        weather=new Weather();
    }

    @Test
    public void testProcess() throws Exception {
        System.out.println(weather.process("杭州天气"));
        System.out.println(weather.process("北京天气"));
        System.out.println(weather.process("涉县天气"));
        System.out.println(weather.process("今天天气"));
    }
}