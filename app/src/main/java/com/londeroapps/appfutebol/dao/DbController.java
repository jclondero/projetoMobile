package com.londeroapps.appfutebol.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbController {

    private DbManager banco;
    private SQLiteDatabase db;

    public DbController(Context context){
        banco = new DbManager(context);
    }

    public String inserirJogador (String nome, int habilidade, String posicao){
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        valores.put(DbManager.NOME,nome);
        valores.put(DbManager.HABILIDADE,habilidade);
        valores.put(DbManager.POSICAO,posicao);
        long resultado = db.insert(DbManager.TABELA,null,valores);
        db.close();
        if(resultado > 0){
            return "Jogador adicionado com sucesso!";
        }
        return "Erro ao inserir jogador, tente novamente!";
    }

}
