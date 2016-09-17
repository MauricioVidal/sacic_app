package semanaacademica.sacic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import semanaacademica.sacic.R;

public class ActivityPrincipal extends Activity{

    private DisplayMetrics metrics = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ImageView cabecalho = (ImageView) findViewById(R.id.cabecalho);
        cabecalho.getLayoutParams().height = (int) (metrics.heightPixels * 0.2);
        cabecalho.requestLayout();
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
