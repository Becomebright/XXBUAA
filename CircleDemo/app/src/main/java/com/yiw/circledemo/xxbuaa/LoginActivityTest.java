package com.yiw.circledemo.xxbuaa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wang.avi.AVLoadingIndicatorView;
import com.yiw.circledemo.R;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivityTest extends AppCompatActivity {
    TextView signupLink;
    Button signin;
    private EditText accountEdit;
    private EditText passwordEdit;
    private TextView mTextMessage;
    private AVLoadingIndicatorView avi;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
    private String getback;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.logintest);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass = (CheckBox)findViewById(R.id.remember_pass);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        boolean isRemember = pref.getBoolean("remember_pass", true);
        if(isRemember){
            //将账号和密码设置在文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        signin = (Button) findViewById(R.id.signin);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.smoothToHide();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avi.show();
                //ActionwhenClick();
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(account.isEmpty()){
                    Toast.makeText(LoginActivityTest.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    avi.hide();
                    return;
                }
                else if(password.isEmpty()){
                    Toast.makeText(LoginActivityTest.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    avi.hide();
                    return;
                }
                else
                {
                    boolean s = sendRequestWithHttpURLConnection(account ,password);
                    Log.d("Hello1", String.valueOf(s));

                    if(!s){
                        Toast.makeText(LoginActivityTest.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        avi.hide();
                    }
                    else{
                        Toast.makeText(LoginActivityTest.this, "登录成功", Toast.LENGTH_SHORT).show();
                        editor = pref.edit();
                        if(rememberPass.isChecked()){//复选框是否被选中
                            editor.putBoolean("remember_password", true);
                            editor.putString("account", account);
                            editor.putString("password", password);
                        } else{
                            editor.clear();
                        }
                        editor.apply();
                        avi.hide();
                        Intent walkThrough = new Intent(LoginActivityTest.this, Slide_Menu.class);
                        startActivity(walkThrough);
                    }
                }
            }
        });
    }
    private boolean sendRequestWithHttpURLConnection(String acc, String pas){
        //开启线程来发起网络请求
        SendUserJson senduserjson = new SendUserJson(acc, pas);
        Thread thread = new Thread(senduserjson);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!senduserjson.content.equals("False") ) {
            getback = senduserjson.content;
            Log.d("Hello", getback);
            return true;
        }
        else return false;
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            ActivityCollector.finishAll();
        }
    }

    public void sendRequestWithHttpURLConnection(){
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try{
                    //http://10.18.117.72:5000/login http://10.19.116.193:5000/login
                    URL url=new URL("http://10.19.116.193:5000/login");
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoOutput(true);

                    //post请求的参数
                    User u = new User(accountEdit.toString(), passwordEdit.toString());

                    String data = JSON.toJSONString(u);

                    //String data = "{\"username\":\"h1047741619\",\"password\":\"hhx\"}";

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
                    String content = null;
                    try {
                        while((line=bufr.readLine())!=null){
                            response.append(line+"\n");
                        }
                        content = EncodingUtils.getString(response.toString().getBytes(), "UTF-8");

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    //parseJSONWithJSONObject(content);
                    //Log.d("LoginActivityTest", content);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally{
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    public  void parseJSONWithJSONObject(String jsonData){
        try{
            List<Grade>gradeList = JSON.parseArray(jsonData, Grade.class);
//            Lesson lesson = lessonList.get(0);
//            Log.e("MainActivity", "" + lesson.getName());
            for(Grade grade : gradeList){
                Log.e("MainActivity", grade.getName() + " " + grade.getCredit() + " " + grade.getScore() + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
