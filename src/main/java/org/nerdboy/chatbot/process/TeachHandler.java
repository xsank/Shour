package org.nerdboy.chatbot.process;

import org.nerdboy.chatbot.Shour;

/**
 * Created by xsank.mz on 2017/4/8.
 */
public class TeachHandler implements Handler {
    private static final String TEARCH_DOOR = "teach-shour";
    private static final String QUESTION = "q";
    private static final String ANSWER = "a";
    private static final String SEPERATOR = "=";
    private static final String DEFAULT_SUCCESS_LEARN_ANSWER = "我学会了~";
    private static final String DEFAULT_FAIL_LEARN_ANSWER = "你教的不对，格式应该是：teach-shour=q=你好a=你好";

    @Override
    public void process(Request request, Response response, HandlerChain chain) {
        String input = request.getQuestion();
        if (isTeach(input)) {
            response.setResult(teach(input));
            chain.unReady();
        }
    }

    private String teach(String input) {
        String result = DEFAULT_FAIL_LEARN_ANSWER;
        String teachPrefix = TEARCH_DOOR + SEPERATOR;
        int qai = input.indexOf(teachPrefix);
        if (qai >= 0) {
            String qa = input.substring(qai + teachPrefix.length());
            String qPrefix = QUESTION + SEPERATOR;
            String aPrefix = ANSWER + SEPERATOR;
            int qi = qa.indexOf(qPrefix);
            int ai = qa.indexOf(aPrefix);
            if (ai != -1 && qi != -1) {
                String question = qa.substring(qi + qPrefix.length(), ai);
                String answer = qa.substring(ai + aPrefix.length());
                Shour.learn(question, answer);
                result = DEFAULT_SUCCESS_LEARN_ANSWER;
            }
        }
        return result;
    }

    private boolean isTeach(String input) {
        return input.startsWith(TEARCH_DOOR);
    }
}
