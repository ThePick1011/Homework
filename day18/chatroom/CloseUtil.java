package org.xupt.chatroom;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭流
 */
public class CloseUtil {
    public static void closeAll(Closeable... io) {
        for (Closeable temp : io) {
            try {
                if (null != temp)
                    temp.close();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }

}

