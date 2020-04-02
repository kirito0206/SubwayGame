package com.example.subwaygame.activity;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.subwaygame.R;
import com.example.subwaygame.adapter.GridviewAdapter;

public class AchievementActivity extends AppCompatActivity {
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        gridView = (GridView)findViewById(R.id.grid_view);
        gridView.setAdapter(new GridviewAdapter(this));

    }




}
