package com.example.birdgame;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

public class GameThread extends Thread {
    SurfaceHolder holder;
    boolean isRunning; //Flag para que el hilo corra o no
    long startTime, loopTime;
    final long DELAY = 33; //delay entre refrescos de pantalla

    public GameThread(SurfaceHolder holder) {
        this.holder = holder;
        isRunning=true;
    }

    @Override
    public void run() {
        //Loop mientras que el boolea sea true
        while(isRunning){
            startTime = SystemClock.uptimeMillis();
            //locking canvas
            Canvas canvas = holder.lockCanvas(null);
            if(canvas != null){
                synchronized (holder){
                    //se pinta lo que hay en la pantalla
                    AppConstants.getGameEngine().updateAndDrawBackgroundImage(canvas);
                    AppConstants.getGameEngine().updateAndDrawBird(canvas);
                    AppConstants.getGameEngine().updateAndDrawTubos(canvas);
                    //unlock canvas
                    holder.unlockCanvasAndPost(canvas);

                }
            }
            loopTime = SystemClock.uptimeMillis() - startTime;
            //Se pause para asegurar de que se refresca bien
            if(loopTime < DELAY){
                try{
                    sleep(DELAY-loopTime);

                }catch(InterruptedException ie){
                    Log.e("Interrumpido", "Interrumpido mientras duerme");
                }
            }

        }

    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
