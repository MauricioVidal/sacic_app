package semanaacademica.sacic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import semanaacademica.sacic.R;

public class ActivitySobre extends Activity{

    private DisplayMetrics metrics = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ImageView cabecalho = (ImageView) findViewById(R.id.cabecalho);
        cabecalho.getLayoutParams().height = (int) (metrics.heightPixels * 0.2);
        cabecalho.requestLayout();
    }
}
