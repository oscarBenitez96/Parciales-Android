package com.example.oscarbenitez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import java.util.ArrayList;

//TODO NO SE ACEPTA UN CODIGO QUE NO COMPILE.
// Si el código crashea, esto va a afectar la nota
// Probar, probar, probar antes de finalizar.


//TODO El Modelo (Class) custom debe tener POR LO MENOS:
// Nombre, Año, Categoria, Poster y Descripcion (algún detalle largo).
// Se debe agregar uno mas a elección como mínimo.
// Las 3 primeras variables seran utilizadas para el Sort (usar el que mas gusten)
// Cuantas mas variables se agreguen y sumen a la app/visual, mas puntos
// puede sumar a la nota final.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO Cargar items en el array list con un Modelo (Class)
        // custom para mostrar en el RecyclerView.
        // Una vez llenada la lista (minimo 9 elementos) realizar un
        // sort de cualquier tipo: Sort A-Z, Sort 0-9, etc
        // Puede ser por el nombre, id, categoria, genero, año u otro
        // valor/variable que agreguen a su modelo.

        //TODO IMPORTANTE! El Modelo (Class) custom debe implementar
        // Serializable.
        ArrayList<Persona> personas = new ArrayList();
        personas =generatePersona();
        /**
         * Ejemplo de Sort con Numeros y es necesario si o si usar
         * Integer para comparar de manera sencilla.
         *
         * Para Strings es exactamente igual pero con dicho Objeto.
         *
         * Utilizar el Modelo (Class) custom y acceder a la variable
         * que se desea realizar un sort.
         *
         * Collections.sort(items, new Comparator<Integer>() {
         *             @Override
         *             public int compare(Integer o1, Integer o2) {
         *                 return o1.compareTo(o2);
         *             }
         *         });
         */

        RecyclerView recycler = findViewById(R.id.recyclerItems);

        //TODO Usar el otro constructor si se desea para incorporar la lista.
        RecyclerAdapter adapter = new RecyclerAdapter(personas);
        recycler.setAdapter(adapter);

        //TODO Usar una grilla (el otro Layout Manager) en vez de una lista.
        // Para pantalla vertical usar 2 elementos y para horizontal 3.
        // De ser tabelt, incrementar una unidad en ambos casos.

        //-----------------------------Mas tarde------------------------------

        int grilla=2;

        if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
            grilla=3;
        }

        recycler.setLayoutManager(new GridLayoutManager(this,grilla));

        //TODO Implementar un Intent Explicito que lleve a una pantalla detalle.
        // Tener en cuenta que el Object debe ser modificado por el verdadero
        // Modelo (Class) custom a utilizar.
        adapter.setOnItemClickListener(new RecyclerAdapter.ItemClickListener() {
            @Override
            public void click(Persona item) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("Persona", item);
                startActivity(intent);
            }
        });
    }

    static ArrayList<Persona> generatePersona() {
        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, R.drawable.avatar_carlos, "Carlitos Gomez", "Empleado Público.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi arcu odio, tristique et consectetur at, ultricies vel sem. Nullam vitae ornare justo, vel posuere tellus. Donec laoreet quis justo nec ultricies. Pellentesque laoreet posuere neque, eu ullamcorper magna venenatis id. Curabitur congue lorem purus, tristique vulputate turpis efficitur ut. In vitae bibendum eros." +
                " Suspendisse sit amet mi nulla. Morbi aliquam lorem est. Proin elementum sem at augue porttitor hendrerit. Vivamus et urna quam. Maecenas consequat ex ut felis auctor bibendum." +
                " Quisque sit amet lobortis erat. Duis ornare tempus dolor sit amet ornare. Nunc blandit ipsum nulla, sed congue ipsum ultrices et. " +
                " Pellentesque hendrerit rutrum nibh quis fermentum.", 1986, R.drawable.avatar_carlos));
        personas.add(new Persona(2, R.drawable.avatar_clara, "Clara Gonzalez", "Productora de Seguros.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi arcu odio, tristique et consectetur at, ultricies vel sem. Nullam vitae ornare justo, vel posuere tellus. Donec laoreet quis justo nec ultricies. Pellentesque laoreet posuere neque, eu ullamcorper magna venenatis id. Curabitur congue lorem purus, tristique vulputate turpis efficitur ut. In vitae bibendum eros." +
                " Suspendisse sit amet mi nulla. Morbi aliquam lorem est. Proin elementum sem at augue porttitor hendrerit. Vivamus et urna quam. Maecenas consequat ex ut felis auctor bibendum." +
                " Quisque sit amet lobortis erat. Duis ornare tempus dolor sit amet ornare. Nunc blandit ipsum nulla, sed congue ipsum ultrices et. " +
                " Pellentesque hendrerit rutrum nibh quis fermentum.", 1992, R.drawable.avatar_clara));
        personas.add(new Persona(3, R.drawable.avatar_jorge, "Jorgito Cabezòn", "Empresario del Alfajor.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi arcu odio, tristique et consectetur at, ultricies vel sem. Nullam vitae ornare justo, vel posuere tellus. Donec laoreet quis justo nec ultricies. Pellentesque laoreet posuere neque, eu ullamcorper magna venenatis id. Curabitur congue lorem purus, tristique vulputate turpis efficitur ut. In vitae bibendum eros." +
                " Suspendisse sit amet mi nulla. Morbi aliquam lorem est. Proin elementum sem at augue porttitor hendrerit. Vivamus et urna quam. Maecenas consequat ex ut felis auctor bibendum." +
                " Quisque sit amet lobortis erat. Duis ornare tempus dolor sit amet ornare. Nunc blandit ipsum nulla, sed congue ipsum ultrices et. " +
                " Pellentesque hendrerit rutrum nibh quis fermentum.", 1956, R.drawable.avatar_jorge));
        personas.add(new Persona(4, R.drawable.avatar_luis, "Luis Miguel", "Cantante de Cuarta o Quinta Categoría.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi arcu odio, tristique et consectetur at, ultricies vel sem. Nullam vitae ornare justo, vel posuere tellus. Donec laoreet quis justo nec ultricies. Pellentesque laoreet posuere neque, eu ullamcorper magna venenatis id. Curabitur congue lorem purus, tristique vulputate turpis efficitur ut. In vitae bibendum eros." +
                " Suspendisse sit amet mi nulla. Morbi aliquam lorem est. Proin elementum sem at augue porttitor hendrerit. Vivamus et urna quam. Maecenas consequat ex ut felis auctor bibendum." +
                " Quisque sit amet lobortis erat. Duis ornare tempus dolor sit amet ornare. Nunc blandit ipsum nulla, sed congue ipsum ultrices et. " +
                " Pellentesque hendrerit rutrum nibh quis fermentum.", 1986, R.drawable.avatar_luis));
        personas.add(new Persona(5, R.drawable.avatar_maricel, "Maricel Lalala", "Vendedora de ropa.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi arcu odio, tristique et consectetur at, ultricies vel sem. Nullam vitae ornare justo, vel posuere tellus. Donec laoreet quis justo nec ultricies. Pellentesque laoreet posuere neque, eu ullamcorper magna venenatis id. Curabitur congue lorem purus, tristique vulputate turpis efficitur ut. In vitae bibendum eros." +
                " Suspendisse sit amet mi nulla. Morbi aliquam lorem est. Proin elementum sem at augue porttitor hendrerit. Vivamus et urna quam. Maecenas consequat ex ut felis auctor bibendum." +
                " Quisque sit amet lobortis erat. Duis ornare tempus dolor sit amet ornare. Nunc blandit ipsum nulla, sed congue ipsum ultrices et. " +
                " Pellentesque hendrerit rutrum nibh quis fermentum.", 1986, R.drawable.avatar_carlos));
        personas.add(new Persona(6, R.drawable.avatar_patricio, "Patricio Rey", "y sus redonditos de ricota.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi arcu odio, tristique et consectetur at, ultricies vel sem. Nullam vitae ornare justo, vel posuere tellus. Donec laoreet quis justo nec ultricies. Pellentesque laoreet posuere neque, eu ullamcorper magna venenatis id. Curabitur congue lorem purus, tristique vulputate turpis efficitur ut. In vitae bibendum eros." +
                " Suspendisse sit amet mi nulla. Morbi aliquam lorem est. Proin elementum sem at augue porttitor hendrerit. Vivamus et urna quam. Maecenas consequat ex ut felis auctor bibendum." +
                " Quisque sit amet lobortis erat. Duis ornare tempus dolor sit amet ornare. Nunc blandit ipsum nulla, sed congue ipsum ultrices et. " +
                " Pellentesque hendrerit rutrum nibh quis fermentum.", 1986, R.drawable.avatar_patricio));
        personas.add(new Persona(7, R.drawable.avatar_paula, "Paula Cahen D'Anvers", "Diseñadora de Modas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi arcu odio, tristique et consectetur at, ultricies vel sem. Nullam vitae ornare justo, vel posuere tellus. Donec laoreet quis justo nec ultricies. Pellentesque laoreet posuere neque, eu ullamcorper magna venenatis id. Curabitur congue lorem purus, tristique vulputate turpis efficitur ut. In vitae bibendum eros." +
                " Suspendisse sit amet mi nulla. Morbi aliquam lorem est. Proin elementum sem at augue porttitor hendrerit. Vivamus et urna quam. Maecenas consequat ex ut felis auctor bibendum." +
                " Quisque sit amet lobortis erat. Duis ornare tempus dolor sit amet ornare. Nunc blandit ipsum nulla, sed congue ipsum ultrices et. " +
                " Pellentesque hendrerit rutrum nibh quis fermentum.", 1986, R.drawable.avatar_paula));
        personas.add(new Persona(8, R.drawable.avatar_roberto, "Roberto Carlos", "Siempre quizo tener un millon de amigos", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi arcu odio, tristique et consectetur at, ultricies vel sem. Nullam vitae ornare justo, vel posuere tellus. Donec laoreet quis justo nec ultricies. Pellentesque laoreet posuere neque, eu ullamcorper magna venenatis id. Curabitur congue lorem purus, tristique vulputate turpis efficitur ut. In vitae bibendum eros." +
                " Suspendisse sit amet mi nulla. Morbi aliquam lorem est. Proin elementum sem at augue porttitor hendrerit. Vivamus et urna quam. Maecenas consequat ex ut felis auctor bibendum." +
                " Quisque sit amet lobortis erat. Duis ornare tempus dolor sit amet ornare. Nunc blandit ipsum nulla, sed congue ipsum ultrices et. " +
                " Pellentesque hendrerit rutrum nibh quis fermentum.", 1986, R.drawable.avatar_roberto));
        personas.add(new Persona(9, R.drawable.avatar_soledad, "Soledad DiPalma", "Hermana de Marquitos.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi arcu odio, tristique et consectetur at, ultricies vel sem. Nullam vitae ornare justo, vel posuere tellus. Donec laoreet quis justo nec ultricies. Pellentesque laoreet posuere neque, eu ullamcorper magna venenatis id. Curabitur congue lorem purus, tristique vulputate turpis efficitur ut. In vitae bibendum eros." +
                " Suspendisse sit amet mi nulla. Morbi aliquam lorem est. Proin elementum sem at augue porttitor hendrerit. Vivamus et urna quam. Maecenas consequat ex ut felis auctor bibendum." +
                " Quisque sit amet lobortis erat. Duis ornare tempus dolor sit amet ornare. Nunc blandit ipsum nulla, sed congue ipsum ultrices et. " +
                " Pellentesque hendrerit rutrum nibh quis fermentum.", 1986, R.drawable.avatar_soledad));

        return personas;
    }

    static ArrayList<Persona> personaFilter(Persona persona){
        ArrayList<Persona> filter = new ArrayList<>();
        for(Persona item : generatePersona()){
            if(!persona.getId().equals((item.getId()))){
                filter.add(item);
            }
        }
        return filter;
    }

}
