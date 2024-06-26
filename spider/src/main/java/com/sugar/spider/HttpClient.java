package com.sugar.spider;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * @Description http 连接客户端
 * @Author sugar
 * @Date 2019/6/19 4:31 PM
 * @Version 1.0
 */
public class HttpClient {


    public static String get(String pageUrl, String charSet) {

        try {
            URL url = new URL(pageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
//            设置头部参数
//            conn.setRequestProperty();

            conn.connect();
            if (conn.getResponseCode() == 404) {
                return null;
            }
            BufferedReader reader = null;
            if (conn.getHeaderField("Content-Encoding") != null && conn.getHeaderField("Content-Encoding").equals("gzip")) {
                reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream()), charSet));
            }else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charSet));

            }
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            conn.disconnect();
            return sb.toString();


        } catch (Exception e) {

            e.printStackTrace();
        }


        return null;
    }
}
