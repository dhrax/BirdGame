package com.example.birdgame;

import java.util.Random;

public class Tubo {

    private int tuboX, topTuboOffsetY, colorTubo; //coordenada de la parte de abajo del tubo superior
    private Random rand;
    public Tubo(int tuboX, int topTuboOffsetY) {
        this.tuboX = tuboX;
        this.topTuboOffsetY = topTuboOffsetY;
        rand = new Random();
    }

    public void setColorTubo(){
        this.colorTubo = rand.nextInt(2);
    }

    public int getColorTubo() {
        return colorTubo;
    }

    public int getTuboX() {
        return tuboX;
    }

    public void setTuboX(int tuboX) {
        this.tuboX = tuboX;
    }

    public int getTopTuboOffsetY() {
        return topTuboOffsetY;
    }

    public void setTopTuboOffsetY(int topTuboOffsetY) {
        this.topTuboOffsetY = topTuboOffsetY;
    }

    public int getTopTubeY(){
        return topTuboOffsetY - AppConstants.getBitmapBank().getTuboHeight();
    }

    public int getBottomTubeY(){
        return topTuboOffsetY + AppConstants.gapBetweenTopAndbottomtubes;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }
}
