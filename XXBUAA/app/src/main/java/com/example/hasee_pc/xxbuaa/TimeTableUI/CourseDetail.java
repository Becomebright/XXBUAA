package com.example.hasee_pc.xxbuaa.TimeTableUI;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.TableLayout;

/**.
 * 用来显示课程表的自定义View
 */
public class CourseDetail extends TableLayout {

    public CourseDetail(Context context)
    {
        super(context);
    }

    /**
     * 重写View必备构造器
     * @param context
     * @param attrs
     */
    public CourseDetail(Context context, AttributeSet attrs) {
        super(context, attrs);
        ListView view = new ListView(context);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void onLayout(boolean changed, int l, int t, int r, int b) {

        super.onLayout(changed,l, t, r, b);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}
