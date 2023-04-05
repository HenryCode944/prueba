package prueba;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

public class crea3clase {
    Map<String, Object> mapa;
    

    public crea3clase(JSONObject salida) {
        this.mapa = salida.toMap();
    }

    // Métodos de la clase
    public JSONObject recorre() {
        Map<String, Integer> names = new HashMap<>();;
        for (Entry<String, Object> entry : mapa.entrySet()) {
            try {
                names.put(entry.getValue().toString(), names.get(entry.getValue().toString()) + 1);
            } catch (Exception NullPointerException) {
                names.put(entry.getValue().toString(), 1);
            }

        }

        return new JSONObject(names);

    }

}