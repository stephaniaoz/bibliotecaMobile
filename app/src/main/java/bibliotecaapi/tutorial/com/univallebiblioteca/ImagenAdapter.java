package bibliotecaapi.tutorial.com.univallebiblioteca;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.snowdream.android.widget.SmartImageView;

import java.util.ArrayList;

/**
 * Created by ADMIN on 21/04/2017.
 */

public class ImagenAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    SmartImageView smartImageView;
    TextView tvLibroTitulo, tvLibroPublicacion, tvNombreLibro;
    int tamArreglo;
    ArrayList<Libro> objLibro = new ArrayList<Libro>();

    public ImagenAdapter(Context contextAplicacion, int tamanioArreglo,
                         ArrayList<Libro> obLibro) {

        this.context = contextAplicacion;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.tamArreglo = tamanioArreglo;
        this.objLibro = obLibro;
    }

    @Override
    public int getCount() {
        return tamArreglo;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.row_listar_busqueda,null);

        smartImageView = (SmartImageView) viewGroup.findViewById(R.id.imagenLista);
        tvLibroTitulo = (TextView) viewGroup.findViewById(R.id.libroTitulo);
        tvLibroPublicacion = (TextView) viewGroup.findViewById(R.id.libroPublicacion);
        tvNombreLibro = (TextView) viewGroup.findViewById(R.id.tvNombreLibro);

        String urlFinal = Config.getUrl("View/imageBooks/"+objLibro.get(position).getLibro_imagen());
        Rect rect = new Rect(smartImageView.getLeft(), smartImageView.getTop(), smartImageView
                .getRight(), smartImageView.getBottom());

        smartImageView.setImageUrl(urlFinal, rect);
        tvNombreLibro.setText(objLibro.get(position).getLibro_titulo());
        tvLibroTitulo.setText(objLibro.get(position).getLibro_publicacioncompleta());
        tvLibroPublicacion.setText(objLibro.get(position).getLibro_descripcionfisica());

        return viewGroup;
    }

}
