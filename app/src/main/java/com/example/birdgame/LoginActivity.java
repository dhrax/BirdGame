package com.example.birdgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout rLayout1, rLayout2;
    Button btLogin, btIrARegistrarUsuario, btOlvidarContrasena;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rLayout1.setVisibility(View.VISIBLE);
            rLayout2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Database db = new Database(this);
        db.nuevoUsuario("val", "123");

        rLayout1 = findViewById(R.id.rLayout1);
        rLayout2 = findViewById(R.id.rLayout2);

        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);
        btIrARegistrarUsuario = findViewById(R.id.btIrARegistrarUsuario);
        btIrARegistrarUsuario.setOnClickListener(this);
        btOlvidarContrasena = findViewById(R.id.btOlvidarContrasena);
        btOlvidarContrasena.setOnClickListener(this);

        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btLogin:
                EditText etLogin = findViewById(R.id.etLogin);
                EditText etContraseña = findViewById(R.id.etContraseña);

                String nomUsuario = etLogin.getText().toString().toLowerCase();
                String contraseña = etContraseña.getText().toString().toLowerCase();
                if(!Util.contraseñaPermitida(contraseña)){
                    Toast.makeText(getApplicationContext(), "La contraseña no cumple los requisitos", Toast.LENGTH_SHORT).show();
                    return;
                }

                Database db = new Database(this);
                int res = db.comprobarUsuarioContraseña(nomUsuario, contraseña);
                switch(res){

                    case 0:
                        Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                        Util.intentosUsuarioFallido++;
                        if(Util.intentosUsuarioFallido>=3){
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Parece que no está registrado, ¿desea registrarse?").setTitle("Usuario no registrado")
                                    .setPositiveButton("Si",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(getApplicationContext(), RegistrarUsuario.class);
                                                    startActivity(intent);
                                                    Util.intentosUsuarioFallido=0;
                                                    dialog.dismiss();
                                                }})
                                    .setNegativeButton("No",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // Qué hacer si el usuario pulsa "No"
                                                    // En este caso se cierra directamente el diálogo y no se hace nada más
                                                    Util.intentosUsuarioFallido=0;
                                                    dialog.dismiss();
                                                }});

                            builder.create().show();


                        }
                        return;

                    case 1:
                        Intent intent = new Intent(this, BeginActivty.class);
                        startActivity(intent);
                        return;

                    case 2:
                        Toast.makeText(getApplicationContext(), "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                        Util.intentosContraseñaFallido++;
                        if(Util.intentosContraseñaFallido>=5){
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Parece que ha olvidado su contraseña, ¿desea cambiarla?").setTitle("Contraseña errónea")
                                    .setPositiveButton("Si",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    Intent intent = new Intent(getApplicationContext(), ResetearContrasenha.class);
                                                    startActivity(intent);
                                                    Util.intentosContraseñaFallido=0;
                                                    dialog.dismiss();
                                                }})
                                    .setNegativeButton("No",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // Qué hacer si el usuario pulsa "No"
                                                    // En este caso se cierra directamente el diálogo y no se hace nada más
                                                    Util.intentosContraseñaFallido=0;
                                                    dialog.dismiss();
                                                }});

                            builder.create().show();


                        }
                        return;

                    default:
                        return;
                }
            case R.id.btIrARegistrarUsuario:
                Intent intentRegistrarUsuario = new Intent(this, RegistrarUsuario.class);
                startActivity(intentRegistrarUsuario);

                break;

            case R.id.btOlvidarContrasena:
                Intent intent = new Intent(this, ResetearContrasenha.class);
                startActivity(intent);
                break;

            default:

                break;
        }
    }
}
