package com.example.parcial2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Modelo.class}, version = 1, exportSchema = false)
public abstract class BaseDeDatos extends RoomDatabase {

    private static BaseDeDatos INSTANCE;

    public abstract DatosDao getDao();

    static BaseDeDatos getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    BaseDeDatos.class,
                    "parcial.db")
                    .build();
        }
        return INSTANCE;
    }
}