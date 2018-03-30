package com.yiw.circledemo.xxbuaa;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.duanqu.qupai.project.Text;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.yiw.circledemo.R;
import com.yiw.circledemo.bean.Background;
import com.yiw.circledemo.bean.ImageAndText;
import com.yiw.circledemo.bean.PhotoInfo;
import com.yiw.circledemo.bean.PostImage;
import com.yiw.circledemo.bean.Sendheadphoto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class GradeTemp extends AppCompatActivity implements View.OnClickListener{
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
    private TextView textView;//姓名
    private ImageView imageView;//头像
    private DrawerLayout drawerLayout;
    private SystemBarTintManager tintManager;
    private NavigationView navigationView;
    private String str;
    ImageView menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActivityCollector.addActivity(this);
        initWindow();
        setContentView(R.layout.activity_grade_temp);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_na);
        navigationView = (NavigationView) findViewById(R.id.nav);
        menu= (ImageView) findViewById(R.id.main_menu);
        //drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//关闭手势滑动，只通过点击按钮来滑动
        menu.setOnClickListener(this);
        View headerView = navigationView.getHeaderView(0);
        textView = headerView.findViewById(R.id.nickname);
        textView.setText(MainActivity.user.getNickname());
        //textView = (TextView)view.findViewById(R.id.nickname);
//        Log.d("NickName",MainActivity.user.getNickname());
        imageView = (ImageView)headerView.findViewById(R.id.person);


        String photopath = Background.getPhotopath();

        Log.d("path", photopath);
        if(!photopath.equals(""))
        {
            Bitmap bm = BitmapFactory.decodeFile(photopath);
            imageView.setImageBitmap(bm);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setheadphoto();
            }
        });
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item.setChecked(true);
                //setContentView(R.layout.head);
                switch (item.getItemId()){
                    case R.id.lessonlist:
                        Intent walkThrough_lesson = new Intent(getApplicationContext(), Course_Temp.class);
                        walkThrough_lesson.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        overridePendingTransition(0, 0);
                        startActivity(walkThrough_lesson);
                        GradeTemp.this.finish();
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.grade:
//                        Intent walkThrough_grade = new Intent(Course_Temp.this, GradeTemp.class);
                        //walkThrough_grade.putExtra("grade", str);
//                        startActivity(walkThrough_grade);
                        break;
                    case R.id.friends:
                        Intent walkThrough_loading = new Intent(getApplicationContext(), com.yiw.circledemo.activity.MainActivity.class);
                        startActivity(walkThrough_loading);
                        break;
                    case R.id.setting:
                        setheadphoto();
                        View headerView1 = navigationView.getHeaderView(0);
                        ImageView imageView1 = (ImageView)headerView1.findViewById(R.id.person);
                        String photopath = Background.getPhotopath();
                        Bitmap bm = BitmapFactory.decodeFile(photopath);
                        imageView1.setImageBitmap(bm);
                        drawerLayout.closeDrawer(navigationView);
                        return true;
                }
                //Toast.makeText(Slide_Menu.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
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
//        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
//        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
//        bottomNavigationBar
//                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
//                );
////        BadgeItem numberBadgeItem = new BadgeItem()
////                .setBorderWidth(4)
////                .setBackgroundColor(Color.RED)
////                .setText("5")
////                .setHideOnSelect(true);
//        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.schedule, "课表查询").setActiveColorResource(R.color.orange))
//                .addItem(new BottomNavigationItem(R.mipmap.report, "成绩查询").setActiveColorResource(R.color.teal))
//                .addItem(new BottomNavigationItem(R.mipmap.friends, "校友圈").setActiveColorResource(R.color.blue))
////                .addItem(new BottomNavigationItem(R.mipmap.search, "Movies & TV").setActiveColorResource(R.color.brown))
////                .addItem(new BottomNavigationItem(R.mipmap.ic_videogame_asset_white_24dp, "Games").setActiveColorResource(R.color.grey).setBadgeItem(numberBadgeItem))
//                .setFirstSelectedPosition(menuPosition)
//                .initialise();
//        bottomNavigationBar.setTabSelectedListener(
//                new BottomNavigationBar.OnTabSelectedListener() {
//                    @Override
//                    public void onTabSelected(int position) {
//                        if(position == menuPosition) return;
//                        switch (position)
//                        {
//                            case 0:
//                                Intent intent0 = new Intent(getApplicationContext(), Course_Temp.class);
//                                startActivity(intent0);
//                                Log.d("start", intent0.toString());
//                                break;
//                            case 1:
//                                Intent intent1 = new Intent(getApplicationContext(), GradeTemp.class);
//                                startActivity(intent1);
//                                Log.d("start", intent1.toString());
//                                break;
//                            case 2:
//                                Intent intent2 = new Intent(getApplicationContext(), GradeTemp.class);
//                                startActivity(intent2);
//                                break;
//                        }
//                    }
//
//                    @Override
//                    public void onTabUnselected(int position) {
//
//                    }
//
//                    @Override
//                    public void onTabReselected(int position) {
//
//                    }
//                }
//        );
    }
    public void setheadphoto(){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
    public static Bitmap decodeSampledBitmapFromFile(String pathName, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeFile(pathName, options);
        return createScaleBitmap(src, reqWidth, reqHeight,0);
    }
    private static Bitmap createScaleBitmap(Bitmap src, int dstWidth, int dstHeight, int inSampleSize) {
        // 如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影响，我们这里是缩小图片，所以直接设置为false
        Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false);
        if (src != dst) { // 如果没有缩放，那么不回收
            src.recycle(); // 释放Bitmap的native像素数组
        }
        return dst;
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
            final String str="你确认要把这张照片作为头像吗？";
            //final String de="e-note"+File.separator+tv.getText().toString();
            new AlertDialog.Builder(this).setTitle(str)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“确认”后的操作
                                    //createFile(de);
                                    Bitmap bm=decodeSampledBitmapFromFile(imgpath,80,80);

                                    String nphotopath= Background.getPhotopath();
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
                                    Sendheadphoto sendheadphoto=new Sendheadphoto(MainActivity.user.getUser_id(), photoInfo1.url);
                                    Thread thread1=new Thread(sendheadphoto);
                                    thread1.start();
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            ActivityCollector.finishAll();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_menu:
                if (drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.closeDrawer(navigationView);
                }else{
                    drawerLayout.openDrawer(navigationView);
                }
                break;
        }
    }
    private void initWindow() {//初始化窗口属性，让状态栏和导航栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            int statusColor = Color.parseColor("#50d2c2");
            tintManager.setStatusBarTintColor(statusColor);
            tintManager.setStatusBarTintEnabled(true);
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