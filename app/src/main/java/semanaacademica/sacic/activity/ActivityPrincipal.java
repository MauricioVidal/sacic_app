package semanaacademica.sacic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import semanaacademica.sacic.R;

public class ActivityPrincipal extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void actionMeusHorarios(View v){
        Intent it = new Intent(ActivityPrincipal.this, ActivityAtividade.class);
        startActivity(it);
    }

    public void actionSobre(View v){
        Intent it = new Intent(ActivityPrincipal.this, ActivitySobre.class);
        startActivity(it);
    }


}
