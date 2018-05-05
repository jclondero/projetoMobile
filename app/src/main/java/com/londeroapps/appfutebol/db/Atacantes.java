package com.londeroapps.appfutebol.db;

import com.londeroapps.appfutebol.model.Jogador;
import com.londeroapps.appfutebol.model.Posicao;

import java.util.HashMap;
import java.util.Map;

public class Atacantes {

    private Map<String,Jogador> listaAtacantes = new HashMap<String,Jogador>();

    /*
    public Atacantes(){

        // Atacante - Dudu
        Jogador atacante = new Jogador("l81jayq8gxex67qwbh9xp46szrts33uk2b7ct6ps", Posicao.A,"Dudu",26,91);
        listaAtacantes.put("l81jayq8gxex67qwbh9xp46szrts33uk2b7ct6ps",atacante);

        // Atacante - Luan
        atacante = new Jogador("ez69v7s90c2hnf647j3k4xe4q0oomboqe8wwo7z6", Posicao.A,"Luan",25,95);
        listaAtacantes.put("ez69v7s90c2hnf647j3k4xe4q0oomboqe8wwo7z6",atacante);

        // Atacante - Wellington Paulista
        atacante = new Jogador("dwiqhsrt9y50mwkne7ca56kwd6o0n1c6vd2p2a2d", Posicao.A,"Wellington Paulista",34,88);
        listaAtacantes.put("dwiqhsrt9y50mwkne7ca56kwd6o0n1c6vd2p2a2d",atacante);

        // Atacante - Robinho
        atacante = new Jogador("k5zpjgro3nf51a3hrrt8z10sr38pqrbvpnc6h5tz", Posicao.A,"Robinho",34,89);
        listaAtacantes.put("k5zpjgro3nf51a3hrrt8z10sr38pqrbvpnc6h5tz",atacante);
    }
    */

    public Map<String, Jogador> getListaAtacantes() {
        return listaAtacantes;
    }
}
