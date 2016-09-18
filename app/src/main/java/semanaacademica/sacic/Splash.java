package semanaacademica.sacic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import semanaacademica.sacic.activity.ActivityAtividade;
import semanaacademica.sacic.database.DatabaseAtividade;
import semanaacademica.sacic.database.DatabaseDia;
import semanaacademica.sacic.database.DatabaseEvento;
import semanaacademica.sacic.database.DatabaseTipo;
import semanaacademica.sacic.model.Atividade;
import semanaacademica.sacic.model.Dia;
import semanaacademica.sacic.model.Evento;
import semanaacademica.sacic.model.Tipo;
import semanaacademica.sacic.util.Notification;
import semanaacademica.sacic.webservice.WS_SACIC;

public class Splash extends Activity implements Runnable{

    private DatabaseEvento dbEvento;
    private DatabaseDia dbDia;
    private DatabaseAtividade dbAtividade;
    private DatabaseTipo dbTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Thread(this).start();

    }

    @Override
    public void run() {
        dbEvento = new DatabaseEvento(this);
        dbDia = new DatabaseDia(this);
        dbAtividade = new DatabaseAtividade(this);
        dbTipo = new DatabaseTipo(this);
        Evento e = dbEvento.getEvento();
        List<Evento> eventos = null;
        if(e == null){
            eventos =  WS_SACIC.getEventos("-1");
        }else{
            eventos = WS_SACIC.getEventos(e.getDatasinc());
        }
        if(eventos != null && !eventos.isEmpty()){
            List<Tipo> tipos = WS_SACIC.getTipos();
            dbTipo.salvar(tipos);
            for(Evento evento : eventos){
                List<Dia> dias = WS_SACIC.getDias(evento.getId());
                dbDia.salvar(dias);
                for(Dia d : dias){
                    List<Atividade> atividades = WS_SACIC.getAtividade(d.getId());
                    dbAtividade.salvar(atividades);
                }
            }
            dbEvento.salvar(eventos);
        }
        Notification.notificar(this);
        Intent it = new Intent(Splash.this, ActivityAtividade.class);
        startActivity(it);
        finish();
    }
}
