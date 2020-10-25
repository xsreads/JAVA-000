package io.xsreads.java.nio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Java 实现一个最简的 HTTP 服务器-01
 * Created by xiushan on 2020/10/25.
 */
public class HttpServer01 {
    public static void main(String[] args) throws IOException {
        /**
         * 1. 创建一个 ServerSocket
         * 2. 绑定 8801 端口
         */
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            try {
                // 3. 当有客户端请求时通过 accept 方法拿到 Socket，进而可以进行处理
                Socket socket = serverSocket.accept();
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
        try {
            // 4. sleep 20ms，模拟业务操作(IO)
            Thread.sleep(20);
            // 5. 模拟输出 HTTP 报文头和 hello
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.println("hello,nio");
            printWriter.close();
            // 6. 关闭 socket
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
