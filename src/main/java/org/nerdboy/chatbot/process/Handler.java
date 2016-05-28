package org.nerdboy.chatbot.process;

/**
 * Created by xsank.mz on 2016/5/22.
 */
public interface Handler {

    void process(Request request, Response response, HandlerChain chain);
}
