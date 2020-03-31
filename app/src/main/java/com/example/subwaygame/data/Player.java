package com.example.subwaygame.data;

import java.util.List;

public class Player {
    private String name;
    private String headPicture;
    private List<String> achievementList;
    private int flowers;
    private int waterNumber;
    private int fertilizerNumber;
    private Flower flower;

    public Player(String name, String headPicture, List<String> achievementList, int flowers, int waterNumber, int fertilizerNumber, Flower flower) {
        this.name = name;
        this.headPicture = headPicture;
        this.achievementList = achievementList;
        this.flowers = flowers;
        this.waterNumber = waterNumber;
        this.fertilizerNumber = fertilizerNumber;
        this.flower = flower;
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

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }
}
