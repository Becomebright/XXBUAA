package com.yiw.circledemo.xxbuaa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiw.circledemo.R;
import com.yiw.circledemo.xxbuaa.Grade;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class GradeAdapter extends BaseAdapter {
    private int resourceId;
    private List<Grade> grades;
    private Context context;

//    public GradeAdapter(Context context, int textViewResourceId,
//                        List<Grade> objects) {
//        super(context, textViewResourceId, objects);
//        resourceId = textViewResourceId;
//    }

    public GradeAdapter(List<Grade> grades, Context mContext)
    {
        this.grades = grades;
        context = mContext;
    }

    @Override
    public int getCount() {
        return grades.size();
    }

    @Override
    public Object getItem(int position) {
        return grades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Grade grade = getItem(position); // 获取当前项的Grade实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.grade_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.gradeScore = (TextView) view.findViewById (R.id.grade_score);
            viewHolder.gradeName = (TextView) view.findViewById (R.id.grade_name);
            viewHolder.gradeCredit = (TextView) view.findViewById(R.id.grade_credit);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.gradeScore.setText(grades.get(position).getScore());
        viewHolder.gradeName.setText(grades.get(position).getName());
        viewHolder.gradeCredit.setText(grades.get(position).getCredit());
        return view;
    }
    public void add(Grade data){
        if (grades == null) {
            grades = new LinkedList<>();
        }
        grades.add(data);
        //删除的话用remove
        notifyDataSetChanged();
    }
    public void remove(Grade data){
        if (grades != null) {
            grades.remove(data);
        }
        //删除的话用remove
        notifyDataSetChanged();
    }
    public void clear() {
        if(grades != null) {
            grades.clear();
        }
        notifyDataSetChanged();
    }
    class ViewHolder {
        TextView gradeScore;//成绩得分

        TextView gradeName;//课程名称

        TextView gradeTime;//开课时间

        TextView gradeCredit;//学分

    }
}
