package com.example.subwaygame;

import android.app.Application;
import android.content.Context;

import com.example.subwaygame.data.Flower;
import com.example.subwaygame.data.Player;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    private static Context context;
    private static Player player;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
        LitePal.getDatabase();
        /*List<Player> players = LitePal.findAll(Player.class);
        if (players != null && !players.isEmpty()){
            player = players.get(0);
        }*/
        if (player == null){
            player = new Player("kirito","123",new ArrayList<String>(),0,100,1,new Flower());
            player.save();
        }
    }

    public static Context getContext(){
        return context;
    }

    public static Player getInstance(){
        return player;
    }
}
