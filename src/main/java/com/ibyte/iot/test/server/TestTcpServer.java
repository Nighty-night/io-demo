package com.ibyte.iot.test.server;

import com.ibyte.iot.tcp.connector.tcp.server.TcpServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestTcpServer {

    /**
     * 程序启动 Server
     * @param args
     */
    public static void main(String[] args) {
//        ApplicationContext context = new FileSystemXmlApplicationContext(new String[]{"classpath:spring-config.xml"});
//        context.getApplicationName();
        TcpServer server = new TcpServer();
        try {
            server.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
