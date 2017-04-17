package org.nerdboy.chatbot.storage;

import org.nerdboy.chatbot.nlp.CoreNLP;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by xsank.mz on 2016/5/20.
 */
public class QATrie {
    private static Random random = new Random();


    enum Type {
        Path,
        End,
        Answer
    }


    private class TrieNode {

        private Map<String, TrieNode> sons;
        private Type type;
        private String value;

        public TrieNode(String value) {
            this(value, Type.Path);
        }

        public TrieNode(String value, Type type) {
            this.value = value;
            this.type = type;
            this.sons = new HashMap<>();
        }

        public boolean hit() {
            return this.type == Type.End;
        }

        public int answerSize() {
            if (hit()) {
                return sons.size();
            }
            return 0;
        }

        public String chooseAnswer() {
            String answer = "";
            if (hit()) {
                int index = random.nextInt(sons.size());
                answer = sons.get(String.valueOf(index)).value;
            }
            return answer;
        }
    }


    private TrieNode root;

    public QATrie() {
        root = new TrieNode("root");
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String question, String... answers) {
        List<String> words = CoreNLP.filterWords(question);
        insert(words, answers);
    }

    private void insert(List<String> words, String... answers) {
        if (words != null && words.size() >= 0) {
            TrieNode node = root;
            for (String word : words) {
                if (!node.sons.containsKey(word)) {
                    node.sons.put(word, new TrieNode(word));
                }
                node = node.sons.get(word);
            }
            node.type = Type.End;
            int start = node.answerSize();
            int end = start + answers.length;
            for (int i = start; i < end; i++) {
                node.sons.put(String.valueOf(i), new TrieNode(answers[i-start], Type.Answer));
            }
        }
    }

    public String search(String sentence) {
        List<String> words = CoreNLP.filterWords(sentence);
        return search(words);
    }

    public String search(List<String> words) {
        String result = "";
        boolean flag = true;
        if (words != null && words.size() >= 0) {
            TrieNode node = root;
            for (String word : words) {
                if (!node.sons.containsKey(word)) {
                    node.sons.put(word, new TrieNode(word));
                    flag = false;
                } else {
                    node = node.sons.get(word);
                }
            }
            if (flag && node.hit()) {
                result = node.chooseAnswer();
            }
        }
        return result;
    }

    public void preTraverse(TrieNode node, int indent) {
        indent++;
        if (node != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < indent; i++) {
                sb.append("-");
            }
            System.out.println(sb.toString() + node.value);
            for (TrieNode child : node.sons.values()) {
                preTraverse(child, indent);
            }
        }
        indent--;
    }
}
