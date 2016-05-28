package org.nerdboy.chatbot.brain.ability;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xsank.mz on 2016/5/28.
 */
public class DatetimeTest {
    private Datetime datetime;

    @Before
    public void setUp(){
        datetime=new Datetime();
    }

    @Test
    public void testProcess() throws Exception {
        System.out.println(datetime.process("现在时间"));
    }
}