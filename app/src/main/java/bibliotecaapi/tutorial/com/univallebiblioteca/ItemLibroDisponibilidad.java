package bibliotecaapi.tutorial.com.univallebiblioteca;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ADMIN on 12/06/2017.
 */

public class ItemLibroDisponibilidad{

    private String itelibdis_codigo;
    private String libro_codigo;
    private String itelibdis_codigobarras;
    private String estante_codigo;
    private String itelibdis_signaturatopografica;
    private String estado_codigo;
    private String categoria_codigo;
    private String itelibdis_fechacreacion;
    private String itelibdis_fechamodificacion;
    private String usuario_codigo;
    private String itelibdis_fechaprestamo;
    private String itelibdis_edicion;
    JSONObject jsonObjectDisponibilidad;
    private String libro_titulo;
    private String estante_nombre;
    private String localizacion_localizacion;
    private String estado_nombre;
    private String categoria_nombre;

    public ItemLibroDisponibilidad() {
    }

    public ItemLibroDisponibilidad(JSONObject jsonObject) {

        setJsonObjectDisponibilidad(jsonObject);
        try {
            setItelibdis_codigo(jsonObject.getString("itelibdis_codigo"));
            setLibro_codigo(jsonObject.getString("libro_codigo"));
            setItelibdis_codigobarras(jsonObject.getString("itelibdis_codigobarras"));
            setEstante_codigo(jsonObject.getString("estante_codigo"));
            setItelibdis_signaturatopografica(jsonObject.getString("itelibdis_signaturatopografica"));
            setEstado_codigo(jsonObject.getString("estado_codigo"));
            setCategoria_codigo(jsonObject.getString("categoria_codigo"));
            setItelibdis_fechacreacion(jsonObject.getString("categoria_codigo"));
            setItelibdis_fechamodificacion(jsonObject.getString("itelibdis_fechamodificacion"));
            setUsuario_codigo(jsonObject.getString("usuario_codigo"));
            setItelibdis_fechaprestamo(jsonObject.getString("itelibdis_fechaprestamo"));
            setItelibdis_edicion(jsonObject.getString("itelibdis_edicion"));
            setLibro_titulo(jsonObject.getString("libro_titulo"));
            setEstante_nombre(jsonObject.getString("estante_nombre"));
            setLocalizacion_localizacion(jsonObject.getString("localizacion_localizacion"));
            setEstado_nombre(jsonObject.getString("estado_nombre"));
            setCategoria_nombre(jsonObject.getString("categoria_nombre"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public JSONObject getJsonObjectDisponibilidad() {
        return jsonObjectDisponibilidad;
    }

    public void setLibro_titulo(String libro_titulo) {
        this.libro_titulo = libro_titulo;
    }

    public void setEstante_nombre(String estante_nombre) {
        this.estante_nombre = estante_nombre;
    }

    public void setLocalizacion_localizacion(String localizacion_localizacion) {
        this.localizacion_localizacion = localizacion_localizacion;
    }

    public void setEstado_nombre(String estado_nombre) {
        this.estado_nombre = estado_nombre;
    }

    public void setCategoria_nombre(String categoria_nombre) {
        this.categoria_nombre = categoria_nombre;
    }

    public String getLibro_titulo() {
        return libro_titulo;
    }

    public String getEstante_nombre() {
        return estante_nombre;
    }

    public String getLocalizacion_localizacion() {
        return localizacion_localizacion;
    }

    public String getEstado_nombre() {
        return estado_nombre;
    }

    public String getCategoria_nombre() {
        return categoria_nombre;
    }

    public String getItelibdis_codigo() {
        return itelibdis_codigo;
    }

    public String getLibro_codigo() {
        return libro_codigo;
    }

    public String getItelibdis_codigobarras() {
        return itelibdis_codigobarras;
    }

    public String getEstante_codigo() {
        return estante_codigo;
    }

    public String getItelibdis_signaturatopografica() {
        return itelibdis_signaturatopografica;
    }

    public String getEstado_codigo() {
        return estado_codigo;
    }

    public String getCategoria_codigo() {
        return categoria_codigo;
    }

    public String getItelibdis_fechacreacion() {
        return itelibdis_fechacreacion;
    }

    public String getItelibdis_fechamodificacion() {
        return itelibdis_fechamodificacion;
    }

    public String getUsuario_codigo() {
        return usuario_codigo;
    }

    public String getItelibdis_fechaprestamo() {
        return itelibdis_fechaprestamo;
    }

    public String getItelibdis_edicion() {
        return itelibdis_edicion;
    }

    public void setItelibdis_codigo(String itelibdis_codigo) {
        this.itelibdis_codigo = itelibdis_codigo;
    }

    public void setLibro_codigo(String libro_codigo) {
        this.libro_codigo = libro_codigo;
    }

    public void setItelibdis_codigobarras(String itelibdis_codigobarras) {
        this.itelibdis_codigobarras = itelibdis_codigobarras;
    }

    public void setEstante_codigo(String estante_codigo) {
        this.estante_codigo = estante_codigo;
    }

    public void setItelibdis_signaturatopografica(String itelibdis_signaturatopografica) {
        this.itelibdis_signaturatopografica = itelibdis_signaturatopografica;
    }

    public void setEstado_codigo(String estado_codigo) {
        this.estado_codigo = estado_codigo;
    }

    public void setCategoria_codigo(String categoria_codigo) {
        this.categoria_codigo = categoria_codigo;
    }

    public void setItelibdis_fechacreacion(String itelibdis_fechacreacion) {
        this.itelibdis_fechacreacion = itelibdis_fechacreacion;
    }

    public void setItelibdis_fechamodificacion(String itelibdis_fechamodificacion) {
        this.itelibdis_fechamodificacion = itelibdis_fechamodificacion;
    }

    public void setUsuario_codigo(String usuario_codigo) {
        this.usuario_codigo = usuario_codigo;
    }

    public void setItelibdis_fechaprestamo(String itelibdis_fechaprestamo) {
        this.itelibdis_fechaprestamo = itelibdis_fechaprestamo;
    }

    public void setItelibdis_edicion(String itelibdis_edicion) {
        this.itelibdis_edicion = itelibdis_edicion;
    }

    public void setJsonObjectDisponibilidad(JSONObject jsonObjectDisponibilidad) {
        this.jsonObjectDisponibilidad = jsonObjectDisponibilidad;
    }

    public ArrayList<ItemLibroDisponibilidad> jsonArrayToArrayListDisponibilidad(JSONArray jsonArray){

        ArrayList<ItemLibroDisponibilidad> arrayListDisponibilidad = new ArrayList<ItemLibroDisponibilidad>();

        for(int i=0; i<jsonArray.length(); i++){
            try {
                arrayListDisponibilidad.add(new ItemLibroDisponibilidad((JSONObject) jsonArray.get(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        return arrayListDisponibilidad;

    }

}
