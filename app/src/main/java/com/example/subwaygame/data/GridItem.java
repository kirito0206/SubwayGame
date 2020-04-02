package com.example.subwaygame.data;

public class GridItem {
    private String image;
    private String title;
    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    GridItem(){
        super();
    }
}
