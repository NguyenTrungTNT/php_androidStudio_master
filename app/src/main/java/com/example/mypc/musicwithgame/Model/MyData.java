package com.example.mypc.musicwithgame.Model;

/**
 * Created by MyPC on 05/12/2017.
 */
public class MyData {

    private int id;
    private String description,image_link;
    private String mp3;

    public MyData(int id, String description, String image_link) {
        this.id = id;
        this.description = description;
        this.image_link = image_link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }
}
