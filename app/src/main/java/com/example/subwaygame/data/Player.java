package com.example.subwaygame.data;

import org.litepal.crud.LitePalSupport;

import java.util.List;

public class Player extends LitePalSupport {
    private String name;
    private String headPicture;
    private List<String> achievementList;
    private int flowers;
    private int waterNumber;
    private int fertilizerNumber;
    private int waterTimes;
    private int cutTimes;

    public Player(){}


    public Player(String name, String headPicture, List<String> achievementList, int flowers, int waterNumber, int fertilizerNumber,int waterTimes,int cutTimes) {
        this.name = name;
        this.headPicture = headPicture;
        this.achievementList = achievementList;
        this.flowers = flowers;
        this.waterNumber = waterNumber;
        this.fertilizerNumber = fertilizerNumber;
        this.waterTimes = waterTimes;
        this.cutTimes = cutTimes;
    }

    public int getFertilizerNumber() {
        return fertilizerNumber;
    }

    public void setFertilizerNumber(int fertilizerNumber) {
        this.fertilizerNumber = fertilizerNumber;
    }

    public int getFlowers() {
        return flowers;
    }

    public void setFlowers(int flowers) {
        this.flowers = flowers;
    }

    public int getWaterNumber() {
        return waterNumber;
    }

    public void setWaterNumber(int waterNumber) {
        this.waterNumber = waterNumber;
    }

    public int getWaterTimes() {return waterTimes; }

    public void setWaterTimes(int waterTimes) { this.waterTimes = waterTimes; }

    public int getCutTimes() {return cutTimes; }

    public void setCutTimes(int cutTimes) { this.cutTimes = cutTimes; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public List<String> getAchievementList() {
        return achievementList;
    }

    public void setAchievementList(List<String> achievementList) {
        this.achievementList = achievementList;
    }

    public void changeAchievementList(int pos,String string){
        if(this.achievementList.size()>pos)
        this.achievementList.set(pos, string);
    }

}
