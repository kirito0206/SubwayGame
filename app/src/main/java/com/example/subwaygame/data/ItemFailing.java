package com.example.subwaygame.data;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.subwaygame.MyApplication;
import com.example.subwaygame.R;
import com.example.subwaygame.activity.CollectActivity;

import java.util.ArrayList;
import java.util.List;

public class ItemFailing extends View implements Runnable {

    private int time = 1;
    public static int timeAll = 80;
    public static int collectNumber = 0;
    private static int widthScreen,heightScreen;
    private int widthPicture,heightPicture;
    private Paint mPaint;
    private Bitmap bitmap;
    public static List<ItemFailing> list = new ArrayList<>();
    private int x,y;
    private int picNumber;
    //是否正在游戏
    private boolean isplaying;

    public ItemFailing(){
        super(MyApplication.getContext());
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        widthScreen = dm.widthPixels;
        heightScreen = dm.heightPixels;

        init();
    }

    private void init(){
        isplaying = true;
        //随机选取图片
        picNumber = (int)(Math.random()*10);
        switch (picNumber)
        {
            case 9:
                bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.addtime);
                break;
            case 0:
            case 1:
            case 2:
                bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.boom);
                break;
            default:bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.addwater);
                break;
        }
        widthPicture = bitmap.getWidth();
        heightPicture = bitmap.getHeight();
        //设置想要的大小
        int newWidth=150;
        int newHeight=150;

        //计算压缩的比率
        float scaleWidth=((float)newWidth)/widthPicture;
        float scaleHeight=((float)newHeight)/heightPicture;

        //获取想要缩放的matrix
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);

        //获取新的bitmap
        bitmap=Bitmap.createBitmap(bitmap,0,0,widthPicture,heightPicture,matrix,true);

        widthPicture = bitmap.getWidth();
        heightPicture = bitmap.getHeight();
        x = (int)(Math.random()*(widthScreen-widthPicture));
        y = 0;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setTextSize(60);
    }

    @Override//绘图
    protected void onDraw(Canvas canvas) {
        if(!isplaying)
        {
            list.clear();
            Integer n =MyApplication.getInstance().getWaterNumber();
            MyApplication.getInstance().setWaterNumber(n+collectNumber);
            Message msg = new Message();
            msg.what = 1;
            CollectActivity.handler.getMHandler().sendMessage(msg);
            return;
        }
        String text0 = "收集水滴数: "+collectNumber;
        String text1 = "剩余时间数:"+timeAll/4;
        canvas.drawText(text1, widthScreen-400,80,mPaint);
        canvas.drawText(text0,widthScreen-400,160,mPaint);
        for(int i=0;i < list.size();i++)
        {
            ItemFailing temp = list.get(i);
            canvas.translate(temp.x,temp.y);
            canvas.drawBitmap(temp.bitmap,0,0,null);
            canvas.translate(-temp.x,-temp.y);
        }
    }

    public void comein(){
        ItemFailing gift = new ItemFailing();
        list.add(gift);
    }

    @Override//响应点击事件
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                double x = event.getX();
                double y = event.getY();
                for(int i=0;i < list.size();i++)
                {
                    ItemFailing temp = list.get(i);
                    if(x - temp.x >= 0 && x - temp.x <= widthPicture && y - temp.y >= 0 && y - temp.y <= heightPicture)
                    {
                        list.remove(i);
                        if (temp.picNumber == 9)
                            timeAll+=15;
                        else if (temp.picNumber < 3)
                            timeAll = 0;
                        else
                            collectNumber++;
                        break;
                    }
                }
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override//创建线程跑程序
    public void run() {
        while(isplaying)
        {
            try {
                Thread.sleep(250);
                timeAll--;
                for(int i=0;i < list.size();i++)
                {
                    ItemFailing temp = list.get(i);
                    temp.y = temp.time*150;
                    temp.time++;

                    if(temp.y > temp.heightScreen-temp.heightPicture)
                    {
                        list.remove(i);
                        continue;
                    }
                    invalidate();
                }
                if(timeAll <= 0)
                    break;
                if(timeAll % 2 == 0)
                comein();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        isplaying = false;
    }
}