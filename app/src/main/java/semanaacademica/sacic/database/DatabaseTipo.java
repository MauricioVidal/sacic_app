package semanaacademica.sacic.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import semanaacademica.sacic.model.Tipo;
import semanaacademica.sacic.model.Usuario;

/**
 * Created by Mauricio on 23/07/2016.
 */
public class DatabaseTipo {
    private static final String TAG = "LOG";
    private static final String NAME_TABLE = "tipo";
    private static final String[] COLUMS = new String[]{
            "id",
            "descricao",
    };
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS tipo";
    static SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public DatabaseTipo(Context ctx) {
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

    public boolean salvar(List<Tipo> tipos){
        boolean rs = true;
        for(Tipo t : tipos){
            rs &= salvar(t);
        }
        return rs;
    }

    public boolean salvar(Tipo t){
        if(atualizar(t))return true;
        ContentValues values = new ContentValues();
        values.put(COLUMS[1], t.getDescricao());
        return db.insert(NAME_TABLE, null, values) == 1;
    }

    public boolean atualizar(Tipo t){
        ContentValues values = new ContentValues();
        values.put(COLUMS[1], t.getDescricao());
        return db.update(NAME_TABLE, values, "id = "+ t.getId(), null ) == 1;
    }

    public Tipo getTipo(int id ) {
        Cursor c = db.query(NAME_TABLE, COLUMS, "id = "+ id, null, null,null, null);
        if (c.moveToFirst()) {
            int id_index = c.getColumnIndex(COLUMS[0]);
            int descricao_index = c.getColumnIndex(COLUMS[1]);
            Tipo t = new Tipo();
            t.setId(c.getInt(id_index));
            t.setDescricao(c.getString(descricao_index));
            return t;
        }
        return null;
    }


}
