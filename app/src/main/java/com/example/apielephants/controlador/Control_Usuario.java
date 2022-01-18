package com.example.apielephants.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apielephants.modelo.modelo.Usuario;

import java.util.ArrayList;

public class Control_Usuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd = "BDUsuarios";
    String tabla = "create table if not exists usuario(id integer primary key autoincrement, usuario text,contraseña text,nombre text, apellidos text)";

    public Control_Usuario(Context c) {
        this.c = c;
        //A continuación creamos un comando que sirve para crear o abrir la base de datos y ejecutamos un comando
        //para crear la tabla
        sql = c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u = new Usuario();
    }

    //Utiliaremos la clase contentValues para retener los valores de un solo registro y luego insertarlos en la
    //tabla usuario

    public boolean insertarUsuario(Usuario u) {
        //Si el usuario que buscamos es igual a 0 significa que no existe por lo cuál podemos crearlo(El método
        //buscar está debajo).
        if (buscar(u.getUsuario()) == 0) {
            ContentValues cv = new ContentValues();
            cv.put("usuario", u.getUsuario());
            cv.put("contraseña", u.getContraseña());
            cv.put("nombre", u.getNombre());
            cv.put("apellidos", u.getApellidos());
            return (sql.insert("usuario", null, cv) > 0);
        } else {
            return false;
        }
    }

    public int buscar(String u) {
        int x = 0;
        lista = selectUsuarios();
        //Recorremos el ArrayList con un bucle donde comprobaremos que el usuario que tenemos en la base de datos
        //no es igual al que estamos recibiendo por lo cual si me devuelve un 1 en la x significa que ya existe
        //y si devuelve 0 significa que no existe
        for (Usuario us : lista) {
            if (us.getUsuario().equals(u)) {
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        Cursor cr = sql.rawQuery("select * from usuario", null);
        //Comprobamos que el cursor no sea nulo y lo ponemos enla primera fila de la tabla de resultado
        //Y si nos devuelve algún registro lo extraeremos y lo pasaremos a nuestra lista
        if (cr != null && cr.moveToFirst()) {
            do {
                Usuario u = new Usuario();
                //Indica en la posición en la que se encuentran según el campo que les corresponda
                u.setId(cr.getInt(0));
                u.setUsuario(cr.getString(1));
                u.setContraseña(cr.getString(2));
                u.setNombre(cr.getString(3));
                u.setApellidos(cr.getString(4));
                //Ahora los añadimos a la lista despues de haberlos extraido
                lista.add(u);
            } while (cr.moveToNext());
        }
        return lista;
    }

    public int login(String usuario, String contraseña) {
        int a = 0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                if (cr.getString(1).equals(usuario) && cr.getString(2).equals(contraseña)) {
                    a++;
                }
            } while (cr.moveToNext());
        }
        return a;
    }
}
