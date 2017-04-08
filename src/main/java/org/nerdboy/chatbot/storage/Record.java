package org.nerdboy.chatbot.storage;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xsank.mz on 2016/5/19.
 */
public class Record {

    private static final int DEFAULT_MAX_RECORD_SIZE = 100;

    private List<Pair> records;
    private int maxSize;

    public Record() {
        this(DEFAULT_MAX_RECORD_SIZE);
    }

    public Record(int size) {
        this.records = new LinkedList<Pair>();
        this.maxSize = size;
    }

    public void add(Pair pair) {
        if (records.size() >= this.maxSize) {
            records.remove(0);
        }
        records.add(pair);
    }

    public String toContext() {
        StringBuilder context = new StringBuilder();
        for (Pair pair : records) {
            context.append(pair.getQuestion());
            context.append(pair.getAnswers());
        }
        return context.toString();
    }

    public void clear() {
        this.records.clear();
        this.maxSize = 0;
    }
}
