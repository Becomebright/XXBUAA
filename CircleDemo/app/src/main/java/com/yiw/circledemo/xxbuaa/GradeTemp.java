package com.yiw.circledemo.xxbuaa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.yiw.circledemo.R;

import java.util.ArrayList;
import java.util.List;


public class GradeTemp extends AppCompatActivity{
    public Handler handler;
    public String getback = null;
    private List<String> dataList;
    private ArrayAdapter<String> spinnerAdapter;
    private Spinner spinner;
    private List<String> gradeTerm;
    private List<Grade> gradeList = new ArrayList<Grade>();
    private ListView listView;
    private GradeAdapter adapter;
    private FinalGradeList finalgradelist;
    private final int menuPosition = 1;
    private BottomNavigationBar bottomNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_grade_temp);
        spinner = (Spinner)findViewById(R.id.switchTerm);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        MainActivity.gradeList.clear();
//        Intent intent = getIntent();
//        getback = intent.getStringExtra("grade");
        initGrades(); // 初始化成绩数据
        finalgradelist = new FinalGradeList();
        //////////////创建Spinner数据
        fillDataList();
        listView = (ListView) findViewById(R.id.list_temp);
        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dataList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        init();
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
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
                                bottomNavigationBar.setFirstSelectedPosition(menuPosition);
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
    private void init()
    {
        gradeList.addAll(finalgradelist.gradeList);
        spinner.setAdapter(spinnerAdapter);
        adapter = new GradeAdapter(gradeList, GradeTemp.this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Grade grade = gradeList.get(position);
                Toast.makeText(GradeTemp.this, grade.getName()+"\n"+grade.getCredit()
                        , Toast.LENGTH_SHORT).show();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<String> adapter_item = (ArrayAdapter<String>) adapterView.getAdapter();
                String s = adapter_item.getItem(i).toString();
                String grade_spring_autumn = s.substring(10,11);
                s = s.substring(0, 9);
                List<Grade> gradeList1 = new ArrayList<Grade>(finalgradelist.gradeList);
                for(Grade g : finalgradelist.gradeList)
                {
                    if(!g.getTime() .equals(s+grade_spring_autumn))
                    {
                        gradeList1.remove(g);
                    }
                }
                gradeList.clear();
                gradeList.addAll(gradeList1);
                adapter.notifyDataSetChanged();
                Log.d("Hello!", "gradeList Changed successfully");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void fillDataList() {
        dataList = new ArrayList<>();
        gradeTerm = new ArrayList<>();
        for(Grade grade : finalgradelist.gradeList)
        {
            String grade_year = grade.getTime().substring(0,9);//区分学年
            String grade_12 = grade.getTime().substring(10);//区分上下学期
            if(!gradeTerm.contains(grade_year))
            {
                gradeTerm.add(grade_year);
                dataList.add(grade_year+"第1学期");
                dataList.add(grade_year+"第2学期");
            }
        }
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initGrades() {
        sendRequestWithHttpURLConnection();
    }

    private void sendRequestWithHttpURLConnection(){
        //开启线程来发起网络请求
        Sendgradejson sendgradejson = new Sendgradejson();
        Thread thread = new Thread(sendgradejson);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MainActivity.gradeList = sendgradejson.gradeList;
    }



}