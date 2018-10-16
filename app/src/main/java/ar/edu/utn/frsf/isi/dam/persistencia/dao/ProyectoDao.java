package ar.edu.utn.frsf.isi.dam.persistencia.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ar.edu.utn.frsf.isi.dam.persistencia.modelo.Proyecto;

@Dao
public interface ProyectoDao {

        @Insert
        void crearProyecto(Proyecto pry);
        @Insert
        void crearProyectos(List<Proyecto> pryList);
        @Query("SELECT * FROM APP_PROYECTO WHERE ID_PROYECTO = :pryId")
        Proyecto buscarPorId(Integer pryId);
        @Query("SELECT * FROM APP_PROYECTO")
        List<Proyecto> buscarTodos();
        @Update
        void actualizar (Proyecto pry);
        @Delete
        void deleteMovie (Proyecto movies);
}
