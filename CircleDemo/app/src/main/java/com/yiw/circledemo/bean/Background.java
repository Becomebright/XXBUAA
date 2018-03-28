package com.yiw.circledemo.bean;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Message;
import android.widget.TextView;

import com.yiw.circledemo.R;
import com.yiw.circledemo.activity.MainActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hasee-pc on 2018/03/28.
 */

public class Background implements Runnable {
    public Bitmap bm;
    private MainActivity mainActivity;

    public Background(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    public static String getPhotopath() {
        // 照片全路径
        String fileName = "";
        if (!createFile("pyq")) {
            return fileName;
        }
        //TextView ProvinceTxt = (TextView) mySpinner.getSelectedView();
        //String codeString = ProvinceTxt.getText().toString();
        // 文件夹路径
        String pathUrl = Environment.getExternalStorageDirectory()+ File.separator + "pyq";
       // SimpleDateFormat formatter   =   new SimpleDateFormat("yyyyMMddHHmmss");
       // Date curDate =  new Date(System.currentTimeMillis());
       // String   str   =   formatter.format(curDate);
        pathUrl=pathUrl+ File.separator + "headphoto.jpg";
        return pathUrl;
    }
    public static boolean createFile(String director) {

        if (isFileExist(director)) {
            return true;
        } else {
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + director);
            if (!file.mkdirs()) {
                return false;
            }
            return true;
        }
    }
    public static boolean isFileExist(String director) {
        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator + director);
        return file.exists();
    }
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message message = new Message();
        message.what = 1;

        mainActivity.h.sendMessage(message);
        File file=new File(getPhotopath());
        if(file.exists()){
            bm= BitmapFactory.decodeFile(getPhotopath());
            Message message1 = new Message();
            message1.what = 2;
            mainActivity.h.sendMessage(message1);
        }

    }
}
