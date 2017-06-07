package bibliotecaapi.tutorial.com.univallebiblioteca;

/**
 * Created by ADMIN on 18/04/2017.
 */

public class Config {

    static String baseIni = "http://192.168.56.1/webservicebiblioteca/";

    public static String getUrl(String url)
    {
        return baseIni+url;

    }
}
