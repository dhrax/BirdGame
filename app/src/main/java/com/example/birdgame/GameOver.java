package com.example.birdgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity implements View.OnClickListener{

    TextView texvPuntAc, txvMejorPunt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        int score = getIntent().getIntExtra("score", -1);
        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        int scoreSP = pref.getInt("scoreSP", -1);
        SharedPreferences.Editor editor = pref.edit();
        if(score > scoreSP) {
            scoreSP = score;
            editor.putInt("scoreSP", scoreSP);
            editor.commit();
        }
        texvPuntAc = findViewById(R.id.texvPuntAc);
        txvMejorPunt = findViewById(R.id.txvMejorPunt);

        Button btRestart = findViewById(R.id.btRestart);
        Button btExit = findViewById(R.id.btExit);
        btRestart.setOnClickListener(this);
        btExit.setOnClickListener(this);

        texvPuntAc.setText(String.valueOf(score));
        txvMejorPunt.setText(String.valueOf(scoreSP));
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.btRestart:
                Intent intent = new Intent(this, BeginActivty.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btExit:
                finish();
                break;
        }
    }
}
