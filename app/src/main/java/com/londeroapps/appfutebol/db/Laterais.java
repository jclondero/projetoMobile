package com.londeroapps.appfutebol.db;

import com.londeroapps.appfutebol.model.Jogador;
import com.londeroapps.appfutebol.model.Posicao;

import java.util.HashMap;
import java.util.Map;

public class Laterais {

    private Map<String,Jogador> listaLaterais = new HashMap<String,Jogador>();

    /*
    public Laterais() {

        // Lateral - Bruno Cortês
        Jogador lateral = new Jogador("mjo4vu1pik06ucwc61q4bjl5o51wf8x82aewd76g", Posicao.L,"Bruno Cortês",31,85);
        listaLaterais.put("mjo4vu1pik06ucwc61q4bjl5o51wf8x82aewd76g",lateral);

        // Lateral - Apodi
        lateral = new Jogador("a9jpt8fvcrzj787nmfjl7skwxj6yvl93t3qwj73u", Posicao.L,"Apodi ",31,82);
        listaLaterais.put("a9jpt8fvcrzj787nmfjl7skwxj6yvl93t3qwj73u",lateral);

        // Lateral - Fagner
        lateral = new Jogador("vxyvrtov3clmg933jmi8fljhm8dmkex7y5luyqm1", Posicao.L,"Fagner ",28,87);
        listaLaterais.put("vxyvrtov3clmg933jmi8fljhm8dmkex7y5luyqm1",lateral);

        // Lateral - Zeca
        lateral = new Jogador("a29nazynlb9t8kpm9n9wowhlm5eot5z58wm0dmtf", Posicao.L,"Zeca ",23,86);
        listaLaterais.put("a29nazynlb9t8kpm9n9wowhlm5eot5z58wm0dmtf",lateral);

        // Lateral - Sidcley
        lateral = new Jogador("k7huec1y9tvvklfs7tm87equg3xosdn75d2pgrly", Posicao.L,"Sidcley ",24,84);
        listaLaterais.put("k7huec1y9tvvklfs7tm87equg3xosdn75d2pgrly",lateral);

        // Lateral - Léo Moura
        lateral = new Jogador("emximuvgo4x8648vyxt6f4jo14uxmn4ryinj2e7l", Posicao.L,"Léo Moura",39,80);
        listaLaterais.put("emximuvgo4x8648vyxt6f4jo14uxmn4ryinj2e7l",lateral);
    }
    */

    public Map<String, Jogador> getListaLaterais() {
        return listaLaterais;
    }
}
