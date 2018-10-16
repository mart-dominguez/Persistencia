package ar.edu.utn.frsf.isi.dam.persistencia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ar.edu.utn.frsf.isi.dam.persistencia.dao.ProyectoRepository;
import ar.edu.utn.frsf.isi.dam.persistencia.modelo.Proyecto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaProyectoActivity extends AppCompatActivity {

    private ProyectoAdapter adaptador;
    private Button btnMenuInicio;
    private ListView lista;
    private List<Proyecto> itemsLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_proyecto);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                itemsLista = ProyectoRepository.getInstance(ListaProyectoActivity.this).getAll();
                Log.d("APP_DAM"," LISTA: "+itemsLista.size());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adaptador = new ProyectoAdapter(ListaProyectoActivity.this,R.layout.fila_proyecto,itemsLista);
                        lista = (ListView) findViewById(R.id.listaProyectos);
                        lista.setAdapter(adaptador);
                    }
                });
            }
        };
        Thread t1 = new Thread(r);
        t1.start();

        Call<List<Proyecto>> listaAsyn =ProyectoRepository.getInstance(ListaProyectoActivity.this).getAllAsyn();
        listaAsyn.enqueue(new Callback<List<Proyecto>>() {
            @Override
            public void onResponse(Call<List<Proyecto>> call, Response<List<Proyecto>> response) {
                switch (response.code()) {
                    case 200:
                        List<Proyecto> data = response.body();
                        adaptador.notifyDataSetChanged();
                        break;
                    case 401:
                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Proyecto>> call, Throwable t) {

            }
        });

        btnMenuInicio = (Button) findViewById(R.id.btnPrincipal);
        btnMenuInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1  = new Intent(ListaProyectoActivity.this,MainActivity.class);
                startActivity(i1);
            }
        });

    }
}
