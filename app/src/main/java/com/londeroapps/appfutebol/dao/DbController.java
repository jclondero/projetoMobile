package com.londeroapps.appfutebol.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public Cursor carregaJogadores(){
        Cursor cursor;
        String [] campos = {DbManager.ID,DbManager.NOME,DbManager.HABILIDADE,DbManager.POSICAO};

        db = banco.getReadableDatabase();
        cursor = db.query(DbManager.TABELA,campos,null,null,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaJogadorById(int id){
        Cursor cursor;
        String [] campos = {DbManager.ID,DbManager.NOME,DbManager.HABILIDADE,DbManager.POSICAO};
        String where = DbManager.ID + "=" + id;

        db = banco.getReadableDatabase();
        cursor = db.query(DbManager.TABELA,campos,where,null,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaJogadorByPosicao(String posicao){
        Cursor cursor;
        String [] campos = {DbManager.ID,DbManager.NOME,DbManager.HABILIDADE,DbManager.POSICAO};
        String where = DbManager.POSICAO + "='" + posicao+"'";

        db = banco.getReadableDatabase();
        cursor = db.query(DbManager.TABELA,campos,where,null,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String alteraRegistro(int id, String nome, int habilidade, String posicao){
        ContentValues valores;
        String where;
        db = banco.getWritableDatabase();
        where = DbManager.ID + "=" + id;
        valores = new ContentValues();
        valores.put(DbManager.NOME, nome);
        valores.put(DbManager.POSICAO,posicao);
        valores.put(DbManager.HABILIDADE,habilidade);

        long resultado = db.update(DbManager.TABELA,valores,where,null);
        db.close();

        if(resultado > 0){
            return "Jogador alterado com sucesso!";
        }
        return "Erro ao alterar jogador, tente novamente!";
    }

    public String deletaRegistro(int id){
        String where = DbManager.ID + "=" + id;
        db = banco.getReadableDatabase();
        long resultado = db.delete(DbManager.TABELA,where,null);
        db.close();

        if(resultado > 0){
            return "Jogador deletado com sucesso!";
        }
        return "Erro ao deletar jogador, tente novamente!";
    }

}
