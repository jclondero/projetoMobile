package com.londeroapps.appfutebol.db;

import com.londeroapps.appfutebol.model.Jogador;
import com.londeroapps.appfutebol.model.Posicao;

import java.util.HashMap;
import java.util.Map;

public class Zagueiros {

    private Map<String,Jogador> listaZagueiros = new HashMap<String,Jogador>();

    /*
    public Zagueiros(){

        // Zagueiro - Pedro Geromel
        Jogador zagueiro = new Jogador("zyxwk7otbcuje4id2dj6j15k7gs980xg9hrfqaen", Posicao.Z,"Pedro Geromel",32,94);
        listaZagueiros.put("zyxwk7otbcuje4id2dj6j15k7gs980xg9hrfqaen",zagueiro);

        // Zagueiro - Paulão
        zagueiro = new Jogador("d6kl5furz3h7sai9ppbes1i6su0ngk1n21ovg1nk", Posicao.Z,"Paulão",32,79);
        listaZagueiros.put("d6kl5furz3h7sai9ppbes1i6su0ngk1n21ovg1nk",zagueiro);

        // Zagueiro - Walter Kannemann
        zagueiro = new Jogador("pt3yk90eg5nsiphxfbv5xfecj604atmr299y5px0", Posicao.Z,"Walter Kannemann",27,91);
        listaZagueiros.put("pt3yk90eg5nsiphxfbv5xfecj604atmr299y5px0",zagueiro);

        // Zagueiro - Dedé
        zagueiro = new Jogador("xk3f5xlby8zn020nas86m48hfk4esaen5zcsgbje", Posicao.Z,"Dedé",29,90);
        listaZagueiros.put("xk3f5xlby8zn020nas86m48hfk4esaen5zcsgbje",zagueiro);

        // Zagueiro - Manoel
        zagueiro = new Jogador("fygwvrkk25ks3bqjlrygitoaeugeo7rp7grs6ff6", Posicao.Z,"Manoel",28,84);
        listaZagueiros.put("fygwvrkk25ks3bqjlrygitoaeugeo7rp7grs6ff6",zagueiro);

        // Zagueiro - Leonardo Silva
        zagueiro = new Jogador("h6utvfvxie1nz3rvg87kt5xfy2xb0avwgor1yj08", Posicao.Z,"Leonardo Silva",38,87);
        listaZagueiros.put("h6utvfvxie1nz3rvg87kt5xfy2xb0avwgor1yj08",zagueiro);
    }
    */

    public Map<String, Jogador> getListaZagueiros() {
        return listaZagueiros;
    }
}
