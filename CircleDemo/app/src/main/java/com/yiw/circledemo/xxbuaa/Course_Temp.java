package com.yiw.circledemo.xxbuaa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;


import com.yiw.circledemo.R;
import com.yiw.circledemo.xxbuaa.TimeTableUI.AbsGridAdapter;
import com.yiw.circledemo.xxbuaa.TimeTableUI.MyAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Course_Temp extends AppCompatActivity {

    private Spinner spinner;

    private GridView detailCource;

    private MyAdapter adapter;

    private String[][] contents = new String[12][7];

    private AbsGridAdapter secondAdapter;

    private List<String> dataList;

    private ArrayAdapter<String> spinnerAdapter;


    private int[] num = new int[12];

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
        //////////////创建Spinner数据
        fillDataList();
        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dataList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }
    /**
     * 准备数据
     */
    private List<String> init() {
        List<String> list = new ArrayList<String>();
        list.add("现代测试技术B211");
        list.add("数据结构与算法B211");
        list.add("微机原理及应用E203");
        list.add("面向对象程序设计A309");
        list.add("数据结构与算法B207");
        list.add("");
        list.add("");
        list.add("微机原理及应用E203");
        list.add("");
        list.add("电磁场理论A212");
        list.add("传感器电子测量A\nC309");
        list.add("微机原理及应用E203");
        list.add("");
        list.add("");
        list.add("电磁场理论A212");
        list.add("面向对象程序设计A309");
        list.add("现代测试技术B211");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("传感器电子测量A\nC309");
        list.add("面向对象程序设计A309");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        return list;
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
                    Log.d("周二",course.getData() + "时间:"+course.getTime()+"\n");
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
