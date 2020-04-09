package com.example.subwaygame.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.subwaygame.MyApplication;
import com.example.subwaygame.R;

import java.util.List;

import static org.litepal.LitePalBase.TAG;

public class GridviewAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private GridView gridView;
    Context context;
    int[] image = {R.drawable.achievement, R.drawable.achievement, R.drawable.achievement, R.drawable.achievement, R.drawable.achievement,
            R.drawable.achievement};
    String[] name = {"暂未解锁", "成就", "成就", "成就", "成就", "成就"};
    String[] str = {"小喷壶","大喷壶","黄金喷壶","修剪新手","修剪熟手","园艺大师","小池塘","小湖泊","水中沙特",
            "养花新手","养花熟手","养花大师","新晋园丁","勤劳园丁","熟练园丁","第一次收获！","你为什么这么熟练啊！","白嫖狂魔","暂未解锁"};


    public GridviewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public Object getItem(int position) {
        return name[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.grid_pic);
        TextView textView = (TextView) view.findViewById(R.id.gird_title);
        refresh(MyApplication.getInstance().getAchievementList());

        Glide.with(context).load(image[position]).override(200,200).into(imageView);
        textView.setText(name[position]);
        return view;
    }

    public void refresh(List<String> list){
        for(int i = 0; i < list.size();i++){
            switch(list.get(i)){
                case "no":
                    image[i] = R.drawable.achievement;
                    name[i] = "暂未解锁";
                    break;
                case "3rd":
                    image[i] = R.drawable.copper;
                    name[i] = str[i*3];
                    break;
                case "2nd":
                    image[i] = R.drawable.silver;
                    name[i] = str[i*3+1];
                    break;
                case "1st":
                    image[i] = R.drawable.golden;
                    name[i] = str[i*3+2];
                    break;
                default:break;
            }
        }
    }

    Dialog dialog;



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("123","456");
        LayoutInflater inflater=LayoutInflater.from( context);
        View view1 = inflater.inflate(R.layout.dialog,null);
        TextView textView;
        ImageView imageView;
        textView = (TextView)view1.findViewById(R.id.text1);
        imageView = (ImageView)view1.findViewById(R.id.img);
        imageView.setImageResource(image[position]);
        if(name[position] == str[18]) {
            textView.setText("您暂未解锁该成就");
        }
        else textView.setText("你已经解锁了 "+name[position]+" 成就！");
        AlertDialog.Builder builder=new AlertDialog.Builder( context );
        builder.setView( view1 );
        dialog=builder.create();//创建对话框
        dialog.show();//显示对话框
        view1.findViewById(R.id.but).setOnClickListener( new View.OnClickListener() {//获取布局里面按钮
            @Override
            public void onClick(View v) {
                dialog.dismiss();//点击按钮对话框消失
            }
        } );
    }
}
