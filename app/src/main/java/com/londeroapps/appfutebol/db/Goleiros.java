package com.londeroapps.appfutebol.db;

import com.londeroapps.appfutebol.model.Jogador;
import com.londeroapps.appfutebol.model.Posicao;

import java.util.HashMap;
import java.util.Map;

public class Goleiros {

    private Map<String,Jogador> listaGoleiros = new HashMap<String,Jogador>();

    /*
    public Goleiros() {

        // Goleiro - Danilo Fernandes
        Jogador goleiro = new Jogador("brzknbtvbgsk3xkxyzj4k0slowdvmq3gw8k7cs90", Posicao.G,"Danilo Fernandes",30,82);
        listaGoleiros.put("brzknbtvbgsk3xkxyzj4k0slowdvmq3gw8k7cs90",goleiro);

        // Goleiro - Marcelo Grohe
        goleiro = new Jogador("cfpsxoyuc37rjeeafqhb92a355267j9srxk2rayq", Posicao.G,"Marcelo Grohe",31,90);
        listaGoleiros.put("cfpsxoyuc37rjeeafqhb92a355267j9srxk2rayq",goleiro);

        // Goleiro - Cássio Ramos
        goleiro = new Jogador("ix6d9vqbhrl1fvvvoa5uotpwuzvtmgyq3ii3gl9c", Posicao.G,"Cássio Ramos",30,86);
        listaGoleiros.put("ix6d9vqbhrl1fvvvoa5uotpwuzvtmgyq3ii3gl9c",goleiro);

        // Goleiro - Fernando Prass
        goleiro = new Jogador("sg036m1q58202na9uzmis2u1u8onshlec4wcfvq9", Posicao.G,"Fernando Prass",39,81);
        listaGoleiros.put("sg036m1q58202na9uzmis2u1u8onshlec4wcfvq9",goleiro);
    }
    */

    public Map<String, Jogador> getListaGoleiros() {
        return listaGoleiros;
    }
}
