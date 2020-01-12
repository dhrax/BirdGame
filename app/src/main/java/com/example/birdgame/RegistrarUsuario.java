package com.example.birdgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrarUsuario extends AppCompatActivity implements View.OnClickListener {

    EditText etCrearContraseña, etRepiteContraseñaCreada;
    TextView txvSignUpVerOcultarNuevaContraseña, txvSignUpVerOcultarRepiteContraseña;
    Button btRegistrarUsuario, btIrALogIn, btSignUpAcercaDe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        etCrearContraseña = findViewById(R.id.etCrearContraseña);
        etRepiteContraseñaCreada = findViewById(R.id.etRepiteContraseñaCreada);
        txvSignUpVerOcultarNuevaContraseña = findViewById(R.id.txvSignUpVerOcultarNuevaContraseña);
        txvSignUpVerOcultarNuevaContraseña.setOnClickListener(this);
        txvSignUpVerOcultarRepiteContraseña = findViewById(R.id.txvSignUpVerOcultarRepiteContraseña);
        txvSignUpVerOcultarRepiteContraseña.setOnClickListener(this);

        btRegistrarUsuario = findViewById(R.id.btRegistrarUsuario);
        btRegistrarUsuario.setOnClickListener(this);
        btIrALogIn = findViewById(R.id.btIrALogIn);
        btIrALogIn.setOnClickListener(this);
        btSignUpAcercaDe = findViewById(R.id.btSignUpAcercaDe);
        btSignUpAcercaDe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btRegistrarUsuario:

                EditText etCrearUsuario = findViewById(R.id.etCrearUsuario);//te amo

                String nomUsuario = etCrearUsuario.getText().toString().toLowerCase();

                Database db = new Database(this);
                if(db.existeUsuario(nomUsuario)>0){
                    Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();
                    return;
                }

                String nuevaContraseña = etCrearContraseña.getText().toString().toLowerCase();
                String repiteContraseña = etRepiteContraseñaCreada.getText().toString().toLowerCase();
                if(!Util.contraseñaPermitida(nuevaContraseña)){
                    Toast.makeText(getApplicationContext(), "La contraseña no cumple los requisitos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (repiteContraseña.equals("")) {
                    Toast.makeText(getApplicationContext(), "Repita la contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!repiteContraseña.equals(nuevaContraseña)) {
                    Toast.makeText(getApplicationContext(), "Las contraseñas son diferentes", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.nuevoUsuario(nomUsuario, nuevaContraseña);
                db.close();
                Intent intentEmpezarJuego = new Intent(this, BeginActivty.class);
                startActivity(intentEmpezarJuego);
                break;

            case R.id.btIrALogIn:
                Intent intentIrALogin = new Intent(this, LoginActivity.class);
                startActivity(intentIrALogin);
                break;

            case R.id.btSignUpAcercaDe:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("BirdGame es un juego creado por DAISA GAMES LTD.\nDesarrollado por:" +
                        "\nValery Isabel Cortez Fernández\nDavid Fernández Liras").setTitle("Acerca de...")
                        .setNegativeButton("Cerrar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Qué hacer si el usuario pulsa "No"
                                        // En este caso se cierra directamente el diálogo y no se hace nada más
                                        dialog.dismiss();
                                    }});

                builder.create().show();

                break;

            case R.id.txvSignUpVerOcultarNuevaContraseña:
                Util.toggleVerOcultarCntraseña(etCrearContraseña, txvSignUpVerOcultarNuevaContraseña);
                break;

            case R.id.txvSignUpVerOcultarRepiteContraseña:
                Util.toggleVerOcultarCntraseña(etRepiteContraseñaCreada, txvSignUpVerOcultarRepiteContraseña);
                break;

            default:
                break;
        }
    }
}
