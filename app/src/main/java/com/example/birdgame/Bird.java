package com.example.birdgame;

public class Bird {

    private int birdX, birdY, currentFrame, velocidad;
    public static int maxFrame;

    public Bird() {
        //creamos el pájaro en mitad de la pantalla al principio de la partida
        birdX = AppConstants.SCREEN_WIDTH/2 - AppConstants.getBitmapBank().getBirdWidth()/2;
        birdY = AppConstants.SCREEN_HEIGHT/2 - AppConstants.getBitmapBank().getBirdHeight()/2;
        currentFrame =0;
        maxFrame = 7;
        velocidad = 0;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getBirdX() {
        return birdX;
    }

    public void setBirdX(int birdX) {
        this.birdX = birdX;
    }

    public int getBirdY() {
        return birdY;
    }

    public void setBirdY(int birdY) {
        this.birdY = birdY;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public static int getMaxFrame() {
        return maxFrame;
    }

    public static void setMaxFrame(int maxFrame) {
        Bird.maxFrame = maxFrame;
    }
}
