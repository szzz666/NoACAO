package top.szzz666;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import static top.szzz666.GetJson.getjson;
import static top.szzz666.InitConfig.getconfig;



public class WebServer {

    public static void main(String[] args) throws IOException {
        getconfig();
        if (InitConfig.useSaveLogs) {
            new LogToFile();
        }
        int serverPort = InitConfig.port; // 设置服务器端口号
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);

        // 设置请求处理程序
        server.createContext("/", new MotdHandler());

        // 启动服务器
        server.start();
        System.out.println("开发者QQ：3214948198（初学者写的烂，请不要喷）");
        System.out.println("开发者邮箱：3214948198@qq.com");
        System.out.println(formatTime() + "服务已经启动，访问地址：");
        System.out.println(formatTime() + "http://localhost:" + serverPort + "/?链接");
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public static String formatTime() {
        LocalDateTime now = LocalDateTime.now();

        // 创建DateTimeFormatter，指定日期/时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[MM/dd/hh:mm:ss]");

        // 格式化当前日期时间
        return "[" + now.format(formatter) + "]";

    }


    static class MotdHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // 获取请求方法
            String requestMethod = exchange.getRequestMethod();

            /// 获取客户端IP
            InetSocketAddress clientAddress = exchange.getRemoteAddress();
            String clientIp = clientAddress.getAddress().getHostAddress();

            if (requestMethod.equalsIgnoreCase("GET")) {
                System.out.println(formatTime() + "接到来自" + clientIp + "请求");
                // 获取查询参数
                String query = exchange.getRequestURI().getQuery();
                System.out.println(formatTime() + "获得请求参数：" + query);

                String Json;
                // 处理MOTD并生成JSON
                Json = getjson(query);

                exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(200, Json.getBytes().length);
                // 发送响应
                OutputStream responseBody = exchange.getResponseBody();

                responseBody.write(Json.getBytes());
                responseBody.close();
                System.out.println(formatTime() + "已经返回JSON");

            } else {
                // 不支持的请求方法
                exchange.sendResponseHeaders(405, 0);
                System.out.println(formatTime() + "不支持的请求");

            }
            System.out.println("-----------------------------------------------------------------------------------");
        }

    }
}