package org.nerdboy.chatbot;

import org.nerdboy.chatbot.brain.Brain;
import org.nerdboy.chatbot.server.QAServer;
import org.nerdboy.chatbot.utils.DataUtil;

import java.util.Properties;

/**
 * Created by xsank.mz on 2016/5/18.
 */
public class Shour {
    private static Brain brain;
    private static QAServer server;

    public Shour(String property) {
        Properties properties = DataUtil.loadProperty(property);
        initBrain(properties);
        initServer(properties);
    }

    public Shour(Properties properties) {
        initBrain(properties);
    }

    private void initBrain(Properties properties) {
        brain = new Brain();
        String strAbility = properties.getProperty("ability");
        String strHandler = properties.getProperty("handler");
        brain.initAbilities(strAbility);
        brain.initHandler(strHandler);
    }

    public static void learn(String question, String answer) {
        brain.learn(question, answer);
    }

    private void initServer(Properties properties) {
        String port = properties.getProperty("port");
        server = new QAServer(port);
    }

    public void serve() {
        server.start(this);
    }

    public String response(String request) {
        return brain.response(request);
    }

    public void die() {
        this.brain.die();
        this.server.destroy();
    }

    public static void main(String[] args) {
        final Shour shour = new Shour("Shour.properties");
        shour.serve();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                shour.die();
            }
        });
    }
}
