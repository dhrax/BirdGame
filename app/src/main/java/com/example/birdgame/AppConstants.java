package com.example.birdgame;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants {

    static BitmapBank bitmapBank;
    static GameEngine gameEngine;
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int VELOCIDAD_CUANDO_SALTA;
    static int gapBetweenTopAndbottomtubes;
    static int numeroTubos;
    static int velocidadTubo;
    static int minTuboOffsetY;
    static int maxTuboOffsetY;
    static int distanciaEntreTubos;
    static Context gameAtivityContext;

    //Constantes de BBDD
    public final static String BBDD = "bird_game.db";
    public final static String TABLA_USUARIOS = "puntuaciones";
    public final static String USUARIO = "usuario";
    public final static String CONTRASEÑA = "contraseña";



    public static void inicializacion(Context context){
        setScreenSize(context);
        bitmapBank = new BitmapBank(context.getResources());
        setGameConstants();
        gameEngine = new GameEngine();

    }

    public static void setGameConstants(){
        AppConstants.gravity=3;
        AppConstants.VELOCIDAD_CUANDO_SALTA=-40;
        gapBetweenTopAndbottomtubes= 600;
        AppConstants.numeroTubos = 2;
        AppConstants.velocidadTubo = 12;
        AppConstants.minTuboOffsetY = (int) (AppConstants.gapBetweenTopAndbottomtubes/2.0);
        AppConstants.maxTuboOffsetY = AppConstants.SCREEN_HEIGHT - AppConstants.minTuboOffsetY - AppConstants.gapBetweenTopAndbottomtubes;
        AppConstants.distanciaEntreTubos = AppConstants.SCREEN_WIDTH * 3/4;
    }

    public static BitmapBank getBitmapBank(){
        return bitmapBank;
    }

    public static GameEngine getGameEngine() {
        return gameEngine;
    }

    //hace que la imagen de fondo ocupe toda la pantalla
    public static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;

    }
}
