package org.xupt.chatroom;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 发送数据
 */

public class Send implements Runnable {
    //控制台输入流
    private BufferedReader console;
    //管道输出流
    private DataOutputStream dos;
    //线程标识
    private boolean isRunning = true;
    private String name;

    //初始化
    public Send() {
        console = new BufferedReader(new InputStreamReader(System.in));

    }

    public Send(Socket client,String name) {
        this();
        try {
            dos = new DataOutputStream(client.getOutputStream());
            this.name = name;
            send(this.name);
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(dos, console);
        }
    }

    //1.从控制台接收数据
    private String getMessageFromConsole() {
        try {
            return console.readLine();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return "";
    }

    /**
     * 1.从控制台接收数据
     * 2.发送数据
     */
    public void send(String msg) {
        try {
            if (msg != null && !msg.equals("")) {
                dos.writeUTF(msg);
                dos.flush();
            }

        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(dos, console);
        }
    }


    //线程体
    @Override
    public void run() {
        while (isRunning) {
            send(getMessageFromConsole());
        }

    }
}
