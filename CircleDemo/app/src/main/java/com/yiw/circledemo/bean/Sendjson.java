package com.yiw.circledemo.bean;

import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

//import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;
//import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by hasee-pc on 2018/03/07.
 */

public class Sendjson implements Runnable {
    private String url;
    private User params;
    public List<CircleJson>  gradeList;
    public Sendjson(String url, User params) {
        this.url = url;
        this.params = params;
    }

    public List<CircleJson> jsonPost(String strURL,User params) {
        try {
            HttpURLConnection connection=null;
            URL url=new URL(strURL);
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            connection.setRequestMethod("POST");
//            connection.setConnectTimeout(10000);
//            connection.setReadTimeout(8000);
            connection.setDoOutput(true);

            //post请求的参数
            UserJson u=new UserJson(params);
            String data = JSON.toJSONString(u);
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
                 gradeList = JSON.parseArray(content, CircleJson.class);
//            Lesson lesson = lessonList.get(0);
//            Log.e("MainActivity", "" + lesson.getName());
                for(CircleJson grade : gradeList){
                    Log.e("MainActivity", grade.getCreateTime() + " " + grade.getContent() + " "  + "\n");
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        } catch (IOException e) {
            //LOG.error("Exception occur when send http post request!", e);
            e.printStackTrace();
            Log.d("123","连接报错");
        }
        return gradeList;
        //return "error"; // 自定义错误信息
    }

    @Override
    public void run() {
        jsonPost(url,params);
    }
}
