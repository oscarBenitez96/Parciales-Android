package com.example.oscarbenitez;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

//TODO Declarar Activity en el Manifest
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        Persona personas = (Persona) (getIntent().getExtras().getSerializable("Persona"));

        ImageView image = findViewById(R.id.image);
        Glide.with(this).load(personas.getImageUrl()).into(image);

        TextView titulo = findViewById(R.id.titulo);
        titulo.setText(personas.getNombre());

        TextView detalle = findViewById(R.id.detalle);
        detalle.setText(personas.getDetalle());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //TODO Usar un ScrollView o NestedScrollView para mostrar los elementos.

        //TODO Puntos adicionales por el uso de un Intent Implicito.
        // no afecta a la nota el no hacerlo.

        //TODO Muchos puntos adicionales si lograr incorporar un RecyclerView
        // dentro de un NestedScrollView. No es necesario, es solo si tienen
        // el tiempo, las ganas y el conocimiento. No afecta a la nota el no
        // hacerlo.
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
