package org.nerdboy.chatbot.brain;

import org.nerdboy.chatbot.brain.ability.Ability;
import org.nerdboy.chatbot.brain.ability.None;
import org.nerdboy.chatbot.storage.Memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xsank.mz on 2016/5/19.
 */
public class Center<T extends Ability> {

    private Memory memory;
    private Map<String, T> moduleMap;
    private Map<String, String> clazzMap;


    public class DefaultMap<K, V> extends HashMap<K, V> {
        private V defaultValue;

        public DefaultMap(V defaultValue) {
            this.defaultValue = defaultValue;
        }

        @Override
        public V get(Object key) {
            V value = super.get(key);
            return value == null ? defaultValue : value;
        }
    }

    public Center() {
        memory = new Memory();
        moduleMap = new DefaultMap(new None());

        initClazzMap();
    }

    private void initClazzMap() {
        clazzMap = new HashMap<String, String>();
        clazzMap.put("天气", "Weather");
        clazzMap.put("时间", "Datetime");
    }

    public void train(T t) {
        moduleMap.put(t.name(), t);
    }

    public int size() {
        return moduleMap.size();
    }

    public void learn(String question, String answer) {
        memory.memorize(question, answer);
    }

    public String process(List<String> words) {
        String result = memory.search(words);
        if (result.equals("")) {
            String module = "", input = "";
            for (String word : words) {
                System.out.println(">" + word);
                if (clazzMap.containsKey(word)) {
                    module = classify(word);
                    StringBuilder sb = new StringBuilder();
                    for (String s : words) {
                        sb.append(s);
                    }
                    input = sb.toString();
                    break;
                }
            }
            result = dispatch(module, input);
        }
        return result;
    }

    public String classify(String input) {
        return clazzMap.get(input);
    }

    public String dispatch(String name, String input) {
        return moduleMap.get(name).process(input);
    }

    public void destroy() {
        moduleMap.clear();
    }
}
