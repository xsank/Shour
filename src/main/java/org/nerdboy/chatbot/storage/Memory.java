package org.nerdboy.chatbot.storage;

import org.nerdboy.chatbot.utils.DataUtil;

import java.util.List;

/**
 * Created by xsank.mz on 2016/5/19.
 */
public class Memory {
    private final String DATA_PATH = "data/common/Knowledge.json";

    private Record record;
    private StringBuilder context;
    private QATrie core;
    private Disk disk;

    public Memory() {
        this.record = new Record();
        this.context = new StringBuilder();
        this.disk = new Disk();

        initCore();
    }

    private void initCore() {
        core = new QATrie();
        Data data = DataUtil.loadData(DATA_PATH, Data.class);
        List<Pair> records = data.getRecords();
        for (Pair pair : records) {
            core.insert(pair.getQuestion(), pair.getAnswers());
        }
        core.preTraverse(core.getRoot(), 0);
    }

    public String search(String request) {
        return core.search(request);
    }

    public String search(List<String> words) {
        return core.search(words);
    }

    public void memorize(String question, String answer) {
        core.insert(question, answer);
    }

    public void add(String req, String res) {
        Pair pair = new Pair(req, res);
        this.record.add(pair);
    }

    public void context() {
        String context = record.toContext();
    }

    public void flush() {
        this.record.clear();
        this.disk.save();
    }
}
