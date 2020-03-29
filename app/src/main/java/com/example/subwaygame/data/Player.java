package com.example.subwaygame.data;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private String headPicture;
    private List<String> achievementList;
    private List<Flower> flowerList;
    private Flower flower;

    public Player(String name, String hp, ArrayList<String> achievements, ArrayList<Flower> flowers, Flower flower) {
        setName(name);
        setHeadPicture(hp);
        setAchievementList(achievements);
        setFlowerList(flowers);
        setFlower(flower);
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

    public List<Flower> getFlowerList() {
        return flowerList;
    }

    public void setFlowerList(List<Flower> flowerList) {
        this.flowerList = flowerList;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }
}
