package com.example.oscarbenitez;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    private ItemClickListener onItemClickListener;

    //TODO Recordar cambiar por el Modelo (Class) custom correcto.
    private ArrayList<Persona> personas = new ArrayList();

    public RecyclerAdapter() {
    }

    //TODO Constructor adicional de ser necesario
    public RecyclerAdapter(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    //TODO Metodo auxiliar de ser necesario
    public void setItems(ArrayList personas) {
        this.personas = personas;
    }

    public void setOnItemClickListener(ItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        //TODO Pasar el valor correcto del ArrayList en vez de solo la posicion.
        holder.bind(personas.get(position));
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //TODO Modo de ejemplo - BORRAR
        TextView titulo;
        ImageView image;
        Persona persona;

        //TODO Declarar las vistas del recycler view item
        public Holder(@NonNull View itemView) {
            super(itemView);

            //TODO Modo de ejemplo - BORRAR
            titulo = itemView.findViewById(R.id.index);
            image = itemView.findViewById(R.id.image);

            //TODO Esto NO se debe borrar, maneja el click.
            itemView.setOnClickListener(this);
        }

        //TODO Recordar cambiar por el Modelo (Class) custom correcto.
        public void bind(Persona persona) {
            //TODO Llenar de información las vistas del recycler view item
            this.persona = persona;
            //TODO Modo de ejemplo - BORRAR
            titulo.setText(String.valueOf(persona.getNombre()));
            Glide.with(itemView.getContext()).load(persona.getImageUrl()).into(image);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                //TODO Pasar el valor correcto del ArrayList en vez de solo la posicion.
                onItemClickListener.click(persona);
            }
        }
    }

    public interface ItemClickListener {
        //TODO Recordar cambiar por el Modelo (Class) custom correcto.
        void click(Persona persona);
    }
}
