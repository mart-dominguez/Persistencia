package ar.edu.utn.frsf.isi.dam.persistencia.dao;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frsf.isi.dam.persistencia.modelo.Proyecto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProyectoRepository {

    private static ProyectoRepository _REPO= null;
    private ProyectoDao proyectoDao;
    ProyectoRest2 proyectoRest;

    private ProyectoRepository(Context ctx){
        MyDatabase db = Room.databaseBuilder(ctx,
                MyDatabase.class, "dam-pry-2018").fallbackToDestructiveMigration()
                .build();
        proyectoDao = db.proyectoDao();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        proyectoRest =retrofit.create(ProyectoRest2.class);
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

    public Call<List<Proyecto>> getAllAsyn(){
        Log.d("APP_DAM"," REALIZA LA BUSQUEDA REST ");

        return proyectoRest.listarTodos();

    }

    public List<Proyecto> getAll(){
        Call<List<Proyecto>>invocacionSyn = proyectoRest.listarTodos();
        Response<List<Proyecto>> resultad = null;
        try{
            resultad = invocacionSyn.execute();
        }catch (IOException e){
            e.printStackTrace();
        }
        return resultad.body();
        //return proyectoDao.buscarTodos();
    }

    public Proyecto buscarPorId(Integer id){
        return proyectoDao.buscarPorId(id);
    }
}
