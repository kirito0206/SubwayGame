package com.example.subwaygame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.subwaygame.R;

public class GridviewAdapter extends BaseAdapter {
    Context context;
    int[] image = {R.drawable.achievement, R.drawable.achievement, R.drawable.achievement, R.drawable.achievement, R.drawable.achievement,
            R.drawable.achievement, R.drawable.achievement, R.drawable.achievement, R.drawable.achievement};
    String[] name = {"成就","成就","成就","成就","成就","成就","成就","成就","成就"};

    public GridviewAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount(){
        return image.length;
    }

    @Override
    public Object getItem(int position){
        return name[position];
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.grid_pic);
        TextView textView = (TextView)view.findViewById(R.id.gird_title);
        imageView.setImageResource(image[position]);
        textView.setText(name[position]);
        return view;
    }


}
