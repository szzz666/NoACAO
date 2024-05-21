package top.szzz666;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class InitConfig {
    static int port = 8662;
    static boolean useSaveLogs = false;
//    static String File = "src/main/resources/";
    static String File = "";

    public static void getconfig() {

        HashMap<String, String> data;
        Yaml yaml = new Yaml();
        try {
            // 读取 YAML 文件
            FileInputStream fis = new FileInputStream(File + "config/config.yml");
            data = yaml.load(fis);
            port = Integer.parseInt(data.get("WebServerPort"));
            useSaveLogs = Boolean.parseBoolean(data.get("UseSaveLogs"));

        } catch (FileNotFoundException e) {
            // 处理文件未找到异常
            System.err.println("[错误]没有找到配置文件：config.yml");
        }

    }
}