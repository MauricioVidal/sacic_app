package semanaacademica.sacic.webservice.task;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.List;

import semanaacademica.sacic.model.Atividade;
import semanaacademica.sacic.webservice.http.Http;

/**
 * Created by Mauricio R. Vidal on 23/05/2016.
 */
public class AsyncTaskGetAtividades extends Task<Object, Void, List<Atividade>>{

    @Override
    protected List<Atividade> doInBackground(Object... params) {
        try {
            Http http = (Http) params[0];
            String json = http.solicitar();
            return GSON.fromJson(json, new TypeToken<List<Atividade>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
