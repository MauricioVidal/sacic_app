package semanaacademica.sacic.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import semanaacademica.sacic.model.Usuario;

/**
 * Created by Mauricio on 23/07/2016.
 */
public class DatabaseUsuario {
    private static final String TAG = "LOG";
    private static final String NAME_TABLE = "usuario";
    private static final String[] COLUMS = new String[]{
            "id",
            "email",
            "senha",
    };
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS usuario";
    static SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public DatabaseUsuario(Context ctx) {
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

    public boolean salvar(Usuario u){
        ContentValues values = new ContentValues();
        values.put(COLUMS[1], u.getEmail());
        values.put(COLUMS[2], u.getSenha());
        return db.insert(NAME_TABLE, null, values) == 1;
    }

    public Usuario getUsuario() {
        Cursor c = db.query(NAME_TABLE, COLUMS, null, null, null,null, null);
        if (c.moveToFirst()) {
            int id_index = c.getColumnIndex(COLUMS[0]);
            int email_index = c.getColumnIndex(COLUMS[1]);
            int senha_index = c.getColumnIndex(COLUMS[2]);
            Usuario u = new Usuario();
            u.setId(c.getInt(id_index));
            u.setEmail(c.getString(email_index));
            u.setSenha(c.getString(senha_index));
            return u;
        }
        return null;
    }


}
