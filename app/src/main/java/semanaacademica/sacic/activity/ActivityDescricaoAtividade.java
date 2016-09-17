package semanaacademica.sacic.activity;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import semanaacademica.sacic.R;
import semanaacademica.sacic.model.Atividade;

public class ActivityDescricaoAtividade extends Activity{

    private DisplayMetrics metrics = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_atividade);
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ImageView cabecalho = (ImageView) findViewById(R.id.cabecalho);
        cabecalho.getLayoutParams().height = (int) (metrics.heightPixels * 0.2);
        cabecalho.requestLayout();
        ImageView image = (ImageView) findViewById(R.id.imageAtividade);
        TextView titulo = (TextView) findViewById(R.id.tituloAtividade);
        TextView horario = (TextView) findViewById(R.id.horarioAtividade);
        TextView info = (TextView) findViewById(R.id.infoAtividade);

        Bundle bundle = getIntent().getExtras();
        Atividade a = (Atividade) bundle.get("atividade");
        titulo.setText(a.getNome());
        info.setText(a.getInfo());
        if(a.getIdtipo() == 1){
            image.setImageResource(R.drawable.minicurso);
        }else if(a.getIdtipo() == 2){
            image.setImageResource(R.drawable.palestra);
        }else if(a.getIdtipo() == 3){
            image.setImageResource(R.drawable.mesaredonda);
        }else if(a.getIdtipo() == 4){
            image.setImageResource(R.drawable.mostra);
        }else{
            image.setImageResource(R.drawable.oficina);
        }
        image.getLayoutParams().width = (int) (metrics.widthPixels *0.3);
        image.getLayoutParams().height = (int) (metrics.widthPixels *0.3);
        int hIni, mIni, hFim, mFim, horaFinal = 0, minutoFinal=0;
        hIni = Integer.parseInt(a.getHorario().split(":")[0]);
        mIni = Integer.parseInt(a.getHorario().split(":")[1]);

        hFim = Integer.parseInt(a.getDuracao().split(":")[0]);
        mFim = Integer.parseInt(a.getDuracao().split(":")[1]);

        minutoFinal = (mIni + mFim) % 60;
        horaFinal = hIni + hFim + ((mIni + mFim) / 60);
        //horario.setText(String.format("%s - %02d:%02d", a.getHorario(), horaFinal, minutoFinal));





    }
}
