package semanaacademica.sacic.listview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import semanaacademica.sacic.R;
import semanaacademica.sacic.database.DatabaseTipo;
import semanaacademica.sacic.model.Atividade;
import semanaacademica.sacic.model.Tipo;

/**
 * Created by Mauricio on 23/07/2016.
 */
public class AdapterAtividade extends ArrayAdapter<Atividade> {
    private LayoutInflater mInflater;
    private int resource;
    private DatabaseTipo databaseTipo;

    public AdapterAtividade(Context context) {
        super(context, R.layout.item_atividade);
        mInflater = LayoutInflater.from(context);
        resource = R.layout.item_atividade;
        databaseTipo = new DatabaseTipo(context);
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

        //image.setImageResource(convertView.getResources().getIdentifier(t.getImagem(), "drawable", getContext().getPackageName()));
        //container.setBackgroundColor(Color.parseColor(t.getColor()));
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
