package org.nerdboy.chatbot.process;

import org.nerdboy.chatbot.nlp.CoreNLP;

import java.util.List;

/**
 * Created by xsank.mz on 2016/5/22.
 */
public class FilterHandler implements Handler {

    public void process(Request request, Response response, HandlerChain chain) {
        List<String> words = CoreNLP.filterWords(request.getQuestion());
        request.setWords(words);
        if (words.size() > 0) {
            chain.handle(request, response);
        } else {
            chain.unReady();
        }
    }
}
