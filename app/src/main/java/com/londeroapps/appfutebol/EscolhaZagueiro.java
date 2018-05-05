package com.londeroapps.appfutebol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.londeroapps.appfutebol.model.Equipe;
import com.londeroapps.appfutebol.model.Jogador;

public class EscolhaZagueiro extends AppCompatActivity {

    private SeekBar habilidadeZagueiroUm;
    private SeekBar habilidadeZagueiroDois;
    private TextView textoHabilidadeZagueiroUm;
    private TextView textoHabilidadeZagueiroDois;
    private int auxHabilidadeZagueiroUm = 0;
    private int auxHabilidadeZagueiroDois = 0;
    private TextView textZagueiroUm;
    private TextView textZagueiroDois;
    private Equipe equipe1;
    private Equipe equipe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_zagueiro);

        Bundle extras = getIntent().getExtras();
        equipe1 = (Equipe) extras.getSerializable("equipe1");
        equipe2 = (Equipe) extras.getSerializable("equipe2");

        habilidadeZagueiroUm = (SeekBar)findViewById(R.id.controleHabilidadeZagueiroUmSeekBar);
        textoHabilidadeZagueiroUm = (TextView) findViewById(R.id.habilidadeZagueiroUm);
        atualizarHabilidadeZagueiroUm(habilidadeZagueiroUm.getProgress());
        habilidadeZagueiroUm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeZagueiroUm(seekBar.getProgress());
                auxHabilidadeZagueiroUm = (int) seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        habilidadeZagueiroDois = (SeekBar)findViewById(R.id.controleHabilidadeZagueiroDoisSeekBar);
        textoHabilidadeZagueiroDois = (TextView) findViewById(R.id.habilidadeZagueiroDois);
        atualizarHabilidadeZagueiroDois(habilidadeZagueiroDois.getProgress());
        habilidadeZagueiroDois.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeZagueiroDois(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void escolherVolantes (View v){

        textZagueiroUm = (TextView) findViewById(R.id.zagueiro1);
        textZagueiroDois = (TextView) findViewById(R.id.zagueiro2);

        if(textZagueiroUm.getText().toString().length() > 0 && textZagueiroDois.getText().toString().length() > 0) {
            if(auxHabilidadeZagueiroUm > 0 && auxHabilidadeZagueiroDois > 0) {

                String nomeZagueiroUm = textZagueiroUm.getText().toString();
                Jogador zagueiroUm = new Jogador(nomeZagueiroUm, auxHabilidadeZagueiroUm);

                String nomeZagueiroDois = textZagueiroDois.getText().toString();
                Jogador zagueiroDois = new Jogador(nomeZagueiroDois, auxHabilidadeZagueiroDois);

                if (auxHabilidadeZagueiroUm < auxHabilidadeZagueiroDois) {
                    equipe1.setZagueiro(zagueiroUm);
                    equipe2.setZagueiro(zagueiroDois);
                } else {
                    equipe1.setZagueiro(zagueiroDois);
                    equipe2.setZagueiro(zagueiroUm);
                }

                Intent intent = new Intent(this, EscolhaVolante.class);
                intent.putExtra("equipe1", equipe1);
                intent.putExtra("equipe2", equipe2);
                startActivity(intent);
            } else {
                Toast.makeText(this,"Cada Jogador deve possuir pelo menos 1 ponto de habilidade!",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this,"É obrigatório o uso de nomes para cada Jogador!",Toast.LENGTH_LONG).show();
        }
    }

    public void voltarEscalacao(View view) {
        super.onBackPressed();
    }

    private void atualizarHabilidadeZagueiroUm(int progressValue) {
        textoHabilidadeZagueiroUm.setText(progressValue + " pts");
        auxHabilidadeZagueiroUm = progressValue;
    }

    private void atualizarHabilidadeZagueiroDois(int progressValue) {
        textoHabilidadeZagueiroDois.setText(progressValue + " pts");
        auxHabilidadeZagueiroDois = progressValue;
    }

}
