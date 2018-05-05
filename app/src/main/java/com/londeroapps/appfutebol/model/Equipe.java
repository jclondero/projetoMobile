package com.londeroapps.appfutebol.model;

import java.io.Serializable;

public class Equipe implements Serializable {

    Jogador goleiro, latDireito, latEsquerdo, zagueiro, volante, meia, atacante;

    public Equipe(){
    }

    public Jogador getGoleiro() {
        return goleiro;
    }

    public void setGoleiro(Jogador goleiro) {
        this.goleiro = goleiro;
    }

    public Jogador getLatDireito() {
        return latDireito;
    }

    public void setLatDireito(Jogador latDireito) {
        this.latDireito = latDireito;
    }

    public Jogador getLatEsquerdo() {
        return latEsquerdo;
    }

    public void setLatEsquerdo(Jogador latEsquerdo) {
        this.latEsquerdo = latEsquerdo;
    }

    public Jogador getZagueiro() {
        return zagueiro;
    }

    public void setZagueiro(Jogador zagueiro) {
        this.zagueiro = zagueiro;
    }

    public Jogador getVolante() {
        return volante;
    }

    public void setVolante(Jogador volante) {
        this.volante = volante;
    }

    public Jogador getMeia() {
        return meia;
    }

    public void setMeia(Jogador meia) {
        this.meia = meia;
    }

    public Jogador getAtacante() {
        return atacante;
    }

    public void setAtacante(Jogador atacante) {
        this.atacante = atacante;
    }
}
