package com.example.birdgame;

public class BackgroundImage {

    private int backgroundImageX,backgroundImageY, backgroundImageVelocity;

    public BackgroundImage() {
        backgroundImageX=0;
        backgroundImageY = 0;
        backgroundImageVelocity=5;
    }

    public int getBackgroundImageX() {
        return backgroundImageX;
    }

    public void setBackgroundImageX(int backgroundImageX) {
        this.backgroundImageX = backgroundImageX;
    }

    public int getBackgroundImageY() {
        return backgroundImageY;
    }

    public void setBackgroundImageY(int backgroundImageY) {
        this.backgroundImageY = backgroundImageY;
    }

    public int getBackgroundImageVelocity() {
        return backgroundImageVelocity;
    }
}
