package com.londeroapps.appfutebol.db;

import com.londeroapps.appfutebol.model.Jogador;
import com.londeroapps.appfutebol.model.Posicao;

import java.util.HashMap;
import java.util.Map;

public class Meias {

    private Map<String,Jogador> listaMeias = new HashMap<String,Jogador>();

    /*
    public Meias(){

        // Meia - Arthur Melo
        Jogador meia = new Jogador("c03muo3fvadwnwmddvm81xw3wyyb9yccafc3ahm9", Posicao.M,"Arthur Melo",21,96);
        listaMeias.put("c03muo3fvadwnwmddvm81xw3wyyb9yccafc3ahm9",meia);

        // Meia - Rodriguinho
        meia = new Jogador("s7e5ud1jyym0o3yoqahfknmpvqb1fhkb8hqdi0o6", Posicao.M,"Rodriguinho",30,93);
        listaMeias.put("s7e5ud1jyym0o3yoqahfknmpvqb1fhkb8hqdi0o6",meia);

        // Meia - Thiago Neves
        meia = new Jogador("pvbeohm0zpvy9csy2nc4ziutuos34gyn5yb1s0fb", Posicao.M,"Thiago Neves",33,89);
        listaMeias.put("pvbeohm0zpvy9csy2nc4ziutuos34gyn5yb1s0fb",meia);

        // Meia - Douglas
        meia = new Jogador("vikaq76atm8att59g4fqmwqbuogal5ani9tja6in", Posicao.M,"Douglas",36,90);
        listaMeias.put("vikaq76atm8att59g4fqmwqbuogal5ani9tja6in",meia);

        // Meia - Diego Souza
        meia = new Jogador("sx1u23mruj9jyxqje10v72fysa09rs66jx1khjuo", Posicao.M,"Diego Souza",32,87);
        listaMeias.put("sx1u23mruj9jyxqje10v72fysa09rs66jx1khjuo",meia);

        // Meia - Vitor Bueno
        meia = new Jogador("kffumbx9i9xip66i6ekj6pgen6kaz9k7uv0fifqo", Posicao.M,"Vitor Bueno",23,85);
        listaMeias.put("kffumbx9i9xip66i6ekj6pgen6kaz9k7uv0fifqo",meia);
    }
    */

    public Map<String, Jogador> getListaMeias() {
        return listaMeias;
    }
}
