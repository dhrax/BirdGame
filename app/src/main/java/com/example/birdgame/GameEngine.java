package com.example.birdgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {

    BackgroundImage backgroundImage;
    Bird bird;
    public enum Estado{
        PAUSA, JUGANDO, ACABADO;
    }
    public Estado estado;
    ArrayList<Tubo> tubos;
    Random rand;
    int puntuacion;
    int tuboPuntuacion; //tubo que me va a aumentar al puntuacin
    Paint puntuacionPaint;

    public GameEngine() {
        backgroundImage = new BackgroundImage();
        //creamnos el pajaro
        bird = new Bird();
        estado = Estado.PAUSA;
        tubos = new ArrayList<>();
        rand = new Random();
        for(int i=0; i<AppConstants.numeroTubos; i++){
            //los tubos se generan con una separacion dependiente de AppConstants.distanciaEntreTubos
            int tuboX = AppConstants.SCREEN_WIDTH + i * AppConstants.distanciaEntreTubos;
            int topTuboOffsetY = AppConstants.minTuboOffsetY + rand.nextInt(AppConstants.maxTuboOffsetY - AppConstants.minTuboOffsetY + 1);
            Tubo tubo = new Tubo(tuboX, topTuboOffsetY);
            tubos.add(tubo);
        }
        puntuacion=0;
        tuboPuntuacion=0;
        puntuacionPaint= new Paint();
        puntuacionPaint.setColor(Color.RED);
        puntuacionPaint.setTextSize(100);
        puntuacionPaint.setTextAlign(Paint.Align.LEFT);
    }

    public void updateAndDrawTubos(Canvas canvas){
        if(estado==Estado.JUGANDO){
            //deteccion de colisiones

            if((tubos.get(tuboPuntuacion).getTuboX() < bird.getBirdX() + AppConstants.getBitmapBank().getBirdWidth())
                    && (tubos.get(tuboPuntuacion).getTopTuboOffsetY() > bird.getBirdY()
                    || tubos.get(tuboPuntuacion).getBottomTubeY() < (bird.getBirdY() +
                    AppConstants.getBitmapBank().getBirdHeight()))){
                //Se acaba la partida
                estado=Estado.ACABADO;

                Context context = AppConstants.gameAtivityContext;
                Intent intent = new Intent(context, GameOver.class);
                intent.putExtra("score", puntuacion);
                context.startActivity(intent);
                ((Activity) context).finish();

            }

            if(tubos.get(tuboPuntuacion).getTuboX() < bird.getBirdX() - AppConstants.getBitmapBank().getTuboWidth()){
                puntuacion++;
                tuboPuntuacion++;
                if(tuboPuntuacion > AppConstants.numeroTubos - 1){
                    tuboPuntuacion =0;
                }
            }
            for(int i =0; i<AppConstants.numeroTubos; i++){
                if(tubos.get(i).getTuboX() < -AppConstants.getBitmapBank().getTuboWidth()){
                    tubos.get(i).setTuboX(tubos.get(i).getTuboX() + AppConstants.numeroTubos * AppConstants.distanciaEntreTubos);
                    int topTuboOffsetY = AppConstants.minTuboOffsetY + rand.nextInt(AppConstants.maxTuboOffsetY - AppConstants.minTuboOffsetY +1);
                    tubos.get(i).setTopTuboOffsetY(topTuboOffsetY);
                    tubos.get(i).setColorTubo();

                }
                tubos.get(i).setTuboX(tubos.get(i).getTuboX() - AppConstants.velocidadTubo);
                if(tubos.get(i).getColorTubo() ==0){
                    canvas.drawBitmap(AppConstants.getBitmapBank().getTuboSuperior(), tubos.get(i).getTuboX(), tubos.get(i).getTopTubeY(), null);
                    canvas.drawBitmap(AppConstants.getBitmapBank().getTuboInferior(), tubos.get(i).getTuboX(), tubos.get(i).getBottomTubeY(), null);
                }else{
                    canvas.drawBitmap(AppConstants.getBitmapBank().getTuboRojoSuperior(), tubos.get(i).getTuboX(), tubos.get(i).getTopTubeY(), null);
                    canvas.drawBitmap(AppConstants.getBitmapBank().getTuboRojoInferior(), tubos.get(i).getTuboX(), tubos.get(i).getBottomTubeY(), null);
                }
            }
            canvas.drawText("Pt: "+puntuacion, 0, 110, puntuacionPaint);
        }
    }

    public void updateAndDrawBackgroundImage(Canvas canvas){
        backgroundImage.setBackgroundImageX(backgroundImage.getBackgroundImageX() - backgroundImage.getBackgroundImageVelocity());
        if(backgroundImage.getBackgroundImageX() < -AppConstants.getBitmapBank().getBackgroundWidth()){
            backgroundImage.setBackgroundImageX(0);
        }
        //Se consigue que la llegar al final de la imagen, la transición sea limpia
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getBackgroundImageX(), backgroundImage.getBackgroundImageY(), null);
        if(backgroundImage.getBackgroundImageX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)){
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getBackgroundImageX() + AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getBackgroundImageY(), null);
        }
    }

    public void updateAndDrawBird(Canvas canvas){

        if(estado == Estado.JUGANDO){
            //vamos colocando al pajaro en diferentes partes de la pantalla
            //en cada iteración de bucle la Y del pájaro es diferente
            //1º iteracion: Velocidad = (0 + 3) Y= (0+3)
            //2º iteracion: Velocidad = (3 + 3) Y= (3+3)
            //3º iteracion: Velocidad = (6 + 3) Y= (3+6)
            if(bird.getBirdY() < (AppConstants.SCREEN_HEIGHT + AppConstants.getBitmapBank().getBirdHeight())
                    || bird.getVelocidad() <0){
                bird.setVelocidad(bird.getVelocidad() + AppConstants.gravity);
                bird.setBirdY(bird.getBirdY() + bird.getVelocidad());
            }

            if(bird.getBirdY() <=0){
                bird.setBirdY(0);
            }
            if(bird.getBirdY() >= (AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getBirdHeight())){
                bird.setBirdY(AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getBirdHeight());
            }

            Log.e("Posicion", String.valueOf(bird.getBirdY()));

        }
        int currentFrame = bird.getCurrentFrame();
        //Pintamos el pájaro
        canvas.drawBitmap(AppConstants.getBitmapBank().getBird(currentFrame), bird.getBirdX(), bird.getBirdY(), null);
        currentFrame++;
        if(currentFrame > bird.maxFrame)
            currentFrame=0;

        bird.setCurrentFrame(currentFrame);

    }
}
