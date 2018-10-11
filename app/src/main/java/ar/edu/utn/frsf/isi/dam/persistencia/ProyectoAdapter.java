package ar.edu.utn.frsf.isi.dam.persistencia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ar.edu.utn.frsf.isi.dam.persistencia.modelo.Proyecto;

public class ProyectoAdapter extends ArrayAdapter<Proyecto> {

    private int resourceLayout;
    private Context mContext;

    public ProyectoAdapter(Context context, int resource, List<Proyecto> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Proyecto p = getItem(position);

        if (p != null) {
            TextView tv1 = (TextView) v.findViewById(R.id.filaNombre);
            TextView tv2 = (TextView) v.findViewById(R.id.filaHorasPry);
            TextView tv3 = (TextView) v.findViewById(R.id.filaPresup);
            tv1.setText("NOMBRE: "+p.getNombre());
            tv2.setText("HS: "+p.getHoras());
            tv3.setText("$"+p.getPresupuesto());
            Button btnEditar = (Button) v.findViewById(R.id.btnEditarFila);
            btnEditar.setTag(p.getId());
            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = (String) view.getTag();
                    Intent intent1 = new Intent(mContext,ProyectoActivity.class);
                    intent1.putExtra("ID_PROYECTO",id);
                    mContext.startActivity(intent1);
                }
            });
        }

        return v;
    }

}
