package org.nerdboy.chatbot.storage;

/**
 * Created by xsank.mz on 2016/5/19.
 */
public class Pair {

    private String question;
    private String answer;

    public Pair() {
    }

    public Pair(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return String.format("question:{},answer:{}", this.question, this.answer);
    }
}
