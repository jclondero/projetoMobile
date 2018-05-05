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

public class EscolhaMeia extends AppCompatActivity {

    private SeekBar habilidadeMeiaUm;
    private SeekBar habilidadeMeiaDois;
    private TextView textoHabilidadeMeiaUm;
    private TextView textoHabilidadeMeiaDois;
    private int auxHabilidadeMeiaUm = 0;
    private int auxHabilidadeMeiaDois = 0;
    private TextView textMeiaUm;
    private TextView textMeiaDois;
    private Equipe equipe1;
    private Equipe equipe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_meia);

        Bundle extras = getIntent().getExtras();
        equipe1 = (Equipe) extras.getSerializable("equipe1");
        equipe2 = (Equipe) extras.getSerializable("equipe2");

        habilidadeMeiaUm = (SeekBar)findViewById(R.id.controleHabilidadeMeiaUmSeekBar);
        textoHabilidadeMeiaUm = (TextView) findViewById(R.id.habilidadeMeiaUm);
        atualizarHabilidadeMeiaUm(habilidadeMeiaUm.getProgress());
        habilidadeMeiaUm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeMeiaUm(seekBar.getProgress());
                auxHabilidadeMeiaUm = (int) seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        habilidadeMeiaDois = (SeekBar)findViewById(R.id.controleHabilidadeMeiaDoisSeekBar);
        textoHabilidadeMeiaDois = (TextView) findViewById(R.id.habilidadeMeiaDois);
        atualizarHabilidadeMeiaDois(habilidadeMeiaDois.getProgress());
        habilidadeMeiaDois.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeMeiaDois(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void escolherAtacantes (View v){

        textMeiaUm = (TextView) findViewById(R.id.meia1);
        textMeiaDois = (TextView) findViewById(R.id.meia2);

        if(textMeiaUm.getText().toString().length() > 0 && textMeiaDois.getText().toString().length() > 0) {
            if(auxHabilidadeMeiaUm > 0 && auxHabilidadeMeiaDois > 0) {

                String nomeMeiaUm = textMeiaUm.getText().toString();
                Jogador meiaUm = new Jogador(nomeMeiaUm, auxHabilidadeMeiaUm);

                String nomeMeiaDois = textMeiaDois.getText().toString();
                Jogador meiaDois = new Jogador(nomeMeiaDois, auxHabilidadeMeiaDois);

                if (auxHabilidadeMeiaUm >= auxHabilidadeMeiaDois) {
                    equipe1.setMeia(meiaUm);
                    equipe2.setMeia(meiaDois);
                } else {
                    equipe1.setMeia(meiaDois);
                    equipe2.setMeia(meiaUm);
                }

                Intent intent = new Intent(this, EscolhaAtacante.class);
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

    private void atualizarHabilidadeMeiaUm(int progressValue) {
        textoHabilidadeMeiaUm.setText(progressValue + " pts");
        auxHabilidadeMeiaUm = progressValue;
    }

    private void atualizarHabilidadeMeiaDois(int progressValue) {
        textoHabilidadeMeiaDois.setText(progressValue + " pts");
        auxHabilidadeMeiaDois = progressValue;
    }
}
