package com.example.birdgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class BeginActivty extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        //Esto se puede hacer mediante la propiedad onClick del layout
        //de esta forma podemos llamar a una fncion que se ejecute cuadno se pulsa
        ImageButton playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(this);

        AppConstants.inicializacion(this.getApplicationContext());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.playButton:
                //escribe en el logcat/verbose
                Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
