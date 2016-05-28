package org.nerdboy.chatbot.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by xsank.mz on 2016/5/23.
 */
public class HttpUtil {
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static HttpClient buildClient() {
        return HttpClientBuilder.create().build();
    }

    private static HttpGet buildGet(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(url);
        if (params != null && params.size() > 0) {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry) iterator.next();
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            sb.append("?");
            sb.append(URLEncodedUtils.format(nameValuePairs, DEFAULT_CHARSET));
        }
        HttpGet httpGet = new HttpGet(sb.toString());
        httpGet.setHeader("Accept-Encoding", "");
        return httpGet;
    }

    private static HttpPost buildPost(String url, Map<String, String> params) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(HTTP.CONTENT_ENCODING, DEFAULT_CHARSET);
        HttpEntity entity;
        if (params != null) {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry) iterator.next();
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            entity = new UrlEncodedFormEntity(nameValuePairs, DEFAULT_CHARSET);
            httpPost.setEntity(entity);
        }
        return httpPost;
    }

    public static String get(String url) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        return get(url, params);
    }

    public static String get(String url, Map<String, String> params) throws IOException {
        return get(url, params, DEFAULT_CHARSET);
    }

    public static String get(String url, Map<String, String> params, String charset) throws IOException {
        HttpClient client = buildClient();
        HttpGet httpGet = buildGet(url, params);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String result = "";
        if (entity != null) {
            result = EntityUtils.toString(entity, charset);
        }
        return result;
    }

    public static String post(String url) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        return post(url, params);
    }

    public static String post(String url, Map<String, String> params) throws IOException {
        return post(url, params, DEFAULT_CHARSET);
    }

    public static String post(String url, Map<String, String> params, String charset) throws IOException {
        HttpClient client = buildClient();
        HttpPost httpPost = buildPost(url, params);
        HttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = "";
        if (entity != null) {
            result = EntityUtils.toString(entity, charset);
        }
        return result;
    }
}
