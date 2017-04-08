package org.nerdboy.chatbot.brain;

import org.nerdboy.chatbot.brain.ability.Ability;
import org.nerdboy.chatbot.brain.lang.Chinese;
import org.nerdboy.chatbot.brain.lang.Language;
import org.nerdboy.chatbot.process.Handler;
import org.nerdboy.chatbot.process.HandlerChain;
import org.nerdboy.chatbot.process.Request;
import org.nerdboy.chatbot.process.Response;
import org.nerdboy.chatbot.utils.ClassUtil;


/**
 * Created by xsank.mz on 2016/5/18.
 */
public class Brain {
    private static final String BASE_PATH = "org.nerdboy.chatbot.";
    private static final String BRAIN_PATH = BASE_PATH + "brain.";
    private static final String HANDLER_PATH = BASE_PATH + "process.";
    private static final String LANGUAGE_PATH = BRAIN_PATH + "lang.";
    private static final String ABILITY_PATH = BRAIN_PATH + "ability.";

    private Center<Ability> center;
    private Language language;

    private HandlerChain handlerChain;

    public Brain() {
        this.language = new Chinese();

        this.center = new Center<Ability>();
        this.handlerChain = new HandlerChain();
    }

    public void initAbilities(String strAbility) {
        String[] abilities = strAbility.split(",");
        for (String sability : abilities) {
            Ability ability = (Ability) ClassUtil.getObject(ABILITY_PATH + sability);
            trainAbility(ability);
        }
    }

    public void initHandler(String strHandler) {
        String[] handlers = strHandler.split(",");
        for (String sHandler : handlers) {
            Handler handler = (Handler) ClassUtil.getObject(HANDLER_PATH + sHandler);
            addHandler(handler);
        }
    }

    public void addHandler(Handler handler) {
        this.handlerChain.addHanlder(handler);
    }

    public void learn(String question, String answer) {
        center.learn(question, answer);
    }

    public String response(String request) {
        String result = process(request);
        return result;
    }

    private String process(String input) {
        Request request = new Request(input);
        Response response = new Response();
        if (handlerChain.ready(request, response)) {
            response.setResult(center.process(request.getWords()));
        }
        return response.getResult();
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void trainAbility(Ability ability) {
        center.train(ability);
    }

    public void die() {
        this.center.destroy();
        this.handlerChain.clear();
    }
}
