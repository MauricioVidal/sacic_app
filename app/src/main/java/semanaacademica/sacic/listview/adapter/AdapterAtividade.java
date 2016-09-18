package semanaacademica.sacic.listview.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import semanaacademica.sacic.R;
import semanaacademica.sacic.database.DatabaseTipo;
import semanaacademica.sacic.dialog.DialogDescricaoAtividade;
import semanaacademica.sacic.model.Atividade;
import semanaacademica.sacic.model.Tipo;

/**
 * Created by Mauricio on 23/07/2016.
 */
public class AdapterAtividade extends ArrayAdapter<Atividade> {
    private LayoutInflater mInflater;
    private int resource;
    private DatabaseTipo databaseTipo;
    private DisplayMetrics metrics = new DisplayMetrics();
    private Activity activity;
    private Animation animAlpha;

    public AdapterAtividade(Activity activity) {
        super(activity.getApplicationContext(), R.layout.item_atividade);
        mInflater = LayoutInflater.from(activity.getApplicationContext());
        resource = R.layout.item_atividade;
        this.activity = activity;
        animAlpha = AnimationUtils.loadAnimation(activity, R.anim.anim_alpha);
        databaseTipo = new DatabaseTipo(activity.getApplicationContext());
        this.activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate(resource, parent, false);
        }
        final Atividade a = getItem(position);
        final Tipo t = databaseTipo.getTipo(a.getIdtipo());

        LinearLayout container = (LinearLayout) convertView.findViewById(R.id.container);
        TextView titulo = (TextView) convertView.findViewById(R.id.titulo);
        ImageView image = (ImageView) convertView.findViewById(R.id.imgTipo);
        CheckBox check = (CheckBox) convertView.findViewById(R.id.checkParticipa);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                Dialog dialog = new DialogDescricaoAtividade(activity, a);
                dialog.show();
            }
        });
        if(t.getId() == 1){
            image.setImageResource(R.drawable.minicurso);
        }else if(t.getId() == 2){
            image.setImageResource(R.drawable.palestra);
        }else if(t.getId() == 3){
            image.setImageResource(R.drawable.mesaredonda);

        }else if(t.getId() == 4){
            image.setImageResource(R.drawable.mostra);
        }else if(t.getId() == 5){
            image.setImageResource(R.drawable.oficina);
        }else if(t.getId() == 6){
            image.setImageResource(R.drawable.competicao);
        }
        image.getLayoutParams().height = (int) (metrics.widthPixels *0.15);
        image.getLayoutParams().width = (int) (metrics.widthPixels *0.15);
        image.requestLayout();
        String color = t.getColor() == null? "#69D2E7" : t.getColor();
        container.setBackgroundColor(Color.parseColor(color));
        check.setChecked(a.getParticipar() == 1);
        titulo.setText(a.getNome());

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                a.setParticipar(isChecked?1:0);
            }
        });

        return convertView;
    }
}
