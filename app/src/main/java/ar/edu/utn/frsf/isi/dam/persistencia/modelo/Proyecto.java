package ar.edu.utn.frsf.isi.dam.persistencia.modelo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;


@Entity(tableName ="APP_PROYECTO")
public class Proyecto {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID_PROYECTO")
    private Integer id;

    private String nombre;
    private Double presupuesto;
    private Integer horas;

    public Integer getId() {
        return id;
    }

    public void setId(@NonNull  Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", presupuesto=" + presupuesto +
                ", horas=" + horas +
                '}';
    }
}
