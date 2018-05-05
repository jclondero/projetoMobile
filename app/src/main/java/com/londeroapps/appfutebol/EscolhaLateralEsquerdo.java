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

public class EscolhaLateralEsquerdo extends AppCompatActivity {

    private SeekBar habilidadeLatEsquerdoUm;
    private SeekBar habilidadeLatEsquerdoDois;
    private TextView textoHabilidadeLatEsquerdoUm;
    private TextView textoHabilidadeLatEsquerdoDois;
    private int auxHabilidadeLatEsquerdoUm = 0;
    private int auxHabilidadeLatEsquerdoDois = 0;
    private TextView textLatEsquerdoUm;
    private TextView textLatEsquerdoDois;
    private Equipe equipe1;
    private Equipe equipe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_lateral_esquerdo);

        Bundle extras = getIntent().getExtras();
        equipe1 = (Equipe) extras.getSerializable("equipe1");
        equipe2 = (Equipe) extras.getSerializable("equipe2");

        habilidadeLatEsquerdoUm = (SeekBar)findViewById(R.id.controleHabilidadeLatEsquerdoUmSeekBar);
        textoHabilidadeLatEsquerdoUm = (TextView) findViewById(R.id.habilidadeLatEsquerdoUm);
        atualizarHabilidadeLatEsquerdoUm(habilidadeLatEsquerdoUm.getProgress());
        habilidadeLatEsquerdoUm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeLatEsquerdoUm(seekBar.getProgress());
                auxHabilidadeLatEsquerdoUm = (int) seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        habilidadeLatEsquerdoDois = (SeekBar)findViewById(R.id.controleHabilidadeLatEsquerdoDoisSeekBar);
        textoHabilidadeLatEsquerdoDois = (TextView) findViewById(R.id.habilidadeLatEsquerdoDois);
        atualizarHabilidadeLatEsquerdoDois(habilidadeLatEsquerdoDois.getProgress());
        habilidadeLatEsquerdoDois.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeLatEsquerdoDois(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void escolherZagueiros (View v){

        textLatEsquerdoUm = (TextView) findViewById(R.id.latesquerdo1);
        textLatEsquerdoDois = (TextView) findViewById(R.id.latesquerdo2);

        if(textLatEsquerdoUm.getText().toString().length() > 0 && textLatEsquerdoDois.getText().toString().length() > 0) {
            if(auxHabilidadeLatEsquerdoUm > 0 && auxHabilidadeLatEsquerdoDois > 0) {

                String nomeLatEsquerdoUm = textLatEsquerdoUm.getText().toString();
                Jogador latEsquerdoUm = new Jogador(nomeLatEsquerdoUm, auxHabilidadeLatEsquerdoUm);

                String nomeLatEsquerdoDois = textLatEsquerdoDois.getText().toString();
                Jogador latEsquerdoDois = new Jogador(nomeLatEsquerdoDois, auxHabilidadeLatEsquerdoDois);

                if (auxHabilidadeLatEsquerdoUm < auxHabilidadeLatEsquerdoDois) {
                    equipe1.setLatEsquerdo(latEsquerdoUm);
                    equipe2.setLatEsquerdo(latEsquerdoDois);
                } else {
                    equipe1.setLatEsquerdo(latEsquerdoDois);
                    equipe2.setLatEsquerdo(latEsquerdoUm);
                }

                Intent intent = new Intent(this, EscolhaZagueiro.class);
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

    private void atualizarHabilidadeLatEsquerdoUm(int progressValue) {
        textoHabilidadeLatEsquerdoUm.setText(progressValue + " pts");
        auxHabilidadeLatEsquerdoUm = progressValue;
    }

    private void atualizarHabilidadeLatEsquerdoDois(int progressValue) {
        textoHabilidadeLatEsquerdoDois.setText(progressValue + " pts");
        auxHabilidadeLatEsquerdoDois = progressValue;
    }
}
