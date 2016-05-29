package org.nerdboy.chatbot.server;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xsank.mz on 2016/5/29.
 */
public class QAServerTest {

    private static QAServer server;

    @BeforeClass
    public static void setUp(){
        server=new QAServer();
    }


    @AfterClass
    public static void testDestroy() throws Exception {
        server.destroy();
    }
}