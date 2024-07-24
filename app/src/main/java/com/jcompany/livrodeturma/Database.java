package com.jcompany.livrodeturma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "livrodeturma", (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE anolectivo(id_anolectivo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, anolectivo TEXT, descricao TEXT, estado INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE disciplina(id_disciplina INTEGER PRIMARY KEY AUTOINCREMENT, id_anolectivo INTEGER, nome Text,descricao TEXT, estado INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE professor(id_professor INTEGER PRIMARY KEY AUTOINCREMENT, id_disciplina INTEGER, nome Text, usuario TEXT,senha TEXT, estado INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE aula(id_aula INTEGER PRIMARY KEY AUTOINCREMENT, id_professor INTEGER, data Text, sumario Text, bibliografia Text, estado INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE aluno(id_aluno INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, senha Text, estado INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE comentario(id_comentario INTEGER PRIMARY KEY AUTOINCREMENT, id_aula INTEGER, id_aluno INTEGER, comentario TEXT, estado INTEGER)");




        //sqLiteDatabase.execSQL("CREATE TABLE estudante(id_estudante PRIMARY KEY AUTOINCREMENT, codigo_estudante INTEGER, nome_estudante TEXT, ano_ingresso NUMERIC, ano_nascimento NUMERIC, curso TEXT, genero TEXT, bi TEXT, duracao_estagio INTEGER, data_inicio NUMERIC, data_fim NUMERIC, estado_estudante INTEGER)");
       // sqLiteDatabase.execSQL("CREATE TABLE empresa(id_empresa PRIMARY KEY AUTOINCREMENT, codigo_empresa INTEGER, area_servico TEXT, tipo_empresa TEXT, ano_vinculo NUMERIC, estado_empresa INTEGER)");
        //sqLiteDatabase.execSQL("CREATE TABLE surpervisor(id_supervisor PRIMARY KEY AUTOINCREMENT, codigo_supervisor INTEGER, nome TEXT, telefone INTEGER, email TEXT, especialidade TEXT, estado_supervisor INTEGER)");
        //sqLiteDatabase.execSQL("CREATE TABLE docente(id_docente PRIMARY KEY AUTOINCREMENT, codigo_docente INTEGER, nome_docente TEXT, disciplina TEXT, genero TEXT, plano_analitico TEXT, estado_docente INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS anolectivo");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS disciplina");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS professor");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS aula");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS aluno");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS comentario3    ");
    }

    public long RegisterAnoLectivo(String anolectivo, String descricao){

        SQLiteDatabase writableDatabase = getWritableDatabase( );

        ContentValues contentValues = new ContentValues();
        contentValues.put("anolectivo", anolectivo);
        contentValues.put("descricao", descricao);
        contentValues.put("estado", 1);

        return writableDatabase.insert("anolectivo", null, contentValues);

    }

    public long RegisterAula(String id_professor, String data, String sumario, String bibliografia){

        SQLiteDatabase writableDatabase = getWritableDatabase( );

        ContentValues contentValues = new ContentValues();
        contentValues.put("id_professor", id_professor);
        contentValues.put("data", data);
        contentValues.put("sumario", sumario);
        contentValues.put("bibliografia", bibliografia);
        contentValues.put("estado", 1);

        return writableDatabase.insert("aula", null, contentValues);

    }

    public long RegisterDisciplina(String id_anolectivo, String nome, String descricao){

        SQLiteDatabase writableDatabase = getWritableDatabase( );

        ContentValues contentValues = new ContentValues();
        contentValues.put("id_anolectivo", id_anolectivo);
        contentValues.put("nome", nome);
        contentValues.put("descricao", descricao);
        contentValues.put("estado", 1);

        return writableDatabase.insert("disciplina", null, contentValues);

    }
    public long RegisterProfessor(String id_disciplina, String nome,String usuario, String senha){

        SQLiteDatabase writableDatabase = getWritableDatabase( );

        ContentValues contentValues = new ContentValues();
        contentValues.put("id_disciplina", id_disciplina);
        contentValues.put("nome", nome);
        contentValues.put("usuario", usuario);
        contentValues.put("senha", senha);
        contentValues.put("estado", 1);

        return writableDatabase.insert("professor", null, contentValues);

    }

    public long RegistrarAluno(String email, String senha){

        SQLiteDatabase writableDatabase = getWritableDatabase( );

        ContentValues contentValues = new ContentValues();
        contentValues.put("usuario", email);
        contentValues.put("senha", senha);
        contentValues.put("estado", 1);

        return writableDatabase.insert("aluno", null, contentValues);

    }

    public Cursor getAllAnoLectivo() {
        return getWritableDatabase().rawQuery("SELECT * FROM anolectivo", null);
    }
    public Cursor getAllDisciplina() {
        return getWritableDatabase().rawQuery("SELECT * FROM disciplina INNER JOIN anolectivo ON(disciplina.id_anolectivo = anolectivo.id_anolectivo)", null);
    }
    public Cursor getAllProfessor() {
        return getWritableDatabase().rawQuery("SELECT * FROM professor INNER JOIN disciplina ON (professor.id_disciplina = disciplina.id_disciplina)", null);
    }
    public Cursor getAllAluno() {
        return getWritableDatabase().rawQuery("SELECT * FROM aluno", null);
    }
    public Cursor getAllAulas() {
        return getWritableDatabase().rawQuery("SELECT * FROM aula INNER JOIN professor ON (aula.id_professor == professor.id_professor)", null);
    }
}

