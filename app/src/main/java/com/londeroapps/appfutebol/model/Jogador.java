package com.londeroapps.appfutebol.model;

import android.content.Intent;

import java.io.Serializable;

public class Jogador implements Serializable {

    private int id;
    private String nome;
    private int habilidade;
    private String posicao;

    public Jogador(String nome, int habilidade, String posicao){
        this.setNome(nome);
        this.setHabilidade(habilidade);
        this.setPosicao(posicao);
    }

    public Jogador(String nome, int habilidade){
        this.setNome(nome);
        this.setHabilidade(habilidade);
    }

    public Jogador(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String toStringHabilidade() {
        return Integer.toString(getHabilidade());
    }
}
