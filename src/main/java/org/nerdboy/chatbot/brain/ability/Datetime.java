package org.nerdboy.chatbot.brain.ability;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xsank.mz on 2016/5/23.
 */
public class Datetime extends Ability {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh点mm分ss秒");

    @Override
    public String process(String input) {
        Date date = new Date();
        return String.format("%s:%s", input, dateFormat.format(date));
    }
}
