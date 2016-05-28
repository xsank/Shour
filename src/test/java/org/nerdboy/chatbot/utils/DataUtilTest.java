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

    @Test
    public void testLoadData() throws Exception {
        DataUtil.loadData("data/common/Knowledge.json", Data.class);
    }

    @Test
    public void testSaveData() {
        Data data = new Data();
        List<Pair> records = new ArrayList<Pair>();
        records.add(new Pair("hi", "hello"));
        records.add(new Pair("haha", "hehe"));
        data.setRecords(records);
        DataUtil.saveData("data/common/Extra.json", data);
    }
}