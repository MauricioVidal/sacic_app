package semanaacademica.sacic.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import semanaacademica.sacic.model.Atividade;
import semanaacademica.sacic.model.Evento;

/**
 * Created by Mauricio on 23/07/2016.
 */
public class DatabaseAtividade {
    private static final String TAG = "LOG";
    private static final String NAME_TABLE = "atividade";
    private static final String[] COLUMS = new String[]{
            "id",
            "nome",
            "horario",
            "duracao",
            "info",
            "iddia",
            "idtipo",
            "participar",
    };
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS atividade";
    static SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public DatabaseAtividade(Context ctx) {
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

    public boolean salvar(List<Atividade> list){
        boolean rs = true;
        for(Atividade a : list) {
            if(atualizar(a)) continue;
            ContentValues values = new ContentValues();
                values.put(COLUMS[0], a.getId());
                values.put(COLUMS[1], a.getNome());
                values.put(COLUMS[2], a.getHorario());
                values.put(COLUMS[3], a.getDuracao());
                values.put(COLUMS[4], a.getInfo());
                values.put(COLUMS[5], a.getIddia());
                values.put(COLUMS[6], a.getIdtipo());
                values.put(COLUMS[7], a.getParticipar());
            long resultado = db.insert(NAME_TABLE, null, values);
            rs &= resultado != 0;
            System.out.println("DEBUG: "+ rs);
        }
        return rs;
    }

    private boolean atualizar(Atividade a){
        ContentValues values = new ContentValues();
        values.put(COLUMS[1], a.getNome());
        values.put(COLUMS[2], a.getHorario());
        values.put(COLUMS[3], a.getDuracao());
        values.put(COLUMS[4], a.getInfo());
        values.put(COLUMS[5], a.getIddia());
        values.put(COLUMS[6], a.getIdtipo());
        values.put(COLUMS[7], a.getParticipar());
        return db.update(NAME_TABLE, values, "id = "+ a.getId(), null ) == 1;
    }


    public List<Atividade> getAtividades(long idDia) {
        Cursor c = db.query(NAME_TABLE, COLUMS, "iddia = "+ idDia, null, null,null, null);
        List<Atividade> list = new ArrayList();
        if (c.moveToFirst()) {
            int id_index = c.getColumnIndex(COLUMS[0]);
            int nome_index = c.getColumnIndex(COLUMS[1]);
            int horario_index = c.getColumnIndex(COLUMS[2]);
            int duracao_index = c.getColumnIndex(COLUMS[3]);
            int info_index = c.getColumnIndex(COLUMS[4]);
            int iddia_index = c.getColumnIndex(COLUMS[5]);
            int idtipo_index = c.getColumnIndex(COLUMS[6]);
            int participar_index = c.getColumnIndex(COLUMS[7]);
            do {
                Atividade a = new Atividade();
                a.setId(c.getInt(id_index));
                a.setNome(c.getString(nome_index));
                a.setHorario(c.getString(horario_index));
                a.setInfo(c.getString(info_index));
                a.setIddia(c.getInt(iddia_index));
                a.setDuracao(c.getString(duracao_index));
                a.setIdtipo(c.getInt(idtipo_index));
                a.setParticipar(c.getInt(participar_index));
                list.add(a);
            }while(c.moveToNext());
        }
        return list;
    }
}
