package org.xupt.chatroom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.创建服务器，指定端口
 * 2.监听等待连接,等待客户端
 * 3.发送+接收数据
 * 4.接收多个客户端
 */

public class Server {
    private List<MyChannel> all = new ArrayList<MyChannel>();//存储所有客户端

    public static void main(String[] args) throws IOException {
        new Server().start();
    }


    public void start() throws IOException {
        //1.创建服务器，指定端口
        ServerSocket server = new ServerSocket(6066);
        //2.监听等待连接,等待客户端
        System.out.println("服务器启动等待连接");
        while (true) {
            Socket client = server.accept();

            MyChannel channel = new MyChannel(client);

            all.add(channel);
            new Thread(channel).start();
        }
    }

    /**
     * 一个客户端段对应一个线程
     * 每个客户端都有对应的输入输出流
     */
    private class MyChannel implements Runnable {
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean isRunning = true;
        private String name;

        public MyChannel(Socket client) {
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                this.name = dis.readUTF();
                this.send("欢迎" + this.name + "进入聊天室");
                System.out.println(this.name + "进入聊天室");
                sendOthers(this.name+ "进入聊天室",true);
            } catch (IOException e) {
                CloseUtil.closeAll(dis, dos);
                isRunning = false;
                all.remove(this);
            }

        }

        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                CloseUtil.closeAll(dis);
                isRunning = false;
                all.remove(this);
                sendOthers(this.name + "离开聊天室",true);
            }
            return msg;
        }

        private void send(String msg) {
            if (msg == null || msg.equals("")) {
                return;
            }
            try {
                dos.writeUTF(msg);
                dos.flush();

            } catch (IOException e) {
                CloseUtil.closeAll(dos);
                isRunning = false;
                sendOthers(this.name + "离开聊天室",true);
            }

        }

        /**
         * 群发
         * 遍历容器
         */
        private void sendOthers(String msg,boolean sys) {
            //判断查看
            if(msg.equals("$$")) {
                send(getListName(all));
                System.out.println(msg);
            } else if (msg.startsWith("@") && msg.indexOf(":") > -1) {     //判断私聊
                //获取name
                System.out.println(this.name+": "+msg);
                String name = msg.substring(1, msg.indexOf(":"));
                String content = msg.substring(msg.indexOf(":") + 1);
                for (MyChannel other : all) {
                    if (other.name.equals(name)) {
                        other.send(this.name + ": " + content);
                    }
                }
            } else {
                for (MyChannel other : all) {
                    if (other == this) {
                        continue;
                    }
                    if(sys){
                       other.send("系统消息" + ": " + msg);
                    }else {
                        other.send(this.name + ": " + msg);
                        System.out.println(this.name + ": " + msg);
                    }
                }
            }
        }


        //得到名称
        private String getListName(List<MyChannel> all) {
            StringBuffer name = new StringBuffer();
                for (MyChannel other : all) {
                    name.append(other.name).append(" ");
                }
                return name.toString();
        }

        @Override
        public void run() {
            while (isRunning) {
                sendOthers(receive(),false);
            }
        }
    }

}



