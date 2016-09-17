package semanaacademica.sacic.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import semanaacademica.sacic.R;
import semanaacademica.sacic.database.DatabaseAtividade;
import semanaacademica.sacic.database.DatabaseDia;
import semanaacademica.sacic.database.DatabaseEvento;
import semanaacademica.sacic.listview.adapter.AdapterAtividade;
import semanaacademica.sacic.model.Atividade;
import semanaacademica.sacic.model.Dia;
import semanaacademica.sacic.model.Evento;
import semanaacademica.sacic.util.AvisoDialog;
import semanaacademica.sacic.util.ButtonClickDialog;

public class ActivityAtividade extends Activity{

    private DatabaseEvento databaseEvento;
    private DatabaseDia databaseDia;
    private DatabaseAtividade databaseAtividade;
    private List<List<Atividade>> atividades = new ArrayList();
    private DisplayMetrics metrics = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade);

        databaseEvento = new DatabaseEvento(this);
        databaseDia = new DatabaseDia(this);
        databaseAtividade = new DatabaseAtividade(this);

        TabHost host  = (TabHost) findViewById(R.id.tabHost);
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ImageView cabecalho = (ImageView) findViewById(R.id.cabecalho);
        cabecalho.getLayoutParams().height = (int) (metrics.heightPixels * 0.2);
        cabecalho.requestLayout();

        host.setup();
        Evento evento = databaseEvento.getEvento();
        if(evento == null) return;
        for(final Dia dia : databaseDia.getDias(evento.getId())){
            TabHost.TabSpec spec = host.newTabSpec(dia.getDiasemana());
            spec.setIndicator(dia.getDiasemana());
            spec.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    ListView list = new ListView(ActivityAtividade.this);
                    final AdapterAtividade adapter = carregarAtividades(dia.getId());
                    list.setAdapter(adapter);
                    return list;
                }
            });
            host.addTab(spec);
        }

    }

    public void salvar(View v){
        for(List<Atividade> list : atividades){
            databaseAtividade.salvar(list);
        }
        Toast.makeText(getApplicationContext(), "Salvo com Sucesso!", Toast.LENGTH_LONG).show();
        /*ButtonClickDialog ok = new ButtonClickDialog() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        };*/
        //DialogFragment dialog = new AvisoDialog().createDialog("Sucesso", "Dados salvo com sucesso!", ok);
    }


    private AdapterAtividade carregarAtividades(long idDia){
        List<Atividade> lista = databaseAtividade.getAtividades(idDia);
        AdapterAtividade adapter = new AdapterAtividade(this);
        atividades.add(lista);
        adapter.addAll(lista);
        return adapter;
    }


}
