package org.nerdboy.chatbot.process;

/**
 * Created by xsank.mz on 2016/5/22.
 */
public class MoodHandler implements Handler {
    private static final int MAX_STAND_NUM = 2;
    private static final String DEFAULT_WEARY_ANSWER = "有意思没";

    private static String lastSentence = "";
    private static int repeatCount = 0;

    public void process(Request request, Response response, HandlerChain chain) {
        String currentSentence = request.getQuestion();
        if (currentSentence.equals(lastSentence)) {
            repeatCount++;
        } else {
            repeatCount = 0;
        }
        lastSentence = currentSentence;
        if (stand()) {
            chain.handle(request, response);
        } else {
            chain.unReady();
            response.setResult(DEFAULT_WEARY_ANSWER);
        }
    }

    private boolean stand() {
        return repeatCount < MAX_STAND_NUM;
    }
}
