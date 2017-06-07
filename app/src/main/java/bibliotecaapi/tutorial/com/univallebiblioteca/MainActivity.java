package bibliotecaapi.tutorial.com.univallebiblioteca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Button btnBuscar;
    private RadioGroup radioGrupo;
    private CheckBox checkCompleto;
    private CheckBox checkCatalogo;
    private EditText textoBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        radioGrupo = (RadioGroup) findViewById(R.id.radioGrupo);
        checkCompleto = (CheckBox) findViewById(R.id.check_completo);
        checkCatalogo = (CheckBox) findViewById(R.id.check_catalogo);
        textoBusqueda = (EditText) findViewById(R.id.textoBusqueda);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirListar(textoBusqueda.getText().toString());
            }
        });

    }

    public void abrirListar(String textobusqueda) {

        String validaRadio = "000";
        String validaCheck = "";

        if (radioGrupo.getCheckedRadioButtonId() == R.id.radio_clave) {
            validaRadio = "100";
        } else if (radioGrupo.getCheckedRadioButtonId() == R.id.radio_titulo) {
            validaRadio = "010";
        } else if (radioGrupo.getCheckedRadioButtonId() == R.id.radio_autor) {
            validaRadio = "001";
        }

        if (checkCompleto.isChecked()) {
            validaCheck += "1";
        } else {
            validaCheck += "0";
        }

        if (checkCatalogo.isChecked()) {
            validaCheck += "1";
        } else {
            validaCheck += "0";
        }

        if(textobusqueda.isEmpty() || textobusqueda.equals("")){
            textobusqueda = "";
        }

        Intent i = new Intent(this, ListarBusqueda.class);
        i.putExtra("textobusqueda", textobusqueda);
        startActivity(i);

    }

}







/*
ListaAdapter adapter;

    String[] titulo = new String[]{
            "Home",
            "Fotos",
            "Contacto",
    };

    int[] imagenes = {
            R.drawable.btn1,
            R.drawable.btn_ubicacion,
            R.drawable.btn1
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lista = (ListView) findViewById(R.id.ContenlistView);
        adapter = new ListaAdapter(this, titulo, imagenes);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "click sobre " + i, Toast.LENGTH_SHORT).show();
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "click Largo " + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
* */