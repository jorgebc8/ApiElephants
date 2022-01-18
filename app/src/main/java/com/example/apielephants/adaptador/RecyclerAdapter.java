package com.example.apielephants.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apielephants.R;
import com.example.apielephants.modelo.modelo.Elefantes;

import java.util.List;

// Se crea la clase herendando de Adapter que admite un tipo viewHolder necesario para
// contener los elementos de la vista
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>{
    public List<Elefantes> listElefante;
    private RecyclerViewClickListener listener;
    private RecyclerViewClickListener1 listener1;
    Context c;

    public RecyclerAdapter(List<Elefantes> listElefante,Context c,RecyclerViewClickListener listener,RecyclerViewClickListener1 listener1) {
        this.listElefante = listElefante;
        this.c = c;
        this.listener=listener;
        this.listener1=listener1;
    }






    //Este método se encarga de crear la estructura de componentes de cada celda de la
    // lista a partir del layout creado (en este caso custom_item_list.xml)
    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //El layoutInflater podría verse como un elemento que se encarga de coger la
        // vista de la celda y anidarla en la estructura jerárquica del padre (parent) en este caso
        // responde a la pregunta. "Esta celda ¿En qué elemento gráfico de tipo lista va a
        // mostrarse? Una vez hecho eso se pasa al viewHolder para que enlace los elementos del
        // layaut con los tipos de datos de cada clase para que puedan ser manipulados en tiempo
        // de ejecución
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tem_list, parent, false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(view);
        return recyclerHolder;
    }

    //Esta vista se encarga de enlazar la información con cada celda. Este método es
    // llamado una vez se ha creado la vista de cada celda de la lista. y lo único que hay que
    // hacer es extraer la información del elemento en la lista y asígnarselo a cada elemento
    // gráfico de la celda.
    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        Elefantes elefantes = listElefante.get(position);
        holder.txt_item_especie.setText(elefantes.getNombre());
        holder.txt_item_nombre.setText(elefantes.getEspecie());
        Glide.with(holder.itemView).load(elefantes.getImagenId()).into(holder.imgElefante);
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }

    public interface RecyclerViewClickListener1{
        boolean onLongClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return listElefante.size();
    }



    // Primero se crea la clase que hereda de ViewHolder. Esta clase tiene como finalidad
    // recrear los elementos de la vista del layout de cada elemento de la lista acorde al modelo
    // de datos creado (en este caso la clase Pelicula)
    public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener , View.OnLongClickListener {
        ImageView imgElefante;
        TextView txt_item_especie;
        TextView txt_item_nombre;


        // El constructor recibe como parámetro un tipo vista(itemView) que contiene la celda ya creada
        // a partir del layout correspondiente.
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            imgElefante = (ImageView) itemView.findViewById(R.id.img_item);
            txt_item_nombre = (TextView) itemView.findViewById(R.id.txt_item_nombre);
            txt_item_especie = (TextView) itemView.findViewById(R.id.txt_item_especie);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            listener1.onLongClick(view, getAdapterPosition());
            return true;
        }


    }
}

