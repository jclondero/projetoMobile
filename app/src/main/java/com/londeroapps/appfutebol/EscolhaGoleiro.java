package com.londeroapps.appfutebol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.londeroapps.appfutebol.model.Equipe;
import com.londeroapps.appfutebol.model.Jogador;

public class EscolhaGoleiro extends AppCompatActivity {

    private SeekBar habilidadeGoleiroUm;
    private SeekBar habilidadeGoleiroDois;
    private TextView textoHabilidadeGoleiroUm;
    private TextView textoHabilidadeGoleiroDois;
    private int auxHabilidadeGoleiroUm = 0;
    private int auxHabilidadeGoleiroDois = 0;
    private TextView textGoleiroUm;
    private TextView textGoleiroDois;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_goleiro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        habilidadeGoleiroUm = (SeekBar)findViewById(R.id.controleHabilidadeGoleiroUmSeekBar);
        textoHabilidadeGoleiroUm = (TextView) findViewById(R.id.habilidadeGoleiroUm);
        atualizarHabilidadeGoleiroUm(habilidadeGoleiroUm.getProgress());
        habilidadeGoleiroUm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeGoleiroUm(seekBar.getProgress());
                auxHabilidadeGoleiroUm = (int) seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        habilidadeGoleiroDois = (SeekBar)findViewById(R.id.controleHabilidadeGoleiroDoisSeekBar);
        textoHabilidadeGoleiroDois = (TextView) findViewById(R.id.habilidadeGoleiroDois);
        atualizarHabilidadeGoleiroDois(habilidadeGoleiroDois.getProgress());
        habilidadeGoleiroDois.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeGoleiroDois(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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

    public void escolherLaterais (View v){

        textGoleiroUm = (TextView) findViewById(R.id.goleiro1);
        textGoleiroDois = (TextView) findViewById(R.id.goleiro2);

        if(textGoleiroUm.getText().toString().length() > 0 && textGoleiroDois.getText().toString().length() > 0) {
            if(auxHabilidadeGoleiroUm > 0 && auxHabilidadeGoleiroDois > 0) {
                Equipe equipe1 = new Equipe();
                Equipe equipe2 = new Equipe();

                String nomeGoleiroUm = textGoleiroUm.getText().toString();
                Jogador goleiroUm = new Jogador(nomeGoleiroUm, auxHabilidadeGoleiroUm);

                String nomeGoleiroDois = textGoleiroDois.getText().toString();
                Jogador goleiroDois = new Jogador(nomeGoleiroDois, auxHabilidadeGoleiroDois);

                if (auxHabilidadeGoleiroUm >= auxHabilidadeGoleiroDois) {
                    equipe1.setGoleiro(goleiroUm);
                    equipe2.setGoleiro(goleiroDois);
                } else {
                    equipe1.setGoleiro(goleiroDois);
                    equipe2.setGoleiro(goleiroUm);
                }

                Intent intent = new Intent(this, EscolhaLateralDireito.class);
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

    private void atualizarHabilidadeGoleiroUm(int progressValue) {
        textoHabilidadeGoleiroUm.setText(progressValue + " pts");
        auxHabilidadeGoleiroUm = progressValue;
    }

    private void atualizarHabilidadeGoleiroDois(int progressValue) {
        textoHabilidadeGoleiroDois.setText(progressValue + " pts");
        auxHabilidadeGoleiroDois = progressValue;
    }

}
