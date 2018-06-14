package com.londeroapps.appfutebol.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "appfutebol.db";
    public static final String TABELA = "jogador";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String HABILIDADE = "habilidade";
    public static final String POSICAO = "posicao";
    public static final int VERSAO = 2;

    public DbManager(Context context){
        super(context,NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABELA + " (" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NOME + " TEXT," + HABILIDADE + " INTEGER,"
                + POSICAO + " TEXT) ";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(sqLiteDatabase);
    }
}
