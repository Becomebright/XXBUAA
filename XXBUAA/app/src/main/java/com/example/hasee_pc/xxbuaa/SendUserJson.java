package com.example.hasee_pc.xxbuaa;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;




public class SendUserJson implements Runnable {
    private String username;
    private String password;
    public String content = null;
    public List<Grade> gradeList = new ArrayList<Grade>();
    public SendUserJson(){}
    public SendUserJson(String account, String password){
        username = account;
        this.password = password;
    }
    public List<Grade> Explain(String grade){
        content = grade;
        parseJSONWithJSONObject(content);
        return  gradeList;
    }
    @Override
    public void run() {
        HttpURLConnection connection=null;
        try{
            //http://10.18.117.72:5000/login http://10.19.116.193:5000/login
            URL url=new URL(MainActivity.ip+"/login");
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(12000);
            connection.setReadTimeout(12000);
            connection.setDoOutput(true);
            //post请求的参数
            User u = new User(username, password);

            String data = JSON.toJSONString(u);

            //String data = "{\"username\":\"h1047741619\",\"password\":\"hhx980315\"}";

            OutputStream out=connection.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            connection.connect();

            InputStream in=connection.getInputStream();
            //下面对获取到的输入流进行读取
            BufferedReader bufr=new BufferedReader(new InputStreamReader(in));
            StringBuilder response=new StringBuilder();
            String line=null;

            try {
                while((line=bufr.readLine())!=null){
                    response.append(line);
                }
                content = EncodingUtils.getString(response.toString().getBytes(), "UTF-8");

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Log.d("Hello", content);
            parseJSONWithJSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(connection!=null){
                connection.disconnect();
            }
        }
    }
    private  void parseJSONWithJSONObject(String jsonData){
        try{
            MainActivity.user = JSON.parseObject(jsonData, User.class);
            Log.d("Hello", MainActivity.user.getAcademy());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

