package com.dom.nioaio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * 模仿阻塞的客户端,模拟低效的客户端
 */
public class EchoClient_2 {

    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private static final int sleepTime = 1000 * 1000*1000;

    public static void main(String[] args) {
        WorkThread workThread = new WorkThread();

        for (int i = 0; i < 10; i++) {
            executorService.execute(workThread);
        }
    }

    public static class WorkThread implements Runnable {

        public void run() {
            Socket client = null;
            PrintWriter writer = null;
            BufferedReader reader = null;
            try {
                client = new Socket();
                client.connect(new InetSocketAddress("localhost", 8000));
                writer = new PrintWriter(client.getOutputStream(), true);
                writer.print("h");
                LockSupport.parkNanos(sleepTime);
                writer.print("e");
                LockSupport.parkNanos(sleepTime);
                writer.print("l");
                LockSupport.parkNanos(sleepTime);
                writer.print("l");
                LockSupport.parkNanos(sleepTime);
                writer.print("o");
                LockSupport.parkNanos(sleepTime);
                writer.print("!");
                LockSupport.parkNanos(sleepTime);
                writer.println();
                writer.flush();
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.println("from server:" + reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                    if (reader != null) {
                        reader.close();
                    }
                    if (client != null) {
                        client.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
