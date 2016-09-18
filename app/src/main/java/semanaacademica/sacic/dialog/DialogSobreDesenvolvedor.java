package semanaacademica.sacic.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import semanaacademica.sacic.R;
import semanaacademica.sacic.database.DatabaseEvento;
import semanaacademica.sacic.model.Evento;

/**
 * Created by mauricio on 17/09/16.
 */
public class DialogSobreDesenvolvedor extends Dialog{

    private Animation animAlpha;

    public DialogSobreDesenvolvedor(final Context context, DisplayMetrics metrics) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_sobre_desenvolvedor);
        animAlpha = AnimationUtils.loadAnimation(context, R.anim.anim_alpha);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setLayout(-1, -2);

        ImageView fb = (ImageView) findViewById(R.id.fb);
        ImageView in = (ImageView) findViewById(R.id.linkedin);
        ImageView github = (ImageView) findViewById(R.id.github);

        fb.getLayoutParams().width = (int) (metrics.widthPixels *0.2);
        fb.getLayoutParams().height = (int) (metrics.widthPixels *0.2);
        fb.requestLayout();
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/Mauricio.Roque.Vidal"));
                context.startActivity(intent);
            }
        });

        in.getLayoutParams().width = (int) (metrics.widthPixels *0.2);
        in.getLayoutParams().height = (int) (metrics.widthPixels *0.2);
        in.requestLayout();
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://br.linkedin.com/in/maur%C3%ADcio-roque-vidal-11118454"));
                context.startActivity(intent);
            }
        });



        github.getLayoutParams().width = (int) (metrics.widthPixels *0.2);
        github.getLayoutParams().height = (int) (metrics.widthPixels *0.2);
        github.requestLayout();
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://github.com/MauricioVidal"));
                context.startActivity(intent);
            }
        });

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
