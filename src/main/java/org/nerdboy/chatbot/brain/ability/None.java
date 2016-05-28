package org.nerdboy.chatbot.brain.ability;

/**
 * Created by xsank.mz on 2016/5/26.
 */
public class None extends Ability {
    private static String DEFAULT_ANSWER = "很抱歉，不知道您的意思";

    @Override
    public String process(String input) {
        return DEFAULT_ANSWER;
    }
}
