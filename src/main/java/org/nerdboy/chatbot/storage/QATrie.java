package org.nerdboy.chatbot.storage;

import org.nerdboy.chatbot.nlp.CoreNLP;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xsank.mz on 2016/5/20.
 */
public class QATrie {
    private static final String ANSWER = "answer";

    private class TrieNode {
        private Map<String, TrieNode> sons;
        private boolean isFinal;
        private boolean isAnswer;
        private String value;

        public TrieNode(String value) {
            this.value = value;
            this.isFinal = false;
            this.isAnswer = false;
            this.sons = new HashMap<String, TrieNode>();
        }

        public boolean isAnswer() {
            return isAnswer;
        }
    }

    private TrieNode root;

    public QATrie() {
        root = new TrieNode("root");
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String question, String answer) {
        List<String> words = CoreNLP.filterWords(question);
        insert(words, answer);
    }

    public void insert(List<String> words, String answer) {
        if (words != null && words.size() >= 0) {
            TrieNode node = root;
            for (String word : words) {
                if (!node.sons.containsKey(word)) {
                    node.sons.put(word, new TrieNode(word));
                }
                node = node.sons.get(word);
            }
            node.isFinal = true;
            node.sons.put(ANSWER, new TrieNode(answer));
            node = node.sons.get(ANSWER);
            node.isAnswer = true;
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
            if (flag && node.isFinal) {
                result = node.sons.get(ANSWER).value;
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
