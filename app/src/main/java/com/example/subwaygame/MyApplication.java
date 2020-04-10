package com.example.subwaygame;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.subwaygame.data.Flower;
import com.example.subwaygame.data.Player;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    private static Context context;
    private static Player player;
    private static Flower flower;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
        LitePal.getDatabase();
        List<Player> players = LitePal.findAll(Player.class);
        if (players != null && !players.isEmpty()){
            player = players.get(0);
        }
        List<Flower> flowers = LitePal.findAll(Flower.class);
        if (flowers != null && !flowers.isEmpty()){
            flower = flowers.get(0);
        }

        if (player == null){
            List<String> list = new ArrayList<>();
            for(int i = 0;i < 6;i++)list.add("no");

            player = new Player("可达鸭","123",list,0,100,1,0,0);
            player.save();
        }
        if (flower == null){
            flower = new Flower();
            flower.save();
        }
    }

    public static Context getContext(){
        return context;
    }

    public static Player getInstance(){
        return player;
    }

    public static Flower getFlowerInstance(){return flower;}
}
