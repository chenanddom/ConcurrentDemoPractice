package com.dom.nioaio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
private static ExecutorService executorService = Executors.newFixedThreadPool(3);
    public static void main(String[] args) {
        ServerSocket echoServer = null;
        Socket clientSocket = null;
        try {
            echoServer = new ServerSocket(8000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                clientSocket = echoServer.accept();
                System.out.println(clientSocket.getRemoteSocketAddress() + " is connect");
                executorService.execute(new HandlerMsg(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static class  HandlerMsg  implements Runnable{
        private Socket clientSocket;
        private BufferedReader is=null;
        private PrintWriter os=null;
        public HandlerMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
               is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                os = new PrintWriter(clientSocket.getOutputStream(),true);
                //从InputStream当中读取客户端所发送的数据
                String inputLine = null;
                long b = System.currentTimeMillis();
                while ((inputLine=is.readLine())!=null){
                os.println(inputLine);
                }
                long e = System.currentTimeMillis();
                System.out.println("spend:"+(e-b)+"ms");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (os!=null){
                    os.close();
                }
                if (is!=null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        }
    }

}
