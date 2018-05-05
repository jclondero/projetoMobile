package com.londeroapps.appfutebol.model;

import android.content.Intent;

import java.io.Serializable;

public class Jogador implements Serializable {

    private String nome;
    private int habilidade;

    public Jogador(String nome, int habilidade){
        this.setNome(nome);
        this.setHabilidade(habilidade);
    }

    public Jogador(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }

    public String toStringHabilidade() {
        return Integer.toString(getHabilidade());
    }
}
