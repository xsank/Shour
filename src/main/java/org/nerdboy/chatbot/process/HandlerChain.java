package org.nerdboy.chatbot.process;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xsank.mz on 2016/5/22.
 */
public class HandlerChain {
    private List<Handler> handlers = new ArrayList<Handler>();
    private int index = 0;
    private boolean isReady = true;

    public HandlerChain addHanlder(Handler handler) {
        handlers.add(handler);
        return this;
    }

    public boolean handle(Request request, Response response) {
        if (index < handlers.size()) {
            Handler handler = handlers.get(index++);
            handler.process(request, response, this);
        }
        return isReady;
    }

    public boolean ready(Request request, Response response) {
        boolean result = handle(request, response);
        reset();
        return result;
    }

    private void reset() {
        index = 0;
        isReady = true;
    }

    public void unReady() {
        this.isReady = false;
    }

    public void clear() {
        handlers.clear();
    }
}
