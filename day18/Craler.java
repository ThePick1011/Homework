package org.xupt.craler;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Craler {
    static int i = 0;
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input num(Before the num - page): ");
        int num = scanner.nextInt();

        for (int page = 0; page <= num; page++) {
            List<String> imgUrl = null;
            if (page == 1) {
                imgUrl = getImageUrl(getHtml("https://tieba.baidu.com/p/2256306796?pn="));//First page is different
            } else {
                imgUrl = getImageUrl(getHtml("https://tieba.baidu.com/p/2256306796?pn=") + page);
            }
//            for (String s : imgUrl) {
//                System.out.println(s);
//            }
            List<String> imageSrc = getImageSrc(imgUrl);
//            for (String s : imageSrc) {
//                System.out.println(s);
//
//            }
            downLoadImg(imageSrc);
        }

    }

    private static void downLoadImg(List<String> imgUrl) throws IOException {

        for (String s : imgUrl) {
            System.out.println("Downloading " + s);

            HttpURLConnection connection = null;
            FileOutputStream image = null;
            try {
                connection = (HttpURLConnection)
                        new URL(s).openConnection();
                i++;
                InputStream in = connection.getInputStream();

                image = new FileOutputStream("d:\\img\\" + i + ".jpg");

                while (true) {
                    byte[] buf = new byte[1024 * 8];
                    int len = in.read(buf);
                    if (len == -1) {
                        break;
                    }
                    image.write(buf, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("ok_____________");

        }

    }

    private static String getHtml(String url) throws IOException {
        System.out.println("Getting Url..........");
        URL url1 = new URL(url);
        URLConnection connection = url1.openConnection();
        InputStream in = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);

        String line;
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
            sb.append(line, 0, line.length());

        }
        br.close();
        isr.close();
        in.close();
        return sb.toString();
    }


    private static List<String> getImageUrl(String html) {

        Matcher matcher = Pattern.compile("<img class=\"BDE_Image\" src=(.*?)[^>]*?>").matcher(html);
        List<String> listimgurl = new ArrayList<String>();
        while (matcher.find()) {
            listimgurl.add(matcher.group());

        }
        return listimgurl;
    }

    private static List<String> getImageSrc(List<String> listimageurl) {
        List<String> listImageSrc = new ArrayList<String>();
        for (String image : listimageurl) {
            Matcher matcher = Pattern.compile("[a-zA-z]+://[^\\s]*").matcher(image);
            while (matcher.find()) {
                listImageSrc.add(matcher.group().substring(0, matcher.group().length() - 1));

            }
        }
        return listImageSrc;
    }
}

//<img class="BDE_Image" src="https://imgsa.baidu.com/forum/w%3D580/sign=0ce4f547738b4710ce2ffdc4f3cfc3b2/97ed8a13632762d04146a9d9a1ec08fa513dc659.jpg" width="560" height="314" pic_type="0">
//<img class="BDE_Image" src="https://imgsa.baidu.com/forum/w%3D580/sign=cb8b178ba2cc7cd9fa2d34d109012104/88fc5266d0160924cc3cae7dd50735fae6cd34bf.jpg" width="560" height="314" pic_type="0">
//<img pic_type="0" class="BDE_Image" src="https://imgsa.baidu.com/forum/w%3D580/sign=f49447ded53f8794d3ff4826e21a0ead/189659ee3d6d55fbd42ad2406c224f4a21a4dded.jpg" height="350" width="560">
//<img pic_type="0" class="BDE_Image" src="https://imgsa.baidu.com/forum/w%3D580/sign=2164257408f79052ef1f47363cf2d738/f51249540923dd54578ef10fd009b3de9d8248dc.jpg" height="350" width="560">
//<img class="BDE_Image" src=(.*?)[^>]*?>
//https://tieba.baidu.com/p/2256306796?pn=1
//https://tieba.baidu.com/p/2256306796?pn=2
