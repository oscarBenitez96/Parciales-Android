package com.example.parcial2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface DatosDao {

    @Query("SELECT * from games")
    Single<List<Modelo>> obtenerTodosLosRegistros();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void agregarLista(List<Modelo> lista);

    @Delete
    void borrarValor(Modelo modelo);
}
