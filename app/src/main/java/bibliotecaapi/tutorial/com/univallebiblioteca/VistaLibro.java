package bibliotecaapi.tutorial.com.univallebiblioteca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ADMIN on 23/04/2017.
 */
public class VistaLibro extends Activity{

    Intent intent;
    JSONObject jsonObject = new JSONObject();
    TextView libroTitulo;
    TextView libroPublicacion;
    TextView descripcionFisica;
    TextView libroResumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_disponibilidad);

        libroTitulo = (TextView) findViewById(R.id.tvLibroTitulo);
        libroPublicacion = (TextView) findViewById(R.id.tvLibroPublicacion);
        descripcionFisica = (TextView) findViewById(R.id.tvDescripcionFisica);
        libroResumen = (TextView) findViewById(R.id.tvLibroResumen);

        intent = getIntent();
        String dataJsonObject = (intent.getStringExtra("dataJsonObject")==null)?"":intent.getStringExtra("dataJsonObject");
        Log.d("dataJsonObject",dataJsonObject);

        if(dataJsonObject.trim().length()>0){
            try {
                jsonObject = new JSONObject(intent.getStringExtra("dataJsonObject"));
                libroTitulo.setText(jsonObject.getString("libro_titulo"));
                libroPublicacion.setText(jsonObject.getString("libro_publicacioncompleta"));
                descripcionFisica.setText(jsonObject.getString("libro_descripcionfisica"));
                libroResumen.setText(jsonObject.getString("libro_resumen"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }
}
