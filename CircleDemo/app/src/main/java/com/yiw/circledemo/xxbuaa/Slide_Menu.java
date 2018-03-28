package com.yiw.circledemo.xxbuaa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasee_pc.xxbuaa.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.Timer;
import java.util.TimerTask;

public class Slide_Menu extends Activity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private SystemBarTintManager tintManager;
    private NavigationView navigationView;
    private String str;
    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_slide__menu);
        initWindow();
        TextView textView = (TextView)this.findViewById(R.id.text_title);
        textView.setText("欢迎使用信息助手！");
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_na);
        navigationView = (NavigationView) findViewById(R.id.nav);
        menu= (ImageView) findViewById(R.id.main_menu);
        View headerView = navigationView.getHeaderView(0);
        //drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//关闭手势滑动，只通过点击按钮来滑动
        menu.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item.setChecked(true);
                //setContentView(R.layout.head);
                switch (item.getItemId()){
                    case R.id.lessonlist:
                        Intent walkThrough_lesson = new Intent(getApplicationContext(), Course_Temp.class);
                        startActivity(walkThrough_lesson);
                        break;
                    case R.id.news:
                        Intent walkThrough_news = new Intent(getApplicationContext(), News_show.class);
                        startActivity(walkThrough_news);
                        break;
                    case R.id.grade:
                        Intent walkThrough_grade = new Intent(Slide_Menu.this, GradeTemp.class);
                        //walkThrough_grade.putExtra("grade", str);
                        startActivity(walkThrough_grade);
                        break;
                    case R.id.friends:
                        Intent walkThrough_loading = new Intent(getApplicationContext(), IndicatorActivity.class);
                        startActivity(walkThrough_loading);
                        break;
                }
                //Toast.makeText(Slide_Menu.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_menu:
                if (drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.closeDrawer(navigationView);
                }else{
                    drawerLayout.openDrawer(navigationView);
                }
                break;
        }
    }
    private void initWindow() {//初始化窗口属性，让状态栏和导航栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            int statusColor = Color.parseColor("#50d2c2");
            tintManager.setStatusBarTintColor(statusColor);
            tintManager.setStatusBarTintEnabled(true);
        }
    }
}

