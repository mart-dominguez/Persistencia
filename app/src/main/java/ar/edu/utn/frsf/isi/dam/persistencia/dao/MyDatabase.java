package ar.edu.utn.frsf.isi.dam.persistencia.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ar.edu.utn.frsf.isi.dam.persistencia.modelo.Proyecto;

@Database(entities = {Proyecto.class}, version = 3)
public abstract class MyDatabase extends RoomDatabase {
    public abstract ProyectoDao proyectoDao();
}