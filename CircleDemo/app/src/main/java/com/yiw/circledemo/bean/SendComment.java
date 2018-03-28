package com.yiw.circledemo.bean;

import android.util.Log;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hasee-pc on 2018/03/21.
 */

public class SendComment implements Runnable {
    private int user_id;
    private int post_id;
    private int toReplyUser_id;
    private int comment_id;
    private String content;
    private int flag;//1为发表,2为删除

    public SendComment(int user_id, int post_id, int toReplyUser_id, int comment_id, String content, int flag) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.toReplyUser_id = toReplyUser_id;
        this.comment_id = comment_id;
        this.content = content;
        this.flag = flag;
    }

    public int jsonPost() {
        try {
            HttpURLConnection connection=null;
            URL url;
            if(flag==1){
                url=new URL("http://123.206.27.121:5000/post/add_comment");
            }
            else{
                url=new URL("http://123.206.27.121:5000/post/delete_comment");
            }
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            connection.setRequestMethod("POST");
//            connection.setConnectTimeout(10000);
//            connection.setReadTimeout(8000);
            connection.setDoOutput(true);

            //post请求的参数
            String data;
            if(flag==1){
                data= "{\"user_id\":\""+user_id+"\",\"post_id\":\"" + post_id + "\",\"toReplyUser_id\":\""+toReplyUser_id+"\",\"content\":\""+content+"\"}";
            }
            else{
                data= "{\"user_id\":\""+user_id+"\",\"post_id\":\"" + post_id + "\",\"comment_id\":\""+comment_id+"\"}";
            }

            //JSON.toJSONString(post_id);

            Log.d("123",data);
            //String data = "{\"username\":\"h1047741619\",\"password\":\"hhx980315\"}";
            Log.d("123","链接之前");
            connection.connect();
            Log.d("123","连接之后");
            OutputStream out=connection.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();



            InputStream in=connection.getInputStream();
            //下面对获取到的输入流进行读取
            BufferedReader bufr=new BufferedReader(new InputStreamReader(in));
            StringBuilder response=new StringBuilder();
            String line=null;
            String content = null;
            try {
                while((line=bufr.readLine())!=null){
                    response.append(line+"\n");
                }
                content = EncodingUtils.getString(response.toString().getBytes(), "UTF-8");

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Log.d("123",content);

            try{
//                gradeList = JSON.parseArray(content, CircleJson.class);
//            Lesson lesson = lessonList.get(0);
//            Log.e("MainActivity", "" + lesson.getName());
//                for(CircleJson grade : gradeList){
//                    Log.e("MainActivity", grade.getCreateTime() + " " + grade.getContent() + " "  + "\n");
//                }

            }catch (Exception e){
                e.printStackTrace();
            }

        } catch (IOException e) {
            //LOG.error("Exception occur when send http post request!", e);
            e.printStackTrace();
            Log.d("123","连接报错");
        }
        return 1;
        //return "error"; // 自定义错误信息
    }
    @Override
    public void run() {
        jsonPost();
    }
}
