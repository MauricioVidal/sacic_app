package semanaacademica.sacic.database;
public class DatabaseConst {

    public static final String[] SCRIPT_DATABASE_CREATE = new String[]{
            "CREATE TABLE usuario (\n" +
                    "id  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "email  TEXT(200) NOT NULL,\n" +
                    "senha  TEXT(200) NOT NULL\n" +
                    ");",

            "CREATE TABLE evento (\n" +
                    "id  INTEGER PRIMARY KEY,\n" +
                    "titulo  TEXT(200) NOT NULL,\n" +
                    "descricao  TEXT(2000) NOT NULL,\n" +
                    "datasinc  TEXT(200) NOT NULL\n" +
                    ");",

            "CREATE TABLE dia (\n" +
                    "id  INTEGER PRIMARY KEY,\n" +
                    "diasemana  TEXT(200) NOT NULL,\n" +
                    "idevento  INTEGER\n" +
                    ");",

            "CREATE TABLE atividade (\n" +
                    "id  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "nome  TEXT(200) NOT NULL,\n" +
                    "horario  TEXT(200) NOT NULL,\n" +
                    "duracao  TEXT(200) NOT NULL,\n" +
                    "info  TEXT(2000) NOT NULL,\n" +
                    "participar  INTEGER NOT NULL DEFAULT 1,\n" +
                    "idtipo  INTEGER NOT NULL,\n" +
                    "iddia  INTEGER NOT NULL\n" +
                    ");",

            "CREATE TABLE tipo (\n" +
                    "id  INTEGER PRIMARY KEY,\n" +
                    "descricao  TEXT(200) NOT NULL," +
                    "color TEXT(200)\n" +
                    ");",
    };

    public static final String NAME_DATABASE = "sacic.db";

    public static final int VERSION = 1;
}
