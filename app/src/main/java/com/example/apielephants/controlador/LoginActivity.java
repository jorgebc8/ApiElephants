package com.example.apielephants.controlador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apielephants.R;

public class LoginActivity extends AppCompatActivity {
    //Creamos dos objetos
    private EditText etu, etc;
    private Button btnIniciarSesion;
    Control_Usuario cu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Creamos la relación de la parte lógica con la parte gráfica

        etu = (EditText) findViewById(R.id.txtUsuario1);
        etc = (EditText) findViewById(R.id.txtContraseña);
        btnIniciarSesion = (Button) findViewById(R.id.btnRegistrarse);
        cu=new Control_Usuario(this);
    }
    //Creamos el método para el botón Iniciar Sesión donde le pasamos la vista como parámetro y este método se encarga
    //de validar los campos de texto del email y la contraseña para que pueda continuar si ambos campos estan correctos

    public void IniciarSesion(View view) {
        //Dentro de este método pasaremos lo que halla dentro de los dos campos a String para comprobar si estan vacios o no
        //En caso de que estén vacios aparecerá un toast y sino pues podra continuar
        String usuario = etu.getText().toString();
        String contraseña = etc.getText().toString();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Debes completar ambos campos", Toast.LENGTH_LONG).show();
        }else if(cu.login(usuario,contraseña)==1){
            Toast.makeText(this, "Datos Correctos", Toast.LENGTH_LONG).show();
            Intent i3 =new Intent(LoginActivity.this, InicioActivity.class);
            startActivity(i3);
        }else{
            Toast.makeText(this, "El Usuario no existe", Toast.LENGTH_SHORT).show();
        }

        }

        //Haremos otro método que al pulsar la palabra Regístrate te lleve a la pantalla de Registro
        public void registro(View view){
            Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
            startActivity(intent);

        }
    }

