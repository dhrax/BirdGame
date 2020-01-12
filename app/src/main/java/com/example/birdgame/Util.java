package com.example.birdgame;

import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.TextView;

public class Util {
    public static int intentosUsuarioFallido=0;
    public static int intentosContraseñaFallido=0;

    /**
     *
     * @param contraseña
     * @return True, si la contraseña cumple los requerimentos
     *          False si no los cumple
     */
    public static boolean contraseñaPermitida(String contraseña){

        if(!contraseña.equals(""))
            if(!contraseña.contains("@"))
                return true;

        return false;
    }

    /**
     *
     * @param usuario
     * @return True, si el usuario cumple con los requerimentos
     *          False, si no los cumple
     */
    public static boolean usuarioPermitido(String usuario){
        return false;
    }

    /**
     *
     * @param contraseña
     * @param boton
     *
     * Toggle que permite mostrar o no la contraseña en el EditText pasado por parámetro
     */
    public static void toggleVerOcultarCntraseña(EditText contraseña, TextView boton){

        String valor = boton.getText().toString();
        if(valor.equals("MOSTRAR")){
            contraseña.setTransformationMethod(null);
            boton.setText("OCULTAR");
        }else{
            contraseña.setTransformationMethod(new PasswordTransformationMethod());
            boton.setText("MOSTRAR");
        }
    }
}
