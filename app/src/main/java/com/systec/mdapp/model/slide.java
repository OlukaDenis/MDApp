package com.systec.mdapp.model;

public class slide {
    private int Image;
    private String Title;

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public slide(int image, String title) {
        Image = image;
        Title = title;
    }
}
