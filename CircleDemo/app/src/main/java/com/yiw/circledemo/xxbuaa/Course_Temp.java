package com.yiw.circledemo.xxbuaa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.yiw.circledemo.R;
import com.yiw.circledemo.xxbuaa.TimeTableUI.AbsGridAdapter;
import com.yiw.circledemo.xxbuaa.TimeTableUI.MyAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Course_Temp extends AppCompatActivity{

    private Spinner spinner;

    private GridView detailCource;

    private MyAdapter adapter;

    private String[][] contents = new String[12][7];

    private AbsGridAdapter secondAdapter;

    private List<String> dataList;

    private ArrayAdapter<String> spinnerAdapter;


    private int[] num = new int[12];

    private final int menuPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course__temp);
        ActivityCollector.addActivity(this);
        detailCource = (GridView)findViewById(R.id.courceDetail);
        ///////////////第一种方式创建Adapater
//        List<String> list = init();
//        adapter = new MyAdapter(this, list);
//        detailCource.setAdapter(adapter);
        ///////////////第二种方式创建Adapter
        fillStringArray();
        secondAdapter = new AbsGridAdapter(this);
        secondAdapter.setContent(contents, 12, 7);
        detailCource.setAdapter(secondAdapter);
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
//        BadgeItem numberBadgeItem = new BadgeItem()
//                .setBorderWidth(4)
//                .setBackgroundColor(Color.RED)
//                .setText("5")
//                .setHideOnSelect(true);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.schedule, "课表查询").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.report, "成绩查询").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.mipmap.friends, "校友圈").setActiveColorResource(R.color.blue))
//                .addItem(new BottomNavigationItem(R.mipmap.search, "Movies & TV").setActiveColorResource(R.color.brown))
//                .addItem(new BottomNavigationItem(R.mipmap.ic_videogame_asset_white_24dp, "Games").setActiveColorResource(R.color.grey).setBadgeItem(numberBadgeItem))
                .setFirstSelectedPosition(menuPosition)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(
                new BottomNavigationBar.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position) {
                        if(position == menuPosition) return;
                        switch (position)
                        {
                            case 0:
                                Intent intent0 = new Intent(getApplicationContext(), Course_Temp.class);
                                startActivity(intent0);
                                Log.d("start", intent0.toString());
                                break;
                            case 1:
                                Intent intent1 = new Intent(getApplicationContext(), GradeTemp.class);
                                startActivity(intent1);
                                Log.d("start", intent1.toString());
                                break;
                            case 2:
                                Intent intent2 = new Intent(getApplicationContext(), com.yiw.circledemo.activity.MainActivity.class);
                                startActivity(intent2);
                                break;
                        }
                    }

                    @Override
                    public void onTabUnselected(int position) {

                    }

                    @Override
                    public void onTabReselected(int position) {

                    }
                }
        );
    }

    public void fillStringArray() {
        for(int i = 0; i < 12; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                contents[i][j] = "";
            }
        }
        for(int i = 0; i < 12; i++)
        {
            num[i] = i;
        }
        sendRequestWithHttpURLConnection();
        for(Course course : MainActivity.courseList){
            switch(course.getDay()){
                case "周一":
                    InsertCourse(1, course);
                    //Log.d("周一",course.getData() + course.getTime()+"\n");
                    break;
                case "周二":
                    InsertCourse(2, course);
                    //Log.d("周二",course.getData() + "时间:"+course.getTime()+"\n");
                    break;
                case "周三":
                    InsertCourse(3, course);
                    //Log.d("周三",course.getData() + course.getTime()+"\n");
                    break;
                case "周四":
                    InsertCourse(4, course);
                    //Log.d("周四",course.getData() + course.getTime()+"\n");
                    break;
                case "周五":
                    InsertCourse(5, course);
                    break;
                case "周六":
                    InsertCourse(6, course);
                    break;
                case "周日":
                    InsertCourse(7, course);
                    break;
            }
        }
    }
    private void InsertCourse(int day, Course course)
    {
//        for(int i = 0; i < 12; i++)
//            if(course.getTime().indexOf(Integer.toString(num[i])) != -1){
//                contents[i][day-1] = course.getData();
//            }
        String str = course.getTime();
        List<String> times = new ArrayList<String>();
        Pattern p = Pattern.compile("\\d{1,}");//这个1是指连续数字的最少个数
        Matcher m = p.matcher(str);
        while(m.find()){
            times.add(m.group());
        }
        for(String string : times){
            contents[Integer.parseInt(string)-1][day-1] = course.getData();
        }
    }
    private void sendRequestWithHttpURLConnection(){
        //开启线程来发起网络请求
        SendCourseJson sendCourseJson = new SendCourseJson();
        Thread thread = new Thread(sendCourseJson);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MainActivity.courseList = sendCourseJson.courseList;
//        for(Course course : MainActivity.courseList)
//        {
//            Log.d("Hello", course.getData()+"\n");
//        }
    }
    public void fillDataList() {
        dataList = new ArrayList<>();
        for(int i = 1; i < 21; i++) {
            dataList.add("第" + i + "周");
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }


    
}
