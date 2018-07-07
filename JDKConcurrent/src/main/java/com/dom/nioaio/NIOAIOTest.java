package com.dom.nioaio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 实现NIO复制文件
 */
public class NIOAIOTest {


    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("E:\\tmp\\8.14.rar");
        FileOutputStream fos = new FileOutputStream("E:\\tmp\\test.rar");
        FileChannel readChannel = fis.getChannel();//读取文件通道
        FileChannel writeChannel = fos.getChannel();//写文件通道
        ByteBuffer buffer = ByteBuffer.allocate(1024);//读入数据缓存
        while (true) {
            buffer.clear();
            int len = readChannel.read(buffer);//写数据
            if (len == -1) {
                break;//读取完毕
            }
            buffer.flip();
            writeChannel.write(buffer);
            //写入文件
        }
        readChannel.close();
        writeChannel.close();
    }
}
