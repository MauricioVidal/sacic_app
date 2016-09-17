package semanaacademica.sacic.webservice.task;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import semanaacademica.sacic.model.Tipo;
import semanaacademica.sacic.webservice.http.Http;

/**
 * Created by Mauricio R. Vidal on 23/05/2016.
 */
public class AsyncTaskGetTipo extends Task<Object, Void, List<Tipo>>{
    @Override
    protected List<Tipo> doInBackground(Object... params) {
        try {
            Http http = (Http) params[0];
            String json = http.solicitar();
            return GSON.fromJson(json, new TypeToken<List<Tipo>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
