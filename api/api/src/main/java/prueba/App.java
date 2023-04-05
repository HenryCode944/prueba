package prueba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException {

        String httpsURL = "https://petstore.swagger.io/v2/pet/findByStatus?status=sold";
        URL myUrl = new URL(httpsURL);
        StringBuilder resultado = new StringBuilder();
        HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String linea;
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
          }
        rd.close();
       
        String resultadoJson = resultado.toString();
        JSONArray arrayPets = new JSONArray(resultadoJson);
        Map<String, String> mapa = new HashMap<>();
        for (int i = 0; i < arrayPets.length(); i++) {
            JSONObject elemeto = arrayPets.getJSONObject(i);
            try {
                mapa.put(elemeto.optString("id"), elemeto.getJSONObject("category").optString("name"));
            } catch (Exception JSONException) {
            }
        }

        JSONObject salida = new JSONObject(mapa);
        System.out.println(salida.length());
        System.out.println(salida.toString());
        crea3clase crea3clase = new crea3clase(salida);
        crea3clase.recorre();
        System.out.println(crea3clase.recorre().toString());

    }
}
