package com.example.birdgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import static android.provider.BaseColumns._ID;
import static com.example.birdgame.AppConstants.BBDD;
import static com.example.birdgame.AppConstants.TABLA_USUARIOS;
import static com.example.birdgame.AppConstants.USUARIO;
import static com.example.birdgame.AppConstants.CONTRASEÑA;


public class Database extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private final String[] SELECT_USUARIO = new String[] {USUARIO};

    public Database(Context context) {
        super(context, BBDD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE "+TABLA_USUARIOS+";");
        db.execSQL("CREATE TABLE "+ TABLA_USUARIOS + "("+ _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                USUARIO + " TEXT, "+CONTRASEÑA +" TEXT"+");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     *
     * @param usuario
     * @param contraseña
     * @return  0: Usuario no existe
     *          1: Usuario y contraseña correctos
     *          2: Contraseña incorrecta
     */
    public int comprobarUsuarioContraseña(String usuario, String contraseña){
        int res=0;

        if(existeUsuario(usuario)>0){
            if(contraseñaCorrecta(usuario, contraseña)>0)
                res=1;
            else
                res=2;
        }
        Log.d("Resultado", String.valueOf(res));
        return res;
    }

    public void actualizarUsuario(){


    }

    public long existeUsuario(String usuario){
        SQLiteDatabase db = getReadableDatabase();
        String[] usuarios = new String[]{usuario};

        return DatabaseUtils.queryNumEntries(db, TABLA_USUARIOS,
                "usuario=?", usuarios);
    }


    public void nuevoUsuario(String usuario, String contraseña){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USUARIO, usuario);
        values.put(CONTRASEÑA, contraseña);
        db.insertOrThrow(TABLA_USUARIOS, null, values);
        db.close();
    }

    public long contraseñaCorrecta(String usuario, String contraseña){
        SQLiteDatabase db = getReadableDatabase();
        String[] datos = new String[]{usuario, contraseña};

        return DatabaseUtils.queryNumEntries(db, TABLA_USUARIOS,
                "usuario=? and contraseña=?", datos);
    }
}
