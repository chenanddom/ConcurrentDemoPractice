package com.dom.nioaio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

/**
 * 文件映射到内存
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("E:\\test.txt","rw");
        FileChannel fc = raf.getChannel();
        //将文件映射到内存当中
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE,0,raf.length());
        while (mbb.hasRemaining()){
            System.out.println(mbb.get());
        }
        //修改文件
        mbb.put(0,(byte) 65);
        raf.close();
    }
}
