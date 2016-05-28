package org.nerdboy.chatbot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by xsank.mz on 2016/5/20.
 */
public class DataUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T loadData(String path, Class<T> clazz) {
        T data = null;
        try {
            data = mapper.readValue(new File(getResourcePath(path)), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static <K, V> Map<K, V> loadMap(String path) throws IOException {
        return mapper.readValue(new File(getResourcePath(path)), new TypeReference<Map<String, String>>() {
        });
    }

    public static <T> void saveData(String path, T t) {
        String resourcePath = getResourcePath(path);
        File file = new File(resourcePath);
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(mapper.writeValueAsString(t));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getResourcePath(String path) {
        return DataUtil.class.getClassLoader().getResource(path).getFile();
    }

    public static boolean checkFile(String filename) {
        return filename.endsWith(".json");
    }


    public static String objectToJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

    public static Properties loadProperty(String property) {
        Properties properties = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(property);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}