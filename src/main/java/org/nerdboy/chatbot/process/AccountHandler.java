package org.nerdboy.chatbot.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.nerdboy.chatbot.utils.DataUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xsank.mz on 2016/5/22.
 */
public class AccountHandler implements Handler {
    public static Map<String, Integer> stats = new HashMap<String, Integer>();

    public void process(Request request, Response response, HandlerChain chain) {
        for (String word : request.getWords()) {
            int count = 1;
            if (stats.containsKey(word)) {
                count = stats.get(word) + 1;

            }
            stats.put(word, count);
        }
        chain.handle(request, response);
    }

    public static String statistics() {
        String result = "";
        try {
            result = DataUtil.objectToJson(stats);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
