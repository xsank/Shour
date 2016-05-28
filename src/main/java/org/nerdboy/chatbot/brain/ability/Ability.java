package org.nerdboy.chatbot.brain.ability;

/**
 * Created by xsank.mz on 2016/5/18.
 */
public abstract class Ability {
    public String name() {
        return getClass().getSimpleName();
    }

    public abstract String process(String input);
}
