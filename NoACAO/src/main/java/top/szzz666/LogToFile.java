package top.szzz666;

import org.apache.commons.io.output.TeeOutputStream;

import java.io.*;
import java.util.Date;

public class LogToFile {

    private static final String LOG_FILE_PATH = InitConfig.File +"app.log"; // 日志文件路径

    // 添加公共方法以初始化日志系统
    public static void initLog() {
        try {
            // 创建一个新的PrintStream，将输出同时写入控制台和日志文件
            PrintStream consoleAndFile = new PrintStream(new FileOutputStream(LOG_FILE_PATH, true), true);
            consoleAndFile.println("日志开始于 " + new Date()); // 记录日志开始时间

            // 保存原始的System.out
            PrintStream originalOut = System.out;

            // 设置新的PrintStream，这样控制台输出会同时写入到日志文件
            System.setOut(new PrintStream(new TeeOutputStream(originalOut, consoleAndFile)));
        } catch (FileNotFoundException e) {
            System.err.println("初始化日志时出错: " + e.getMessage());
        }
    }

    // 移除私有构造器，添加公共构造器以实例化对象并初始化日志
    public LogToFile() {
        initLog();
    }
}
