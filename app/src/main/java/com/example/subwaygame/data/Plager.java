package com.example.subwaygame.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.ArrayList;

@Entity
public class Plager {
    @Id
    private String name;
    private String headPicture;
    private ArrayList<String> achievementList;
    private ArrayList<Flower> flowerList;
    private Flower flower;

    @Generated
    public Plager(){}
}
