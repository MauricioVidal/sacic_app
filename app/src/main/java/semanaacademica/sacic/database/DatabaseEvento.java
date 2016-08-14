package semanaacademica.sacic.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import semanaacademica.sacic.model.Evento;
import semanaacademica.sacic.model.Usuario;

/**
 * Created by Mauricio on 23/07/2016.
 */
public class DatabaseEvento {
    private static final String TAG = "LOG";
    private static final String NAME_TABLE = "evento";
    private static final String[] COLUMS = new String[]{
            "id",
            "titulo",
            "descricao",
            "datasinc",
    };
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS evento";
    static SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public DatabaseEvento(Context ctx) {
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

    public boolean salvar(List<Evento> eventos){
        boolean rs = true;
        for(Evento e : eventos){
            rs &= salvar(e);
        }
        return rs;
    }

    public boolean salvar(Evento e){
        ContentValues values = new ContentValues();
        values.put(COLUMS[0], e.getId());
        values.put(COLUMS[1], e.getTitulo());
        values.put(COLUMS[2], e.getDescricao());
        values.put(COLUMS[3], e.getDatasinc());
        return db.insert(NAME_TABLE, null, values) == 1;
    }

    public Evento getEvento() {
        Cursor c = db.rawQuery("Select * from evento where id = (Select max(id) from evento)", null);
        if (c.moveToFirst()) {
            int id_index = c.getColumnIndex(COLUMS[0]);
            int titulo_index = c.getColumnIndex(COLUMS[1]);
            int descricao_index = c.getColumnIndex(COLUMS[2]);
            int datasinc_index = c.getColumnIndex(COLUMS[3]);
            Evento e = new Evento();
            e.setId(c.getInt(id_index));
            e.setTitulo(c.getString(titulo_index));
            e.setDescricao(c.getString(descricao_index));
            e.setDatasinc(c.getString(datasinc_index));
            return e;
        }
        return null;
    }


}
