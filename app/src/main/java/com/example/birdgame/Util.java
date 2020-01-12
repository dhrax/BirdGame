package com.example.birdgame;

public class Util {
    public static int intentosUsuarioFallido=0;
    public static int intentosContraseñaFallido=0;

    public static boolean contraseñaPermitida(String contraseña){

        if(!contraseña.equals(""))
            if(!contraseña.contains("@"))
                return true;

        return false;
    }
}
