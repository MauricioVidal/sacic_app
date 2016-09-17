package semanaacademica.sacic.webservice.task;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import semanaacademica.sacic.model.Dia;
import semanaacademica.sacic.webservice.http.Http;

/**
 * Created by Mauricio R. Vidal on 19/05/2016.
 */
public class AsyncTaskGetDias extends Task<Object, Void, List<Dia>>{

    @Override
    protected List<Dia> doInBackground(Object... params) {
        try {
            Http http = (Http) params[0];
            String json = http.solicitar();
            return GSON.fromJson(json, new TypeToken<List<Dia>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
