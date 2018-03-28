package com.yiw.circledemo.xxbuaa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Lesson_show extends AppCompatActivity {

    private TextView mTextMessage;
    private Button button;

    public List<Course> courseList = new ArrayList<Course>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_show);

        mTextMessage = (TextView) findViewById(R.id.message);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SendCourseJson sendCourseJson = new SendCourseJson();
                Thread thread = new Thread(sendCourseJson);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                courseList = sendCourseJson.courseList;
                for(Course course : courseList){
                    Log.d("heiheihei", course.getData());
                }
            }
        });
    }

}
