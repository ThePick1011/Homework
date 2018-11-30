package org.xupt.chatroom;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *接收数据
 */

public class Receive implements Runnable{
    //管道输入流
    private DataInputStream dis;
    //线程标识
    private boolean isRunning = true;
    //初始化

    public Receive(Socket client) {
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
           isRunning = false;
           CloseUtil.closeAll(dis);
        }
    }
    //接收数据
    public String receive(){
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(dis);
        }
        return msg;
    }
    //线程体
    @Override
    public void run() {
        while (isRunning){
            String msg = receive();
            System.out.println(msg);
        }
    }
}
