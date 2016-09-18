package semanaacademica.sacic.util;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import semanaacademica.sacic.R;
import semanaacademica.sacic.Splash;
import semanaacademica.sacic.database.DatabaseAtividade;
import semanaacademica.sacic.database.DatabaseEvento;
import semanaacademica.sacic.model.Atividade;
import semanaacademica.sacic.model.Evento;


/**
 * Created by Mauricio on 17/09/2016.
 */
public class Notification {

    private static DatabaseAtividade database;
    private static DatabaseEvento databaseEvento;
    private static final int ID = 2341234;

    public static void notificar(Activity activity){
        Context context = activity.getApplicationContext();
        database =  new DatabaseAtividade(context);
        databaseEvento = new DatabaseEvento(context);
        long iddia = Long.parseLong(new SimpleDateFormat("dd/MM/yyyy").format(new Date()).replace("/",""));
        List<Atividade> atividades = database.getAtividades(iddia);
        if(!atividades.isEmpty()){
            for(Atividade a :atividades){
                if(a.getParticipar() == 1){
                    Evento e = databaseEvento.getEvento();
                    TaskStackBuilder stack = TaskStackBuilder.create(context);
                    Intent it  = new Intent(context, Splash.class);
                    stack.addNextIntent(it);
                    NotificationCompat.Builder  builder = new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.icone)
                            .setContentTitle(e.getTitulo())
                            .setContentText("Há atividades agendas para hoje!")
                            .setColor(Color.parseColor("#d0e087"))
                            .setAutoCancel(true);
                    PendingIntent pi = stack.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pi);

                    builder.setVibrate(new long[]{100, 100, 100, 100, 100});

                    Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    builder.setSound(uri);

                    NotificationManager nm = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
                    nm.notify(ID, builder.build());

                    break;
                }
            }

        }


    }

}
