package top.szzz666;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import static top.szzz666.WebServer.formatTime;


///**
// * 从指定的URL获取JSON数据。
// *
// * @param url 请求的URL地址
// * @return 从URL返回的JSON字符串
// * @throws IOException 如果打开连接失败或者读取输入流时发生错误
// */
public class GetJson {
    public static String getjson(String url) throws IOException {
        // 创建URL对象并打开连接
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // 设置请求方式为GET
        con.setRequestMethod("GET");

        // 发送GET请求并获取响应码
        int responseCode = con.getResponseCode();
        System.out.println(formatTime() + "向 URL 发送 GET 请求：" + url);
        System.out.println(formatTime() + "响应代码：" + responseCode);

        // 读取返回的JSON数据
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        // 将输入流中的数据逐行读取并存入StringBuilder中
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        System.out.println(formatTime() + "获得返回数据：");
        in.close();

        String json = response.toString();

        //逗号分割为数组并打印每个元素
            String[] jsonArray = json.split(",");
            for (int i = 0; i < jsonArray.length; i++) {
                if (i != jsonArray.length - 1) {
                    System.out.println(jsonArray[i] + ",");
                } else {
                    System.out.println(jsonArray[i]);
                }
            }

        return json;
    }
}

