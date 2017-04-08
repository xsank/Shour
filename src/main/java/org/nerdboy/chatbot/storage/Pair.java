package org.nerdboy.chatbot.storage;

/**
 * Created by xsank.mz on 2016/5/19.
 */
public class Pair {

    private String question;
    private String[] answers;

    public Pair() {
    }

    public Pair(String question, String... answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return String.format("question:{},answers:{}", this.question, this.answers);
    }
}
