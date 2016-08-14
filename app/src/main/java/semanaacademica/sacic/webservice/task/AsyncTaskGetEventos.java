package semanaacademica.sacic.webservice.task;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import semanaacademica.sacic.model.Evento;
import semanaacademica.sacic.webservice.http.Http;

/**
 * Created by Mauricio R. Vidal on 19/05/2016.
 */
public class AsyncTaskGetEventos extends Task<Object, Void, List<Evento>>{

    @Override
    protected List<Evento> doInBackground(Object... params) {
        try{
            Http http = (Http) params[0];
            String json = http.solicitar();
            return GSON.fromJson(json, new TypeToken<List<Evento>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
