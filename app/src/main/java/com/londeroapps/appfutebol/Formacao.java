package com.londeroapps.appfutebol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import com.londeroapps.appfutebol.model.Equipe;
import com.londeroapps.appfutebol.model.Jogador;

public class Formacao extends AppCompatActivity {

    Equipe equipe1;
    Equipe equipe2;
    String[] jogadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formacao);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        equipe1 = (Equipe) extras.getSerializable("equipe1");
        equipe2 = (Equipe) extras.getSerializable("equipe2");
        jogadores = new String[14];

        // Para teste
        // equipe1 = new Equipe();
        // equipe2 = new Equipe();
        // populaEquipe();

        converterObjetoJogadores();

        GridView gridView = (GridView) findViewById(R.id.gridFormacao);
        gridView.setAdapter(new JogadorAdapter(this,jogadores));
    }

    public void converterObjetoJogadores(){
        jogadores[0] = equipe1.getGoleiro().getNome();
        jogadores[1] = equipe1.getLatDireito().getNome();
        jogadores[2] = equipe1.getLatEsquerdo().getNome();
        jogadores[3] = equipe1.getZagueiro().getNome();
        jogadores[4] = equipe1.getVolante().getNome();
        jogadores[5] = equipe1.getMeia().getNome();
        jogadores[6] = equipe1.getAtacante().getNome();
        jogadores[7] = equipe2.getGoleiro().getNome();
        jogadores[8] = equipe2.getLatDireito().getNome();
        jogadores[9] = equipe2.getLatEsquerdo().getNome();
        jogadores[10] = equipe2.getZagueiro().getNome();
        jogadores[11] = equipe2.getVolante().getNome();
        jogadores[12] = equipe2.getMeia().getNome();
        jogadores[13] = equipe2.getAtacante().getNome();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    public void populaEquipe(){
        Jogador jogador = new Jogador("Jean",80);
        equipe1.setGoleiro(jogador);

        jogador = new Jogador("José",69);
        equipe2.setGoleiro(jogador);

        jogador = new Jogador("Marcos",85);
        equipe1.setLatDireito(jogador);

        jogador = new Jogador("Mateus",78);
        equipe2.setLatDireito(jogador);

        jogador = new Jogador("Lucas",79);
        equipe1.setLatEsquerdo(jogador);

        jogador = new Jogador("Jorge",81);
        equipe2.setLatEsquerdo(jogador);

        jogador = new Jogador("Ricardo",90);
        equipe1.setZagueiro(jogador);

        jogador = new Jogador("Roberto",74);
        equipe2.setZagueiro(jogador);

        jogador = new Jogador("Arthur",65);
        equipe1.setVolante(jogador);

        jogador = new Jogador("Alexandre",53);
        equipe2.setVolante(jogador);

        jogador = new Jogador("Sérgio",87);
        equipe1.setMeia(jogador);

        jogador = new Jogador("Pedro",67);
        equipe2.setMeia(jogador);

        jogador = new Jogador("Luan",83);
        equipe1.setAtacante(jogador);

        jogador = new Jogador("Guilherme",80);
        equipe2.setAtacante(jogador);
    }
    */
}
