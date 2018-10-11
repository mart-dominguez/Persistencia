package ar.edu.utn.frsf.isi.dam.persistencia.dao;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import java.util.List;

import ar.edu.utn.frsf.isi.dam.persistencia.modelo.Proyecto;

public class ProyectoRepository {

    private static ProyectoRepository _REPO= null;
    private ProyectoDao proyectoDao;

    private ProyectoRepository(Context ctx){
        MyDatabase db = Room.databaseBuilder(ctx,
                MyDatabase.class, "dam-pry-2018").fallbackToDestructiveMigration()
                .build();
        proyectoDao = db.proyectoDao();
    }

    public static ProyectoRepository getInstance(Context ctx){
        if(_REPO==null) _REPO = new ProyectoRepository(ctx);
        return _REPO;
    }

    public void crearProyecto(Proyecto p){
        Log.d("APP_DAM"," Crea proyecto: "+p);
        proyectoDao.crearProyecto(p);
        Log.d("APP_DAM"," LISTO Crea proyecto: "+p);
    }

    public void actualizarProyecto(Proyecto p){
        Log.d("APP_DAM"," ACTUALIZA proyecto: "+p);
        proyectoDao.actualizar(p);
        Log.d("APP_DAM"," LISTO ACTUALIZA proyecto: "+p);
    }

    public List<Proyecto> getAll(){
        return proyectoDao.buscarTodos();
    }

    public Proyecto buscarPorId(String id){
        return proyectoDao.buscarPorId(id);
    }
}
