package com.example.parcial2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "games")
public class Modelo {

    @PrimaryKey
    @ColumnInfo(name = "_id")
    @NonNull
    int id;

    @ColumnInfo(name = "nombre")
    String nombre;
    @ColumnInfo(name = "genero")
    String genero;
    @ColumnInfo(name = "year")
    int year;
    @ColumnInfo(name = "consola")
    String consola;
    @ColumnInfo(name = "precio")
    String precio;

    public Modelo() {
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", genero='" + genero + '\'' +
                ", year=" + year +
                ", consola='" + consola + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }


}
