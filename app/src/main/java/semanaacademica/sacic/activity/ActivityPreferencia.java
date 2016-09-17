package semanaacademica.sacic.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

import java.util.List;

import semanaacademica.sacic.R;
import semanaacademica.sacic.database.DatabaseTipo;
import semanaacademica.sacic.listview.adapter.AdapterTipo;
import semanaacademica.sacic.model.Tipo;
import semanaacademica.sacic.util.ButtonClickDialog;

public class ActivityPreferencia extends Activity{

    private DatabaseTipo databaseTipo;
    private ListView listView;
    private List<Tipo> tipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencia);
        listView = (ListView) findViewById(R.id.listView);
        databaseTipo = new DatabaseTipo(getApplicationContext());
        listView.setAdapter(carregarTipos());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Tipo t = (Tipo) parent.getItemAtPosition(position);
                final ColorPicker cp = new ColorPicker(ActivityPreferencia.this);
                Button button = (Button) cp.findViewById(R.id.okColorButton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       TextView hex = (TextView) cp.findViewById(R.id.codHex);
                        t.setColor(hex.getText().toString());
                    }
                });
                cp.show();
            }
        });
    }

    private ArrayAdapter<Tipo> carregarTipos(){
        AdapterTipo adapter = new AdapterTipo(getApplicationContext());
        adapter.addAll(databaseTipo.getTipos());
        return adapter;
    }
}
