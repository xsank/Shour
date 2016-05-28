package org.nerdboy.chatbot.process;

import java.util.List;

/**
 * Created by xsank.mz on 2016/5/22.
 */
public class Request {

    private String question;
    private List<String> words;

    public Request(List<String> words) {
        this.words = words;
    }

    public Request(String request) {
        question = request;
    }

    public String getQuestion() {
        return question;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }
}
