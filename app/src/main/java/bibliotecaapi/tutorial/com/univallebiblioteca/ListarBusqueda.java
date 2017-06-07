package bibliotecaapi.tutorial.com.univallebiblioteca;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ADMIN on 18/04/2017.
 */

public class ListarBusqueda extends Activity {

    private ListView listaLibros;
    ArrayList<Libro> listaObjLibrosJson = new ArrayList<Libro>();
    JSONArray jsonArray;
    Intent intent;
    Button btnCancelar;
    Button btnDisponibilidad;
    TextView tvLibroTitulo;

    JSONObject jsonObject = new JSONObject();
    TextView libroTitulo;
    TextView libroPublicacion;
    TextView descripcionFisica;
    TextView libroResumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_busqueda);

        intent = getIntent();
        String textobusqueda = (intent.getStringExtra("textobusqueda")==null)?"":intent
                .getStringExtra("textobusqueda");

        listaLibros = (ListView) findViewById(R.id.listaLibros);
        this.descargarImagen(textobusqueda);
        this.listViewClick();


    }

    private void listViewClick() {

        listaLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent i = new Intent(ListarBusqueda.this, VistaLibro.class);
                try {
                    i.putExtra("dataJsonObject", jsonArray.get(position).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(i);*/

                showInfoLibro(position);
            }

        });

    }

    public void showInfoLibro(final int position){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.vista_libro);
        dialog.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dialog.setTitle("Editar");


        //etEditarInvitadoNombre = (EditText) dialog.findViewById(R.id.etEditarInvitadoNombre);
        //etEditarInvitadoNombre.setText(listaInternaInvitados.get(position).getItevisinvNombre());

        libroTitulo = (TextView) dialog.findViewById(R.id.tvLibroTitulo);
        libroPublicacion = (TextView) dialog.findViewById(R.id.tvLibroPublicacion);
        descripcionFisica = (TextView) dialog.findViewById(R.id.tvDescripcionFisica);
        libroResumen = (TextView) dialog.findViewById(R.id.tvLibroResumen);

        try {
            String dataJsonObject = jsonArray.get(position).toString();

            if(dataJsonObject.trim().length()>0){
                try {
                    jsonObject = new JSONObject(dataJsonObject);
                    libroTitulo.setText(jsonObject.getString("libro_titulo"));
                    libroPublicacion.setText(jsonObject.getString("libro_publicacioncompleta"));
                    descripcionFisica.setText(jsonObject.getString("libro_descripcionfisica"));
                    libroResumen.setText(jsonObject.getString("libro_resumen"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        btnDisponibilidad = (Button) dialog.findViewById(R.id.btnDisponibilidad);
        btnCancelar = (Button) dialog.findViewById(R.id.btnCancelar);

        btnDisponibilidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDisponibilidad();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }

    public void showDisponibilidad(){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.vista_disponibilidad);
        dialog.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dialog.setTitle("Disponibilidad");

        tvLibroTitulo = (TextView) dialog.findViewById(R.id.tvLibroTitulo);
        btnCancelar = (Button) dialog.findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    };

    public void obtDatosJSON(String response) {

        listaObjLibrosJson.clear();

        ArrayList<String> listado = new ArrayList<>();

        try {
            jsonArray = new JSONArray(response);
            String texto;

            listaObjLibrosJson = new Libro().jsonArrayToArrayListLibro(jsonArray);

            for (int i=0; i<jsonArray.length(); i++){
                System.out.println("Titulo::"+listaObjLibrosJson.get(i).getLibro_titulo());
                System.out.println("size::"+listaObjLibrosJson.size());
                listaLibros.setAdapter(new ImagenAdapter(getApplicationContext(),
                        listaObjLibrosJson.size(), listaObjLibrosJson));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    public void descargarImagen(String palabraclave){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando libros");
        progressDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        String url = Config.getUrl("Controller/ListarBusquedaController.php");
        String parametros = "?palabraclave="+palabraclave;

        //RequestParams parametros = new RequestParams();
        //client.post(url, parametros, new AsyncHttpResponseHandler() {
        client.post(url+parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    progressDialog.dismiss();
                    obtDatosJSON(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });

    }

}
