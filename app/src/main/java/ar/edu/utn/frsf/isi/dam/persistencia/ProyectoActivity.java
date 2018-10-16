package ar.edu.utn.frsf.isi.dam.persistencia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ar.edu.utn.frsf.isi.dam.persistencia.dao.ProyectoRepository;
import ar.edu.utn.frsf.isi.dam.persistencia.modelo.Proyecto;

public class ProyectoActivity extends AppCompatActivity {

    private Proyecto p;
    private Button btnGuardar;
    private Button btnMenuInicio;
    private EditText txtPryNombre;
    private EditText txtPryPresup;
    private EditText txtPryHoras;
    private Integer idproyecto;
    private Boolean FLAG_ALTA=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyecto);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnMenuInicio = (Button) findViewById(R.id.btnInicio);
        txtPryNombre = (EditText) findViewById(R.id.pryNombre);
        txtPryPresup = (EditText) findViewById(R.id.pryPresupuesto);
        txtPryHoras = (EditText) findViewById(R.id.pryHoras);
        Intent origen = getIntent();

        idproyecto = origen.getIntExtra("ID_PROYECTO",-1);
        Log.d("APP_PROY","CLAVE: "+idproyecto );
        if(idproyecto>0){
            FLAG_ALTA=false;
            p = ProyectoRepository.getInstance(ProyectoActivity.this).buscarPorId(idproyecto);
            txtPryNombre.setText(p.getNombre());
            txtPryPresup.setText(p.getPresupuesto()+"");
            txtPryHoras.setText(p.getHoras()+"");
        }else{
            FLAG_ALTA=true;
            p=null;
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(p==null && FLAG_ALTA){
                    p=new Proyecto();
                }
                p.setNombre(txtPryNombre.getText().toString());
                p.setHoras(Integer.valueOf(txtPryHoras.getText().toString()));
                p.setPresupuesto(Double.valueOf(txtPryPresup.getText().toString()));
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        if(FLAG_ALTA) ProyectoRepository.getInstance(ProyectoActivity.this).crearProyecto(p);
                        else ProyectoRepository.getInstance(ProyectoActivity.this).actualizarProyecto(p);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ProyectoActivity.this,"Datos Proyecto sincronizados",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                };
                Thread t1 = new Thread(r);
                t1.start();

            }
        });

        btnMenuInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1  = new Intent(ProyectoActivity.this,MainActivity.class);
                startActivity(i1);
            }
        });
    }
}
