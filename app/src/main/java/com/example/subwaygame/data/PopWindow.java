package com.example.subwaygame.data;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.subwaygame.R;

public class PopWindow {
    private static PopupWindow popupWindow;
    public static  void show(final Activity activity, final CharSequence text){
        if (popupWindow == null) {
            popupWindow = new PopupWindow();
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout linearLayout = new LinearLayout(activity);
            View viewContent = inflater.inflate(R.layout.popwindow, linearLayout);
            popupWindow.setContentView(viewContent);
            popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setAnimationStyle(R.style.PopupTopAnim);
        }
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.TOP, 0, 0);

        final TextView tv = ((TextView) popupWindow.getContentView().findViewById(R.id.txt));
        tv.setText(text);


        handler.removeMessages(1);
        handler.sendEmptyMessageDelayed(1, 1000);

    }

    public static void dismiss(){
        popupWindow.dismiss();
    }

    private static  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            popupWindow.dismiss();
        }

    };
}
