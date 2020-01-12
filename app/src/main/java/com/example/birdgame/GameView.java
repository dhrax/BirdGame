package com.example.birdgame;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static com.example.birdgame.GameEngine.Estado.JUGANDO;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gameThread;

    public GameView(Context context) {
        super(context);
        initView();
    }
    //en esta funcion se inicializa el SurfaceHolder y los Thread objects
    void initView(){
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
    }

    //ponemos a correr el hilo si no está corriendo
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(!gameThread.isRunning()){
            gameThread = new GameThread(holder);
            gameThread.start();
        }else{
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(gameThread.isRunning()){
            gameThread.setRunning(false);
            boolean retry = true;
            while(retry){
                try{
                    gameThread.join();
                    retry=false;
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int  action = event.getAction();
        //Se detecta una pulsación
        if(action == MotionEvent.ACTION_DOWN){
            AppConstants.getGameEngine().estado = JUGANDO;
            AppConstants.getGameEngine().bird.setVelocidad(AppConstants.VELOCIDAD_CUANDO_SALTA);
        }

        return true;
    }
}
