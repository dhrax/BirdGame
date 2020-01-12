package com.example.birdgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {

    Bitmap background;
    Bitmap[] bird;
    Bitmap tuboSuperior, tuboInferior;
    Bitmap tuboRojoSuperior, tuboRojoInferior;

    public BitmapBank(Resources resources) {
        this.background = BitmapFactory.decodeResource(resources, R.drawable.background);
        background = scaleImage(background);
        bird = new Bitmap[8];
        bird[0]= BitmapFactory.decodeResource(resources, R.drawable.bird_frame1);
        bird[1]= BitmapFactory.decodeResource(resources, R.drawable.bird_frame2);
        bird[2]= BitmapFactory.decodeResource(resources, R.drawable.bird_frame3);
        bird[3]= BitmapFactory.decodeResource(resources, R.drawable.bird_frame4);
        bird[4]= BitmapFactory.decodeResource(resources, R.drawable.bird_frame5);
        bird[5]= BitmapFactory.decodeResource(resources, R.drawable.bird_frame6);
        bird[6]= BitmapFactory.decodeResource(resources, R.drawable.bird_frame7);
        bird[7]= BitmapFactory.decodeResource(resources, R.drawable.bird_frame8);
        tuboSuperior= BitmapFactory.decodeResource(resources, R.drawable.tube_top);
        tuboInferior= BitmapFactory.decodeResource(resources, R.drawable.tube_bottom);
        tuboRojoSuperior= BitmapFactory.decodeResource(resources, R.drawable.red_tube_top);
        tuboRojoInferior= BitmapFactory.decodeResource(resources, R.drawable.red_tube_bottom);
    }

    public Bitmap getTuboRojoSuperior() {
        return tuboRojoSuperior;
    }

    public Bitmap getTuboRojoInferior() {
        return tuboRojoInferior;
    }

    public Bitmap getTuboSuperior() {
        return tuboSuperior;
    }

    public Bitmap getTuboInferior() {
        return tuboInferior;
    }

    public int getTuboWidth(){
        return tuboSuperior.getWidth();
    }

    public int getTuboHeight(){
        return tuboSuperior.getHeight();
    }

    public Bitmap getBird(int frame) {
        return bird[frame];
    }

    public int getBirdWidth(){
        return bird[0].getWidth();
    }

    public int getBirdHeight(){
        return bird[0].getHeight();
    }

    public Bitmap getBackground() {
        return background;
    }

    public int getBackgroundWidth(){
        return background.getWidth();
    }

    public int getBackgroundHeight(){
        return background.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio= getBackgroundWidth() / getBackgroundHeight();
        //se multiplica widthHeightRatio por la altura para conseguir un Bitmap escalado
        //luego se llamará a createScaledBitmap para obtener un nuevo bitmap escalado
        int backgroundScaledWidth = (int) widthHeightRatio * AppConstants.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaledWidth, AppConstants.SCREEN_HEIGHT, false);
    }
}
