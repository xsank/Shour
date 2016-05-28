package org.nerdboy.chatbot.storage;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xsank.mz on 2016/5/20.
 */
public class MemoryTest {

    private static Memory memory;

    @BeforeClass
    public static void setUp() throws Exception {
        memory=new Memory();
    }

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testSearch() throws Exception {
        System.out.println(memory.search("你好吗？"));
    }

    @Test
    public void testContext() throws Exception {

    }

    @Test
    public void testFlush() throws Exception {

    }
}