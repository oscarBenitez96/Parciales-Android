package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN ACT" ;
    DatosDao db;
    TextView texto;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = BaseDeDatos.getInstance(this).getDao();
        texto = findViewById(R.id.texto);

        Single.create(new SingleOnSubscribe<List<Modelo>>() {
            @Override
            public void subscribe(SingleEmitter<List<Modelo>> emitter) throws Exception {
                Type type = new TypeToken<List<Modelo>>(){}.getType();
                List<Modelo> lista = new Gson().fromJson("[{\"id\":0,\"nombre\":\"Doom\",\"genero\":\"FPS\",\"year\":2016,\"consola\":\"Xbox\",\"precio\":2600},{\"id\":1,\"nombre\":\"Legend Of Zelda\",\"genero\":\"RPG\",\"year\":1998,\"consola\":\"Nintendo64\",\"precio\":750},{\"id\":2,\"nombre\":\"Super Mario Bros\",\"genero\":\"Aventuras\",\"year\":1986,\"consola\":\"Family\",\"precio\":300},{\"id\":3,\"nombre\":\"Cyberpunk 2077\",\"genero\":\"FPS\",\"year\":2019,\"consola\":\"PS4\",\"precio\":2600},{\"id\":4,\"nombre\":\"God Of War\",\"genero\":\"Aventuras\",\"year\":2018,\"consola\":\"PS4\",\"precio\":2100},{\"id\":5,\"nombre\":\"Halo\",\"genero\":\"FPS\",\"year\":2020,\"consola\":\"Xbox\",\"precio\":2600},{\"id\":6,\"nombre\":\"Fortnite\",\"genero\":\"Shooter\",\"year\":2017,\"consola\":\"PC\",\"precio\":0}]", type); // TODO Utilizar alguna de las 3 listas
                db.agregarLista(lista); // TODO guardar los valores en la base de datos
                emitter.onSuccess(lista); // TODO pasar el valor de la lista obtenida
            }
        })
            .subscribeOn(Schedulers.io())
            .flatMap(new Function<List<Modelo>, Single<List<Modelo>>>() {
            @Override
            public Single<List<Modelo>> apply(List<Modelo> modelos) throws Exception {
                return db.obtenerTodosLosRegistros(); // TODO obtener los datos de la base de datos

            }
        })
                .doOnSuccess(new Consumer<List<Modelo>>() {
                    @Override
                    public void accept(List<Modelo> s) throws Exception {
                        Log.e(TAG, "onCreate: " + s.toString());
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable s) throws Exception {
                        Log.e(TAG, "onCreate: " + s.getMessage());
                    }
                })
                .toObservable()
                .flatMapIterable(new Function<List<Modelo>, List<Modelo>>() {
                    @Override
                    public List<Modelo> apply(List<Modelo> modelos) throws Exception {
                        return modelos;
                    }
                })

                .filter(new Predicate<Modelo>() {    // TODO realizar 2 filtros
                    @Override
                    public boolean test(Modelo modelo) throws Exception {
                        return modelo.genero.toLowerCase() != "xbox";
                    }
                })
                .filter(new Predicate<Modelo>() {
                    @Override
                    public boolean test(Modelo modelo) throws Exception {
                        return modelo.year > 1987;
                    }
                })
                .toList()
                .subscribeOn(Schedulers.newThread()) // TODO realizar tarea en segundo plano
                .observeOn(AndroidSchedulers.mainThread()) // TODO observar en el hilo principal
                .subscribe(new SingleObserver<List<Modelo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        dialog = ProgressDialog.show(MainActivity.this, "", // TODO mostrar un texto de loading
                                "Loading. Please wait...", false);

                    }

                    @Override
                    public void onSuccess(List<Modelo> modelos) {
                        dialog.dismiss();

                        modelos.remove(modelos.size()-1);  // TODO borrar el ultimo valor de la lista
                        StringBuilder resultado = new StringBuilder();

                        for (Modelo modelo : modelos) {
                            resultado.append(modelo.toString()).append("\n");
                        }
                        texto.setText(resultado);  // TODO mostrar el resultado en el texto
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

//    MUSICOS
//    {
//        "id":0,
//        "nombre":"Drake",
//        "genero":"hombre",
//        "edad":32,
//        "esDJ":false,
//        "estilo":"rap"
//    }
    String obtenerMusicos() {
        return "[{\"id\":0,\"nombre\":\"Drake\",\"genero\":\"hombre\",\"edad\":32,\"esDJ\":false,\"estilo\":\"rap\"},{\"id\":1,\"nombre\":\"Ed Sheeran\",\"genero\":\"hombre\",\"edad\":28,\"esDJ\":false,\"estilo\":\"pop\"},{\"id\":2,\"nombre\":\"Martin Garrix\",\"genero\":\"hombre\",\"edad\":23,\"esDJ\":true,\"estilo\":\"electronica\"},{\"id\":3,\"nombre\":\"Beyoncé\",\"genero\":\"mujer\",\"edad\":37,\"esDJ\":false,\"estilo\":\"R&B\"},{\"id\":4,\"nombre\":\"Pink\",\"genero\":\"mujer\",\"edad\":39,\"esDJ\":false,\"estilo\":\"pop\"},{\"id\":5,\"nombre\":\"Bruno Mars\",\"genero\":\"hombre\",\"edad\":33,\"esDJ\":false,\"estilo\":\"pop\"},{\"id\":6,\"nombre\":\"Zedd\",\"genero\":\"hombre\",\"edad\":29,\"esDJ\":true,\"estilo\":\"electronica\"}]";
    }

//    PERSONAS
//    {
//        "id":0,
//        "nombre":"Manuel Gonzalez",
//        "genero":"hombre",
//        "edad":54,
//        "pais":"España",
//        "sueldo":40000
//    }
    String obtenerPersonas() {
        return "[{\"id\":0,\"nombre\":\"Manuel Gonzalez\",\"genero\":\"hombre\",\"edad\":54,\"pais\":\"España\",\"sueldo\":40000},{\"id\":1,\"nombre\":\"Olga Puals\",\"genero\":\"mujer\",\"edad\":19,\"pais\":\"Argentina\",\"sueldo\":22500},{\"id\":2,\"nombre\":\"Roger Smith\",\"genero\":\"hombre\",\"edad\":35,\"pais\":\"USA\",\"sueldo\":94200},{\"id\":3,\"nombre\":\"Heidy Stuart\",\"genero\":\"mujer\",\"edad\":43,\"pais\":\"UK\",\"sueldo\":103000},{\"id\":4,\"nombre\":\"Sofie Miller\",\"genero\":\"mujer\",\"edad\":27,\"pais\":\"Australia\",\"sueldo\":54000},{\"id\":5,\"nombre\":\"Lucas Velazquez\",\"genero\":\"hombre\",\"edad\":17,\"pais\":\"Venezuela\",\"sueldo\":61000},{\"id\":6,\"nombre\":\"Mike Benitez\",\"genero\":\"hombre\",\"edad\":41,\"pais\":\"Mexico\",\"sueldo\":67300}]";
    }

//    JUEGOS
//    {
//        "id":0,
//        "nombre":"Doom",
//        "genero":"FPS",
//        "year":2016,
//        "consola":"Xbox",
//        "precio":2600
//    }
    String obtenerJuegos() {
        return "[{\"id\":0,\"nombre\":\"Doom\",\"genero\":\"FPS\",\"year\":2016,\"consola\":\"Xbox\",\"precio\":2600},{\"id\":1,\"nombre\":\"Legend Of Zelda\",\"genero\":\"RPG\",\"year\":1998,\"consola\":\"Nintendo64\",\"precio\":750},{\"id\":2,\"nombre\":\"Super Mario Bros\",\"genero\":\"Aventuras\",\"year\":1986,\"consola\":\"Family\",\"precio\":300},{\"id\":3,\"nombre\":\"Cyberpunk 2077\",\"genero\":\"FPS\",\"year\":2019,\"consola\":\"PS4\",\"precio\":2600},{\"id\":4,\"nombre\":\"God Of War\",\"genero\":\"Aventuras\",\"year\":2018,\"consola\":\"PS4\",\"precio\":2100},{\"id\":5,\"nombre\":\"Halo\",\"genero\":\"FPS\",\"year\":2020,\"consola\":\"Xbox\",\"precio\":2600},{\"id\":6,\"nombre\":\"Fortnite\",\"genero\":\"Shooter\",\"year\":2017,\"consola\":\"PC\",\"precio\":0}]";
    }
}
