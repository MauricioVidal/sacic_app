package semanaacademica.sacic.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import semanaacademica.sacic.R;
import semanaacademica.sacic.database.DatabaseEvento;
import semanaacademica.sacic.model.Evento;

/**
 * Created by mauricio on 17/09/16.
 */
public class DialogSobreEvento extends Dialog{

    private DatabaseEvento database;


    public DialogSobreEvento(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_sobre_evento);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setLayout(-1, -2);
        TextView title = (TextView) findViewById(R.id.title);
        TextView descricao = (TextView) findViewById(R.id.descricao);

        database = new DatabaseEvento(context);
        Evento e = database.getEvento();
        title.setText(e.getTitulo());
        descricao.setText(e.getDescricao());
    }

    /*public static void main(String[] args) {
        final Dialog progress = new Dialog(this);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progress.setContentView(R.layout.alert_progress);
        progress.setCanceledOnTouchOutside(false);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progress.show();

    }*/
}
