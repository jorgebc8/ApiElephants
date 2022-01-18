package com.example.apielephants.controlador;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.apielephants.R;
import com.example.apielephants.modelo.modelo.Usuario;


public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{
EditText txtUsuario1,txtContraseña,txtNombre,txtApellidos;
Button btnRegistrarse;
Control_Usuario cu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtUsuario1 = (EditText) findViewById(R.id.txtUsuario1);
        txtContraseña = (EditText) findViewById(R.id.txtContraseña);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);
        btnRegistrarse.setOnClickListener(this);
        cu = new Control_Usuario(this);

    // Ajustamos las preferencias dentro del Oncreate para que aparezca cada vez que entramos en registro

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String nombre1 = sharedPreferences.getString("key_nombre","Jorge");
        String nombre2 = sharedPreferences.getString("key_apellido","Bermudez");
        txtNombre.setText(nombre1);
        txtApellidos.setText(nombre2);
    }

    //Metodo para que regrese al login

    public void VolverInicioSesion(View view){
        Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    //Cuando vuelvas a la aplicación te cargue las preferencias tambien

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String nombre1 = sharedPreferences.getString("key_nombre","Jorge");
        String nombre2 = sharedPreferences.getString("key_apellido","Bermudez");
        txtNombre.setText(nombre1);
        txtApellidos.setText(nombre2);
    }

//Para programar lo que quiere hacer cuando pulsa el boton y dependiendo de los campos hace diversas opciones

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.btnRegistrarse){
            Usuario u = new Usuario();
            u.setUsuario(txtUsuario1.getText().toString());
            u.setContraseña(txtContraseña.getText().toString());
            u.setNombre(txtNombre.getText().toString());
            u.setApellidos(txtApellidos.getText().toString());
            if(!u.isNull()){
                Toast.makeText(this, "ERROR: Campos Vacios", Toast.LENGTH_LONG).show();
            }else if(cu.insertarUsuario(u)){
                Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_LONG).show();
                Intent i2 = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(i2);
                finish();
            }else{
                Toast.makeText(this, "Este Usuario ya existe", Toast.LENGTH_LONG).show();
            }
        }
    }
}