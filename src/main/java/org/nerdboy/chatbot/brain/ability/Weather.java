package org.nerdboy.chatbot.brain.ability;

import org.nerdboy.chatbot.utils.DataUtil;
import org.nerdboy.chatbot.utils.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xsank.mz on 2016/5/23.
 */
public class Weather extends Ability {
    private static final String WEATHER_CIPY_DATA_PATH = "data/weather/city.json";
    private static final String WEATHER_API = "http://www.weather.com.cn/data/sk/%s.html";
    private static final String PROVINCE_LIST_API = "http://m.weather.com.cn/data5/city.xml";
    private static final String PROVINCE_DETAIL_API = "http://m.weather.com.cn/data5/city%s.xml";
    private static final String TRIM_WORD = "天气";

    private static Map<String, String> cityCodeMap = new HashMap<String, String>();

    static {
        initCityDict();
    }

    static class WeatherData {
        static class WeatherInfo {
            public String cityid;
            public String city;
            public String temp;
            public String WD;
            public String WS;
            public String SD;
            public String WSE;
            public String time;
            public String isRadar;
            public String Radar;
            public String njd;
            public String qy;
            public String rain;

            @Override
            public String toString() {
                return String.format("天气:%s,温度%s:,湿度:%s", WD, temp, SD);
            }
        }

        public WeatherInfo weatherinfo;

        public WeatherData() {
        }

        @Override
        public String toString() {
            return weatherinfo.toString();
        }
    }

    private static void initCityDict() {
        try {
            cityCodeMap = DataUtil.loadMap(WEATHER_CIPY_DATA_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String process(String input) {
        String city = input.replaceFirst(TRIM_WORD, "");
        String result = "没查出来";
        if (cityCodeMap.containsKey(city)) {
            String code = cityCodeMap.get(city);
            String weatherUrl = String.format(WEATHER_API, code);
            try {
                String weather = HttpUtil.get(weatherUrl);
                if (weather != null && !weather.equals("")) {
                    WeatherData weatherData = DataUtil.jsonToObject(weather, WeatherData.class);
                    result = weatherData.toString();
                }
            } catch (IOException e) {
                result = "妈蛋，天气预报接口不让查";
            }
        }
        return String.format("%s:%s", city, result);
    }
}
