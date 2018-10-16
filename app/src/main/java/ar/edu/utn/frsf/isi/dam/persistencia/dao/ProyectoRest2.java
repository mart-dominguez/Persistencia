package ar.edu.utn.frsf.isi.dam.persistencia.dao;

import java.util.List;

import ar.edu.utn.frsf.isi.dam.persistencia.modelo.Proyecto;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProyectoRest2 {
    @GET("proyectos/")
    Call<List<Proyecto>> listarTodos();

}
