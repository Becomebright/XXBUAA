package com.yiw.circledemo.bean;


        import android.util.Log;

        import java.io.BufferedInputStream;
        import java.io.BufferedOutputStream;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.URLConnection;
        import java.util.Scanner;

public class PostImage implements Runnable{

    private String sendpath;
    private String backpath;

    public PostImage(String sendpath) {
        this.sendpath = sendpath;
    }

    public String getSendpath() {
        return sendpath;
    }

    public void setSendpath(String sendpath) {
        this.sendpath = sendpath;
    }

    public String getBackpath() {
        return backpath;
    }

    public void setBackpath(String backpath) {
        this.backpath = backpath;
    }

    public  void startPost() throws IOException {
        //这个地方根据自己的实际情况做更改，比如你自己的IP地址，以及你在tomcat中的工程部署
        //这里的地址要和web.xml当中的设置匹配
        String url = "http://123.206.27.121:8080/ReceivePhoto/Upload_image";

        String result = doPost(url);
        System.out.println(result);

    }

    public  String doPost(String urlString)
            throws IOException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        //try里面拿到输出流，输出端就是服务器端
        try (BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream())) {

            //我java代码是在Windows上运行的，图片路径就是下面这个
            InputStream is = new FileInputStream(sendpath);
            BufferedInputStream bis = new BufferedInputStream(is);

            byte[] buf= new byte[1024];
            int length = 0;
            length = bis.read(buf);
            while(length!=-1) {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bis.close();
            is.close();
            bos.close();
        }
        Log.d("hhhhhh", sendpath);
        //下面是服务器端如果有返回数据的话，做接收用的
        StringBuilder response = new StringBuilder();
        try (Scanner in = new Scanner(connection.getInputStream())) {
            while (in.hasNextLine()) {
                response.append(in.nextLine());
                //response.append("\n");
            }
        } catch (IOException e) {
            if (!(connection instanceof HttpURLConnection))
                throw e;
            InputStream err = ((HttpURLConnection) connection).getErrorStream();
            if (err == null)
                throw e;
            Scanner in = new Scanner(err);
            response.append(in.nextLine());
            response.append("\n");
            in.close();
        }
        this.backpath="http://123.206.27.121:8089/"+response.toString()+".jpg";
        Log.d("qqqqq",backpath);
        return response.toString();
    }

    @Override
    public void run() {
        try {
            startPost();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
