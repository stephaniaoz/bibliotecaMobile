package bibliotecaapi.tutorial.com.univallebiblioteca;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ADMIN on 6/06/2017.
 */

public class DisponibilidadAdapter extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<ItemLibroDisponibilidad> items;

    public DisponibilidadAdapter(Activity activity, ArrayList<ItemLibroDisponibilidad> items){
        this.activity = activity;
        this.items = items;
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<ItemLibroDisponibilidad> disponiblilidad) {
        for (int i = 0; i < disponiblilidad.size(); i++) {
            items.add(disponiblilidad.get(i));
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.row_listar_disponibilidad, null);
        }

        ItemLibroDisponibilidad dir = items.get(position);

        TextView codBarras = (TextView) v.findViewById(R.id.tvCodBarras);
        codBarras.setText(dir.getItelibdis_codigobarras());
        TextView localizacion = (TextView) v.findViewById(R.id.tvLocalizacion);
        localizacion.setText(dir.getLocalizacion_localizacion());
        TextView estante = (TextView) v.findViewById(R.id.tvEstante);
        estante.setText(dir.getEstante_nombre());
        TextView signatura = (TextView) v.findViewById(R.id.tvSignatura);
        signatura.setText(dir.getItelibdis_signaturatopografica());
        TextView estado = (TextView) v.findViewById(R.id.tvEstado);
        estado.setText(dir.getEstado_nombre());
        TextView categoria = (TextView) v.findViewById(R.id.tvCategoria);
        categoria.setText(dir.getCategoria_nombre());

        return v;

    }

}
