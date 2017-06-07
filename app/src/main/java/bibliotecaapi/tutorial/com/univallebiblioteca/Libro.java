package bibliotecaapi.tutorial.com.univallebiblioteca;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ADMIN on 23/04/2017.
 */

public class Libro {

    private String libro_codigo;
    private String libro_titulo;
    private String paisCodigo;
    private String editorialCodigo;
    private String libro_ano;
    private String libro_publicacioncompleta;
    private String libro_descripcionfisica;
    private String libro_isbn;
    private String libro_fechacreacion;
    private String libro_fechamodificacion;
    private String usuario_codigo;
    private String libro_resumen;
    private String libro_imagen;
    private String tippubl_codigo;
    private String estado_codigo;
    private String libro_edicion;
    private String libro_nota;
    private String tippub_codigo;
    private String libro_cantidadvisitas;
    JSONObject jsonObjectLibros;

    public Libro(){

    }

    public Libro(JSONObject jsonObject){

        try {

            setJsonObjectLibros(jsonObject);
            setLibro_codigo(jsonObject.getString("libro_codigo"));
            setLibro_titulo(jsonObject.getString("libro_titulo"));
            setLibro_ano(jsonObject.getString("libro_ano"));
            setPaisCodigo(jsonObject.getString("pais_codigo"));
            setEditorialCodigo(jsonObject.getString("editorial_codigo"));
            setLibro_publicacioncompleta(jsonObject.getString("libro_publicacioncompleta"));
            setLibro_descripcionfisica(jsonObject.getString("libro_descripcionfisica"));
            setLibro_isbn(jsonObject.getString("libro_isbn"));
            setTippubl_codigo(jsonObject.getString("tippub_codigo"));
            setLibro_fechacreacion(jsonObject.getString("libro_fechacreacion"));
            setLibro_fechamodificacion(jsonObject.getString("libro_fechamodificacion"));
            setUsuario_codigo(jsonObject.getString("usuario_codigo"));
            setLibro_resumen(jsonObject.getString("libro_resumen"));
            setLibro_imagen(jsonObject.getString("libro_imagen"));
            setTippubl_codigo(jsonObject.getString("tippubl_codigo"));
            setEstado_codigo(jsonObject.getString("estado_codigo"));
            setLibro_edicion(jsonObject.getString("libro_edicion"));
            setLibro_nota(jsonObject.getString("libro_nota"));
            setLibro_cantidadvisitas(jsonObject.getString("libro_cantidadvisitas"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public JSONObject getJsonObjectLibros() {
        return jsonObjectLibros;
    }

    public String getLibro_codigo() {
        return libro_codigo;
    }

    public String getLibro_titulo() {
        return libro_titulo;
    }

    public String getLibro_ano() {
        return libro_ano;
    }

    public String getPaisCodigo() {
        return paisCodigo;
    }

    public String getLibro_cantidadvisitas() {
        return libro_cantidadvisitas;
    }

    public String getLibro_descripcionfisica() {
        return libro_descripcionfisica;
    }

    public String getLibro_isbn() {
        return libro_isbn;
    }

    public String getLibro_fechacreacion() {
        return libro_fechacreacion;
    }

    public String getLibro_fechamodificacion() {
        return libro_fechamodificacion;
    }

    public String getUsuario_codigo() {
        return usuario_codigo;
    }

    public String getLibro_resumen() {
        return libro_resumen;
    }

    public String getLibro_imagen() {
        return libro_imagen;
    }

    public String getTippubl_codigo() {
        return tippubl_codigo;
    }

    public String getEstado_codigo() {
        return estado_codigo;
    }

    public String getLibro_publicacioncompleta() {
        return libro_publicacioncompleta;
    }

    public String getLibro_edicion() {
        return libro_edicion;
    }

    public String getLibro_nota() {
        return libro_nota;
    }

    public String getEditorialCodigo() {
        return editorialCodigo;
    }

    public String getTippub_codigo() {
        return tippub_codigo;
    }

    public void setLibro_codigo(String libro_codigo) {
        this.libro_codigo = libro_codigo;
    }

    public void setLibro_titulo(String libro_titulo) {
        this.libro_titulo = libro_titulo;
    }

    public void setLibro_ano(String libro_ano) {
        this.libro_ano = libro_ano;
    }

    public void setPaisCodigo(String pais_codigo) {
        this.paisCodigo = pais_codigo;
    }

    public void setLibro_descripcionfisica(String libro_descripcionfisica) {
        this.libro_descripcionfisica = libro_descripcionfisica;
    }

    public void setLibro_isbn(String libro_isbn) {
        this.libro_isbn = libro_isbn;
    }

    public void setLibro_fechacreacion(String libro_fechacreacion) {
        this.libro_fechacreacion = libro_fechacreacion;
    }

    public void setLibro_fechamodificacion(String libro_fechamodificacion) {
        this.libro_fechamodificacion = libro_fechamodificacion;
    }

    public void setLibro_cantidadvisitas(String libro_cantidadvisitas) {
        this.libro_cantidadvisitas = libro_cantidadvisitas;
    }

    public void setUsuario_codigo(String usuario_codigo) {
        this.usuario_codigo = usuario_codigo;
    }

    public void setLibro_resumen(String libro_resumen) {
        this.libro_resumen = libro_resumen;
    }

    public void setLibro_imagen(String libro_imagen) {
        this.libro_imagen = libro_imagen;
    }

    public void setTippubl_codigo(String tippubl_codigo) {
        this.tippubl_codigo = tippubl_codigo;
    }

    public void setEstado_codigo(String estado_codigo) {
        this.estado_codigo = estado_codigo;
    }

    public void setLibro_publicacioncompleta(String libro_publicacion) {
        this.libro_publicacioncompleta = libro_publicacion;
    }

    public void setLibro_edicion(String libro_edicion) {
        this.libro_edicion = libro_edicion;
    }

    public void setLibro_nota(String libro_nota) {
        this.libro_nota = libro_nota;
    }

    public void setEditorialCodigo(String editorialCodigo) {
        this.editorialCodigo = editorialCodigo;
    }

    public void setJsonObjectLibros(JSONObject jsonObjectLibros) {
        this.jsonObjectLibros = jsonObjectLibros;
    }

    public void setTippub_codigo(String tippub_codigo) {
        this.tippub_codigo = tippub_codigo;
    }

    public ArrayList<Libro> jsonArrayToArrayListLibro(JSONArray jsonArray){

        ArrayList<Libro> arrayListLibro = new ArrayList<Libro>();

        for(int i=0; i<jsonArray.length(); i++){
            try {
                arrayListLibro.add(new Libro((JSONObject) jsonArray.get(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        return arrayListLibro;

    }

}
