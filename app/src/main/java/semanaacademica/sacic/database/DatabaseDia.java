package semanaacademica.sacic.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import semanaacademica.sacic.model.Dia;

/**
 * Created by Mauricio on 23/07/2016.
 */
public class DatabaseDia {
    private static final String TAG = "LOG";
    private static final String NAME_TABLE = "dia";
    private static final String[] COLUMS = new String[]{
            "id",
            "diasemana",
            "idevento",
    };
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS dia";
    static SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public DatabaseDia(Context ctx) {
        dbHelper = SQLiteHelper.getInstance(ctx, DatabaseConst.NAME_DATABASE, DatabaseConst.VERSION,
                DatabaseConst.SCRIPT_DATABASE_CREATE, SCRIPT_DATABASE_DELETE);
        //abre o banco no mode escrita para poder alterar tbm
        db = dbHelper.getWritableDatabase();

    }

    public void closeDataBase() {
        if (db != null) {
            db.close();
        }
    }

    public boolean removeAll(){
        return db.delete(NAME_TABLE, null, null) == 1;
    }

    public boolean salvar(List<Dia> list){
        boolean rs = true;
        for(Dia d : list) {
            ContentValues values = new ContentValues();
            values.put(COLUMS[0], d.getId());
            values.put(COLUMS[1], d.getDiasemana());
            values.put(COLUMS[2], d.getIdevento());
            rs &= db.insert(NAME_TABLE, null, values) == 1;
        }
        return rs;
    }

    public List<Dia> getDias(int idEvento) {
        Cursor c = db.query(NAME_TABLE, COLUMS, "idevento = "+ idEvento, null, null,null, null);
        List<Dia> list = new ArrayList();
        if (c.moveToFirst()) {
            int id_index = c.getColumnIndex(COLUMS[0]);
            int diasemana_index = c.getColumnIndex(COLUMS[1]);
            int idevento_index = c.getColumnIndex(COLUMS[2]);
            do {
                Dia d = new Dia();
                d.setId(c.getInt(id_index));
                d.setDiasemana(c.getString(diasemana_index));
                d.setIdevento(c.getInt(idevento_index));
                list.add(d);
            }while(c.moveToNext());
          }
        return list;
    }


}
