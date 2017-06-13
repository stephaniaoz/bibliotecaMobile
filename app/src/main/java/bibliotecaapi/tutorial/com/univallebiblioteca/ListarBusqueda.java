package bibliotecaapi.tutorial.com.univallebiblioteca;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
    ArrayList<ItemLibroDisponibilidad> listaObjDisponibilidadJson = new ArrayList<ItemLibroDisponibilidad>();
    JSONArray jsonArray;
    JSONArray jsonArrayDisponibilidad;
    Intent intent;
    Button btnCancelar;
    Button btnDisponibilidad;
    TextView librotitulodisponibilidad;
    ListView listaDisponibilidad;

    JSONObject jsonObject = new JSONObject();
    TextView libroTitulo;
    TextView libroPublicacion;
    TextView descripcionFisica;
    TextView libroResumen;
    Integer pocisionlibro;
    CharSequence[] items = new CharSequence[0];
    DisponibilidadAdapter adapter;
    Button btnGenerar;
    EditText etCodigoEstudiante;
    EditText etCorreoEstudiante;
    EditText etObservacion;
    Button btnCancelarTiquete;


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
                pocisionlibro = position;
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
        String libro_codigo = "";

        try {
            String dataJsonObject = jsonArray.get(position).toString();

            if(dataJsonObject.trim().length()>0){
                try {
                    jsonObject = new JSONObject(dataJsonObject);
                    libroTitulo.setText(jsonObject.getString("libro_titulo"));
                    libroPublicacion.setText(jsonObject.getString("libro_publicacioncompleta"));
                    descripcionFisica.setText(jsonObject.getString("libro_descripcionfisica"));
                    libroResumen.setText(jsonObject.getString("libro_resumen"));
                    libro_codigo = jsonObject.getString("libro_codigo");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        btnDisponibilidad = (Button) dialog.findViewById(R.id.btnDisponibilidad);
        btnCancelar = (Button) dialog.findViewById(R.id.btnCancelar);

        final String finalLibro_codigo = libro_codigo;
        btnDisponibilidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDisponibilidad(finalLibro_codigo);
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

    public void showDisponibilidad(final String libro_codigo){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.vista_disponibilidad);
        dialog.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dialog.setTitle("Disponibilidad");

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, valores);
        //listaDisponibilidad.setAdapter(adapter);

        btnCancelar = (Button) dialog.findViewById(R.id.btnCancelar);
        listaDisponibilidad = (ListView) dialog.findViewById(R.id.listaDisponibilidad);
        librotitulodisponibilidad = (TextView) dialog.findViewById(R.id.tvLibroTituloDis);
        librotitulodisponibilidad.setText(listaObjLibrosJson.get(pocisionlibro).getLibro_titulo());

        this.setOnClickListenerListViewDisponibilidad();

        cargaDatosDisponibilidad(libro_codigo);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    };

    public void setOnClickListenerListViewDisponibilidad(){

        listaDisponibilidad.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemLibroDisponibilidad disponibilidad = listaObjDisponibilidadJson.get(position);
                showDialogOpcionListaDisponibilidad(disponibilidad);
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void showDialogOpcionListaDisponibilidad(final ItemLibroDisponibilidad disponibilidad){

        String estadoTitle = "Opciones";
        if(disponibilidad.getEstado_nombre().equals("DISPONIBLE")){
            items = new CharSequence[]{"Generar tiquete"};
            estadoTitle = "Reserva libro";
        }else{
            items = new CharSequence[]{"Solicitar"};
            estadoTitle = "Libro no disponible, solicitalo";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(estadoTitle);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_LONG).show();
                Log.d("Opción:",""+items[which]);

                Intent intent;

                if(items[which].toString().equals("Generar tiquete")){
                    /*intent = new Intent(Visita.this,CerrarVisita.class);
                    intent.putExtra("dataJsonObject", objectVisita.getDataJsonObject().toString());
                    startActivity(intent);*/
                    showGenerarTiquete(disponibilidad);
                }else
                if(items[which].toString().equals("Solicitar")){
                    /*intent = new Intent(Visita.this,CancelarVisita.class);
                    intent.putExtra("dataJsonObject", objectVisita.getDataJsonObject().toString());
                    startActivity(intent);*/
                }

            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    public void showGenerarTiquete(final ItemLibroDisponibilidad disponibilidad){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.datos_generar_tiquete);
        dialog.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dialog.setTitle("Generar tiquete");

        btnGenerar = (Button) dialog.findViewById(R.id.btnGenerar);
        etCodigoEstudiante = (EditText) dialog.findViewById(R.id.etCodigoEstudiante);
        etCorreoEstudiante = (EditText) dialog.findViewById(R.id.etCorreoEstudiante);
        etObservacion = (EditText) dialog.findViewById(R.id.etObservacion);
        btnCancelarTiquete = (Button) dialog.findViewById(R.id.btnCancelarTiquete);

        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grabarTiquete(disponibilidad);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        btnCancelarTiquete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void grabarTiquete(final ItemLibroDisponibilidad disponibilidad){

        final ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Generando tiquete");
        progressDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        String url = Config.getUrl("Controller/GrabarTiqueteController.php");

        RequestParams parametros = new RequestParams();
        parametros.put("itelibdis_codigo",disponibilidad.getItelibdis_codigo());
        parametros.put("correoestudiante",etCorreoEstudiante.getText());
        parametros.put("codigoestudiante",etCodigoEstudiante.getText());
        parametros.put("observacionestudiante",etObservacion.getText());
        parametros.put("libro_codigo",disponibilidad.getLibro_codigo());

        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    Toast.makeText(getApplicationContext(), "okk: "+respuesta, Toast
                            .LENGTH_LONG).show();
                    disponibilidad.setEstado_codigo("8");
                    disponibilidad.setEstado_codigo("RESERVADO");
                    progressDialog.dismiss();
                    adapter.notifyDataSetChanged();
                    //obtDatosJSONdisponiblidad(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });

    }

    public void cargaDatosDisponibilidad(String libroCodigo){

        final ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Cargando disponibilidad");
        progressDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        String url = Config.getUrl("Controller/ListarBusquedaDisponiblidadController.php");
        String parametros = "?librocodigo="+libroCodigo;

        client.post(url+parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    progressDialog.dismiss();
                    obtDatosJSONdisponiblidad(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });

    }

    public void obtDatosJSONdisponiblidad(String response){
        listaObjDisponibilidadJson.clear();

        ArrayList<String> listado = new ArrayList<>();

        try {
            jsonArrayDisponibilidad = new JSONArray(response);
            String texto;

            listaObjDisponibilidadJson = new ItemLibroDisponibilidad().jsonArrayToArrayListDisponibilidad(jsonArrayDisponibilidad);

            for (int i=0; i<jsonArrayDisponibilidad.length(); i++){
                System.out.println("Titulo::"+listaObjDisponibilidadJson.get(i).getLibro_codigo());
                System.out.println("size::"+listaObjDisponibilidadJson.size());

                listado.add(listaObjDisponibilidadJson.get(i).getItelibdis_signaturatopografica());

            }

            adapter = new DisponibilidadAdapter(this, listaObjDisponibilidadJson);
            listaDisponibilidad.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout
                    .simple_expandable_list_item_1, listado);
            listaDisponibilidad.setAdapter(adapter);*/

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

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
