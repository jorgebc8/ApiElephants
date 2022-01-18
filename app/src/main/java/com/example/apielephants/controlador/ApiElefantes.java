package com.example.apielephants.controlador;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apielephants.R;
import com.example.apielephants.adaptador.RecyclerAdapter;
import com.example.apielephants.io.ConectarApiElefantes;
import com.example.apielephants.modelo.modelo.Elefantes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ApiElefantes extends AppCompatActivity {

    private RecyclerView recView;
    public ArrayList<Elefantes> ElefantesApi = new ArrayList<Elefantes>();

    private RecyclerAdapter adapter;
    private RecyclerAdapter.RecyclerViewClickListener listener;
    private RecyclerAdapter.RecyclerViewClickListener1 listener1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recView = (RecyclerView) findViewById(R.id.recView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        adapter = new RecyclerAdapter(ElefantesApi,getApplicationContext(),listener,listener1);
        recView.setAdapter(adapter);
        recView.setLayoutManager(layoutManager);
        recView.setHasFixedSize(true);
        new taskConnections().execute();

    }

    //Al ser una tarea que implica una espera,
// como es la respuesta del servidor, por ello se tiene que llevar a cabo a través de un hilo
// secundario.
    private class taskConnections extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
                    result = ConectarApiElefantes.getRequest();
            return result;
        }


    }
}