package com.example.hasee_pc.xxbuaa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小黑866 on 2018/3/28.
 */

public class FinalGradeList {
    public final List<Grade> gradeList;
    public FinalGradeList()
    {
        gradeList = new ArrayList<Grade>(MainActivity.gradeList);
    }

}
