package com.example.apielephants.controlador;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.apielephants.adaptador.RecyclerAdapter;
import com.example.apielephants.modelo.modelo.Elefantes;
import com.example.apielephants.R;
import com.example.apielephants.io.ConectarApiElefantes;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import android.view.ActionMode;
/**
 * Actividad que explica los componentes necesarios para el uso de listas con RecyclerView.
 * <p>
 * Organización previa:
 * <p>
 * 1.- Se debe tener en cuenta la organización del proyecto separado en paquetes que sigan el patrón MVC
 *
 * @author JC
 * @version 0.1
 */
public class InicioActivity extends AppCompatActivity {

    // Se declaran los tipos de datos RecyclerView, que engloba la lista, y RecyclerAdapter, que enlazará los datos
    // con cada elemento de la lista. En este caso RecyclerAdapter es una clase que debemos impelementar nosotros.
    private RecyclerView recyclerView;
    public ArrayList<Elefantes> listElefante = new ArrayList<>();
    RecyclerAdapter adapter;
    private RecyclerAdapter.RecyclerViewClickListener listener;
    private RecyclerAdapter.RecyclerViewClickListener1 listener1;
    private ActionMode actionMode;
    Elefantes e = new Elefantes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        // Se inicializan los objetos recyclerView y recyclerAdapter, pasandole
        // a este último la lista
        recyclerView = (RecyclerView) findViewById(R.id.recView);
        new LeerJson().execute();

        setOnClickListener();
        setOnLongClickListener();
        // La clase layaoutManager se encarga de gestionar la disposición de los elementos
        // de la lista dentro del recyclerView. Existen diferentes opciones, como gridLayoutManager
        // que los coloca en vista de rejilla. Para este caso se ha usado Linear, para una
        // disposición básica (un elemento debajo de otro).
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        adapter = new RecyclerAdapter(listElefante,getApplicationContext(),listener,listener1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

    }

    public void setOnClickListener() {
        listener = new RecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(InicioActivity.this, DescripcionActivity.class);
                i.putExtra("foto", listElefante.get(recyclerView.getChildAdapterPosition(view)).getImagenId());
                i.putExtra("nombre", listElefante.get(recyclerView.getChildAdapterPosition(view)).getNombre());
                i.putExtra("especie", listElefante.get(recyclerView.getChildAdapterPosition(view)).getEspecie());
                startActivity(i);
            }
        };
    }

    public void setOnLongClickListener(){
        listener1 = new RecyclerAdapter.RecyclerViewClickListener1() {
            @Override
            public boolean onLongClick(View view, int position) {
                if(actionMode != null){
                    return false;
                }else{
                    actionMode = startActionMode(actioncallback);
                    e = listElefante.get(position);
                    return true;
                }
            }
        };
    }

    // Como no sabemos  recuperar datos desde una base de datos se ha creado un
    // método auxiliar que emule la obtención de datos. Para ello se han importado diferentes imágenes
    // en la carpeta Drawable y se han creado diferentes objetos de la clase Peliculas almacenandolas
    // en un arrayList que posteriormente será devuelto.

    public class LeerJson extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String resultado;
            resultado = ConectarApiElefantes.getRequest();
            return resultado;
        }

        @Override
        protected void onPostExecute(String s) {
            try {

                if (s != null) {


                    // La respuesta que nos devuelve es un texto en formato JSON. Para ello,
                    // en este caso, haremos uso de las clases que nos proporciona Android.
                    JSONArray jsonArray = new JSONArray(s);

                    //JSONArray jsonArray1 = jsonArray.getJSONArray(0);


                    String name = "", especie = "", imagen = "";
                    for (int i = 0; i < 20; i++) {
                        name = jsonArray.getJSONObject(i).getString("name");
                        especie = jsonArray.getJSONObject(i).getString("species");
                        imagen = jsonArray.getJSONObject(i).getString("image");

                        listElefante.add(new Elefantes(name, especie, imagen));
                        Log.d("zzzz", "DATOS: " + listElefante.get(i).getImagenId());
                    }

                    // Una vez tenemos los datos en nuestra colección debemos avisar al
                    // adaptador que la información ha cambiado.
                    adapter.notifyDataSetChanged();
                    Log.d("D", "Array: " + listElefante.toString());


                } else {
                    Toast.makeText(getApplicationContext(), "Problema al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //Método para mostrar y ocultar el menú
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Método para asignar las funciones correspondientes a las opciones que en este caso es solo una, los ajustes.
    public boolean onOptionsItemSelected(MenuItem items) {
        int id = items.getItemId();

        if (id == R.id.idAjustes) {
            AlertDialog alertDialog = createAlertDialog("Ajustes", "¿Quieres ir a los ajustes?");
            alertDialog.show();
        }

        return super.onOptionsItemSelected(items);
    }

    //Metodo para crear el AlertDialog

    public AlertDialog createAlertDialog(String titulo, String mensaje) {
        // Creamos un 'builder' o constructor que nos ayude a configurar el cuadro de dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(InicioActivity.this);

        //  Este objeto builder nos permitirá añadir todas las configuraciones que se necesiten
        // antes de crear el alert. En este ejemplo se añade el mensaje y el titulo del alert
        builder.setMessage(mensaje)
                .setTitle(titulo);

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(InicioActivity.this, AjustesActivity.class);
                startActivity(intent);
            }
        });


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "No quiere ver los ajustes", Toast.LENGTH_SHORT).show();
            }
        });

        //  Una vez hemos añadido todas las configuraciones creamos el alertDialog. En este
        // caso, devolvemos el objeto creado.
        return builder.create();
    }

    public AlertDialog createAlertDialog1(String titulo, String mensaje) {
        // Creamos un 'builder' o constructor que nos ayude a configurar el cuadro de dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(InicioActivity.this);

        //  Este objeto builder nos permitirá añadir todas las configuraciones que se necesiten
        // antes de crear el alert. En este ejemplo se añade el mensaje y el titulo del alert
        builder.setMessage(mensaje)
                .setTitle(titulo);

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    listElefante.remove(e);
                    adapter.notifyDataSetChanged();
            }
        });


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "No quiere borrar", Toast.LENGTH_SHORT).show();
            }
        });

        //  Una vez hemos añadido todas las configuraciones creamos el alertDialog. En este
        // caso, devolvemos el objeto creado.
        return builder.create();
    }


    private ActionMode.Callback actioncallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            int itemid = menuItem.getItemId();
            if(itemid == R.id.idBorrar){
                AlertDialog alertDialog = createAlertDialog1("Borrar", "¿Quieres borrarlo?");
                alertDialog.show();
                actionMode.finish();
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            actioncallback = null;
        }
    };

}