package org.nerdboy.chatbot.utils;

import org.junit.Test;
import org.nerdboy.chatbot.storage.Data;
import org.nerdboy.chatbot.storage.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xsank.mz on 2016/5/20.
 */
public class DataUtilTest {

    private static final String DATA_PATH = "data/common/Knowledge.json";

    @Test
    public void testLoadData() throws Exception {
        DataUtil.loadData(DATA_PATH, Data.class);
    }

    @Test
    public void testSaveData() {
        Data data = new Data();
        List<Pair> records = new ArrayList<Pair>();
        records.add(new Pair("hi", "hello"));
        records.add(new Pair("haha", "hehe"));
        data.setRecords(records);
        DataUtil.saveData(DATA_PATH, data);
    }
}