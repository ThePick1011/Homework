package org.xupt.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Client {
    public static void main(String[] args) throws IOException {
        //1.创建客户端，并指定服务器和端口
        Socket client = new Socket("localhost", 6066);
        System.out.println("请输入名称:");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        if(name.equals("")){
           return;
        }
        System.out.println("群聊直接发送");
        System.out.println("私聊请@+“message”+:");
        System.out.println("$$查看当前在线用户");
        //2.发送数据
        //输出流
        new Thread(new Send(client,name)).start();
        new Thread(new Receive(client)).start();


    }
}