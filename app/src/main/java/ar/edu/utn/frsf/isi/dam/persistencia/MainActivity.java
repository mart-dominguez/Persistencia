package ar.edu.utn.frsf.isi.dam.persistencia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnProyecto;
    private Button btnProyectoRest;
    private Button btnLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLista = (Button) findViewById(R.id.btnListaProyecto);
        btnProyecto= (Button) findViewById(R.id.btnAltaProyecto);
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1  = new Intent(MainActivity.this,ListaProyectoActivity.class);
                startActivity(i1);
            }
        });

        btnProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1  = new Intent(MainActivity.this,ProyectoActivity.class);
                startActivity(i1);
            }
        });
        btnProyectoRest= (Button) findViewById(R.id.btnAltaProyectoRest);
        btnProyectoRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1  = new Intent(MainActivity.this,ProyectoRestActivity.class);
                startActivity(i1);
            }
        });

    }
}
