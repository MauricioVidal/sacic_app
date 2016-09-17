package semanaacademica.sacic.webservice;
import java.util.List;
import java.util.concurrent.ExecutionException;

import semanaacademica.sacic.model.Atividade;
import semanaacademica.sacic.model.Dia;
import semanaacademica.sacic.model.Evento;
import semanaacademica.sacic.model.Tipo;
import semanaacademica.sacic.webservice.http.Get;
import semanaacademica.sacic.webservice.task.AsyncTaskGetAtividades;
import semanaacademica.sacic.webservice.task.AsyncTaskGetDias;
import semanaacademica.sacic.webservice.task.AsyncTaskGetEventos;
import semanaacademica.sacic.webservice.task.AsyncTaskGetTipo;

/**
 * Created by Mauricio R. Vidal on 19/05/2016.
 */
public class WS_SACIC {


    private static final String HOST = "http://www.bcc.unifal-mg.edu.br/sacic/bd/app/webservice_sacic.php";

    public static List<Evento> getEventos(String datasinc){
        try {
            return new AsyncTaskGetEventos().execute(new Get(HOST+"?info=evento&datasinc="+datasinc)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Dia> getDias(long ano){
        try {
            return new AsyncTaskGetDias().execute(new Get(HOST+"?info=dia&ano="+ano)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Atividade> getAtividade(long iddia){
        try {
            return new AsyncTaskGetAtividades().execute(new Get(HOST+"?info=atividade&iddia="+iddia)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Tipo> getTipos(){
        try {
            return new AsyncTaskGetTipo().execute(new Get(HOST+"?info=tipo")).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }



}
