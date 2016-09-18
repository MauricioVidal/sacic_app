package semanaacademica.sacic.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.TextView;

import semanaacademica.sacic.R;
import semanaacademica.sacic.database.DatabaseEvento;
import semanaacademica.sacic.model.Atividade;
import semanaacademica.sacic.model.Evento;

/**
 * Created by mauricio on 17/09/16.
 */
public class DialogDescricaoAtividade extends Dialog{

    private DatabaseEvento database;


    public DialogDescricaoAtividade(Context context, Atividade a) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_descricao_atividade);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setLayout(-1, -2);
        TextView title = (TextView) findViewById(R.id.title);
        TextView horario = (TextView) findViewById(R.id.horario);
        TextView descricao = (TextView) findViewById(R.id.descricao);

        int hIni, mIni, hFim, mFim, horaFinal = 0, minutoFinal=0;
        hIni = Integer.parseInt(a.getHorario().split(":")[0]);
        mIni = Integer.parseInt(a.getHorario().split(":")[1]);

        hFim = Integer.parseInt(a.getDuracao().split(":")[0]);
        mFim = Integer.parseInt(a.getDuracao().split(":")[1]);

        minutoFinal = (mIni + mFim) % 60;
        horaFinal = hIni + hFim + ((mIni + mFim) / 60);

        title.setText(a.getNome());
        descricao.setText(a.getInfo());
        horario.setText(String.format("%s - %02d:%02d", a.getHorario(), horaFinal, minutoFinal));
    }
}
