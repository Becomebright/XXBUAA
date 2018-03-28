package com.example.hasee_pc.xxbuaa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class News_show extends AppCompatActivity {

    private List<News> newsList = new ArrayList<News>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initNews(); // 初始化水果数据
        NewsAdapter adapter = new NewsAdapter(News_show.this, R.layout.news_item, newsList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                News news = newsList.get(position);
            }
        });
    }

    private void initNews() {
            News apple = new News(R.drawable.news_1);
            newsList.add(apple);
            News banana = new News(R.drawable.news_2);
            newsList.add(banana);
            News orange = new News(R.drawable.news_3);
            newsList.add(orange);
            News watermelon = new News(R.drawable.news_4);
            newsList.add(watermelon);
            News pear = new News(R.drawable.news_5);
            newsList.add(pear);
            News pear2 = new News(R.drawable.news_6);
            newsList.add(pear2);
    }

}

