package com.yiw.circledemo.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yiw.circledemo.R;
import com.yiw.circledemo.adapter.ImageAndTextListAdapter;
import com.yiw.circledemo.bean.AddJson;
import com.yiw.circledemo.bean.ImageAndText;
import com.yiw.circledemo.bean.PhotoInfo;
import com.yiw.circledemo.bean.PostImage;
import com.yiw.circledemo.bean.Sendjson;
import com.yiw.circledemo.bean.Sendpost;
import com.yiw.circledemo.bean.User;
import com.yiw.circledemo.mvp.presenter.CirclePresenter;
import com.yiw.circledemo.widgets.TitleBar;
import com.yiw.qupai.QPManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hasee-pc on 2018/03/20.
 */

public class AddPostActivity extends AppCompatActivity {
    private String user_id;
    private TitleBar titleBar;
    private EditText editText;
    private List<PhotoInfo> photos=new ArrayList<>();
    private ImageAndTextListAdapter imageAndTextListAdapter;
    private GridView gridView;
    private List<ImageAndText> list = new ArrayList<ImageAndText>();
    private TextView textViewphone;
    private TextView textViewprice;
    private EditText editTextphone;
    private EditText editTextprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initTitle();
        Intent intent=getIntent();
        user_id=intent.getStringExtra("user_id");
        //initPermission();
        gridView=(GridView)findViewById(R.id.gv);
        imageAndTextListAdapter=new ImageAndTextListAdapter(this, list, gridView);
        gridView.setAdapter(imageAndTextListAdapter);
        //跳骚市场选框判定
        final CheckBox checkBox=(CheckBox)findViewById(R.id.checkBoxts);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    textViewphone=(TextView)findViewById(R.id.textViewphone);
                    textViewprice=(TextView)findViewById(R.id.textViewprice);
                    editTextphone=(EditText)findViewById(R.id.editTextphone);
                    editTextprice=(EditText)findViewById(R.id.editTextprice);
                    textViewphone.setVisibility(View.VISIBLE);
                    textViewprice.setVisibility(View.VISIBLE);
                    editTextphone.setVisibility(View.VISIBLE);
                    editTextprice.setVisibility(View.VISIBLE);
                }
                else {
                    textViewphone=(TextView)findViewById(R.id.textViewphone);
                    textViewprice=(TextView)findViewById(R.id.textViewprice);
                    editTextphone=(EditText)findViewById(R.id.editTextphone);
                    editTextprice=(EditText)findViewById(R.id.editTextprice);
                    textViewphone.setVisibility(View.GONE);
                    textViewprice.setVisibility(View.GONE);
                    editTextphone.setVisibility(View.GONE);
                    editTextprice.setVisibility(View.GONE);
                }
            }
        });

    }
    public void addphoto(View v){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==2&&resultCode == Activity.RESULT_OK && data != null){
            super.onActivityResult(requestCode, resultCode, data);
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            final String imgpath = c.getString(columnIndex);
            //chooseimg = new File(imagePath);
            //TextView ProvinceTxt = (TextView) mySpinner.getSelectedView();
            //final String codeString = ProvinceTxt.getText().toString();
            //final TextView tv=(TextView)findViewById(R.id.editTextaddcourse);
            final String str="你确认要上传这张照片吗？";
            //final String de="e-note"+File.separator+tv.getText().toString();
            new AlertDialog.Builder(this).setTitle(str)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“确认”后的操作
                                    //createFile(de);
                                    Bitmap bm = BitmapFactory.decodeFile(imgpath);
                                    String nphotopath=getPhotopath();
                                    File file=new File(nphotopath);
                                    try {
                                        file.createNewFile();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    OutputStream os = null;
                                    try {
                                        os =new FileOutputStream(file);
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    bm.compress(Bitmap.CompressFormat.JPEG,10,os) ;
                                    ImageAndText imageAndText=new ImageAndText(nphotopath);
                                    //list.add(imageAndText);
                                    //imageAndTextListAdapter.notifyDataSetChanged();
                                    PostImage postImage=new PostImage(nphotopath);
                                    Thread thread=new Thread(postImage);
                                    thread.start();
                                    try {
                                        thread.join();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    PhotoInfo photoInfo1=new PhotoInfo();
                                    photoInfo1.h=1500;
                                    photoInfo1.w=1500;
                                    photoInfo1.url=postImage.getBackpath();
                                    photos.add(photoInfo1);
                                    if(photos.size()<3){
                                        //list.add(imageAndText);
                                        //imageAndTextListAdapter.notifyDataSetChanged();
                                    }
                                    //imageAndTextListAdapter.notifyDataSetChanged();
                                    //EditText editText=(EditText)findViewById(R.id.editTextpz);
                                    //RatingBar ratingBar=(RatingBar)findViewById(R.id.ratingBar2);
                                    //TextView ProvinceTxt = (TextView) mySpinner.getSelectedView();
                                    //String codeString = ProvinceTxt.getText().toString();
                                    //Date date=new Date();
                                    //sqLiteDatabase.execSQL("insert into "+codeString+" values('"+file.getAbsolutePath().toString()+"','"+editText.getText().toString()+"',"+(int)ratingBar.getRating()+","+date.getTime()+",0,0);");
                                    //updatespanner();
                                    //Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                    //Record.biji(sqLiteDatabase);
                                }
                            })
                    .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 点击“返回”后的操作,这里不设置没有任何操作
                        }
                    }).show();
            c.close();
        }
    }
    private String getPhotopath() {
        // 照片全路径
        String fileName = "";
        if (!createFile("pyq")) {
            return fileName;
        }
        //TextView ProvinceTxt = (TextView) mySpinner.getSelectedView();
        //String codeString = ProvinceTxt.getText().toString();
        // 文件夹路径
        String pathUrl = Environment.getExternalStorageDirectory()+ File.separator + "pyq";
        SimpleDateFormat   formatter   =   new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate =  new Date(System.currentTimeMillis());
        String   str   =   formatter.format(curDate);
        pathUrl=pathUrl+ File.separator + str+".jpg";
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
    private void initTitle() {

        titleBar = (TitleBar) findViewById(R.id.main_title_bar);
        titleBar.setTitle("BUAA校友圈");
        titleBar.setTitleColor(getResources().getColor(R.color.white));
        titleBar.setBackgroundColor(getResources().getColor(R.color.title_bg));

        TextView textView = (TextView) titleBar.addAction(new TitleBar.TextAction("发布动态") {
            @Override
            public void performAction(View view) {
                CheckBox checkBox=(CheckBox)findViewById(R.id.checkBoxts);

                //Toast.makeText(MainActivity.this, "敬请期待...", Toast.LENGTH_SHORT).show();
//                PhotoInfo photoInfo1=new PhotoInfo();
//                photoInfo1.h=326;
//                photoInfo1.w=519;
//                photoInfo1.url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521561164480&di=5c3bafacb8cd901a0c1f86fe8aba031f&imgtype=0&src=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Farchive%2Fb3a3dd9b0468699f928c6acfeaf9de3bb221a876.jpg";
//                photos.add(photoInfo1);
                SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
                Date curDate =  new Date(System.currentTimeMillis());
                editText=(EditText) findViewById(R.id.content);
                AddJson addJson;
                if(checkBox.isChecked()){
                    if(photos.size()==0){
                        Toast.makeText(AddPostActivity.this,"请至少发布一张图片",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String str="###跳瘙市场###\n"+editText.getText().toString()+"\n期望价格："+editTextprice.getText().toString()+"\n联系方式"+editTextphone.getText().toString();

                    addJson=new AddJson(Integer.parseInt(user_id),str,formatter.format(curDate),"5",photos);
                }
                else{
                    addJson=new AddJson(Integer.parseInt(user_id),editText.getText().toString(),formatter.format(curDate),"2",photos);
                }
                Sendpost sendjson=new Sendpost(addJson);

                Thread thread=new Thread(sendjson);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent2 = new Intent();
                intent2.putExtra("data_return", "hello firstActivity");
                setResult(100, intent2);
                finish();
                //QPManager.startRecordActivity(MainActivity.this);
            }
        });
        textView.setTextColor(getResources().getColor(R.color.white));
    }
}
