package com.example.apielephants.controlador;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.apielephants.R;

public class DescripcionActivity extends AppCompatActivity {

    //Declaramos nuestras variables globales
    TextView txt_DescripcionNombre;
    TextView txt_Descripcionespecie;
    ImageView imageDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);

        //Unimos las variables con el layout

        txt_DescripcionNombre = findViewById(R.id.txt_DescripcionNombre);
        txt_Descripcionespecie = findViewById(R.id.txt_Descripcionespecie);
        imageDescripcion = findViewById(R.id.imageDescripcion);

        //Recibe los datos para mostrarlos en esta actividad

        Intent i = getIntent();

        String imagen = i.getStringExtra("foto");
        String name = i.getStringExtra("nombre");
        String especie = i.getStringExtra("especie");

        txt_DescripcionNombre.setText(name);
        txt_Descripcionespecie.setText(especie);

        //Recibimos la imagen

        Glide.with(getApplicationContext())
                .load(imagen)
                .into(imageDescripcion);



    }
}
