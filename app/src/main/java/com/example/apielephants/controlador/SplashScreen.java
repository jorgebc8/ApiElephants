package com.example.apielephants.controlador;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apielephants.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Creamos un Timer para mostrar esta ventana un determinado tiempo y me implementa el metodo run donde
        voy a pasar a otra ventana con el objeto Intent una vez  que acabe el tiempo que le he puesto con el timer
         al Splash*/
        TimerTask tarea =new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                /*Usamos el startActivity pasandole el intent para que pase a la otra actividad*/
                startActivity(intent);
                /*Cerramos esta actividad para que no se vuelva a mostrar hasta que se inicie otra vez la aplicación*/
                finish();
            }
        };
        /*Declaramos un Timer donde a tarea le indicamos el tiempo que debe mostrar (en milisegundos) el Splash antes de pasar a la otra ventana*/
        Timer tiempo = new Timer();
        tiempo.schedule(tarea,2000);
    }
}