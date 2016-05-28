package org.nerdboy.chatbot.server;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xsank.mz on 2016/5/19.
 */
public class HttpServerTest {

    public HttpServer server;

    @Before
    public void setUp() throws Exception {
        server=new HttpServer();
    }

    @Test
    public void testDestroy() throws Exception {
        server.destroy();
    }
}