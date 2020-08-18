package com.knight.iot.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        //要连接的服务器IP地址和端口
        String host = "127.0.0.1";
        //客户端建议不要绑定端口
        int port = 55533;
        //与服务器端建立连接
        Socket socket = new Socket(host, port);
        //建立连接后获得输出流
        OutputStream outputStream = socket.getOutputStream();
        String message = "miss you";
        outputStream.write(message.getBytes("UTF-8"));
        //通过shutdownOutput高速服务器已经发送完数据，后面只能接收数据
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        StringBuffer sb = new StringBuffer();
        while ((len = inputStream.read(bytes)) != -1){
            sb.append(new String(bytes,0,len,"UTF-8"));
        }
        System.out.println("get message from server: " + sb);
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
