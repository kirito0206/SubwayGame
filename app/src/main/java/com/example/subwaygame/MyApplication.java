package com.example.subwaygame;

import android.app.Application;
import android.content.Context;

import com.example.subwaygame.data.Flower;
import com.example.subwaygame.data.Player;

import org.litepal.LitePal;

import java.util.ArrayList;

public class MyApplication extends Application {
    private static Context context;
    private static Player player;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
        LitePal.getDatabase();
        player = LitePal.findFirst(Player.class);
        if (player == null)
            player = new Player("kirito","123",new ArrayList<String>(),new ArrayList<Flower>(),new Flower());
    }

    public static Context getContext(){
        return context;
    }

    public static Player getInstance(){
        return player;
    }
}
