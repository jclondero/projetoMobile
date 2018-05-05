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

public class EscolhaVolante extends AppCompatActivity {

    private SeekBar habilidadeVolanteUm;
    private SeekBar habilidadeVolanteDois;
    private TextView textoHabilidadeVolanteUm;
    private TextView textoHabilidadeVolanteDois;
    private int auxHabilidadeVolanteUm = 0;
    private int auxHabilidadeVolanteDois = 0;
    private TextView textVolanteUm;
    private TextView textVolanteDois;
    private Equipe equipe1;
    private Equipe equipe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_volante);

        Bundle extras = getIntent().getExtras();
        equipe1 = (Equipe) extras.getSerializable("equipe1");
        equipe2 = (Equipe) extras.getSerializable("equipe2");

        habilidadeVolanteUm = (SeekBar)findViewById(R.id.controleHabilidadeVolanteUmSeekBar);
        textoHabilidadeVolanteUm = (TextView) findViewById(R.id.habilidadeVolanteUm);
        atualizarHabilidadeVolanteUm(habilidadeVolanteUm.getProgress());
        habilidadeVolanteUm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeVolanteUm(seekBar.getProgress());
                auxHabilidadeVolanteUm = (int) seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        habilidadeVolanteDois = (SeekBar)findViewById(R.id.controleHabilidadeVolanteDoisSeekBar);
        textoHabilidadeVolanteDois = (TextView) findViewById(R.id.habilidadeVolanteDois);
        atualizarHabilidadeVolanteDois(habilidadeVolanteDois.getProgress());
        habilidadeVolanteDois.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeVolanteDois(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void escolherMeias (View v){

        textVolanteUm = (TextView) findViewById(R.id.volante1);
        textVolanteDois = (TextView) findViewById(R.id.volante2);

        if(textVolanteUm.getText().toString().length() > 0 && textVolanteDois.getText().toString().length() > 0) {
            if(auxHabilidadeVolanteUm > 0 && auxHabilidadeVolanteDois > 0) {

                String nomeVolanteUm = textVolanteUm.getText().toString();
                Jogador volanteUm = new Jogador(nomeVolanteUm, auxHabilidadeVolanteUm);

                String nomeVolanteDois = textVolanteDois.getText().toString();
                Jogador volanteDois = new Jogador(nomeVolanteDois, auxHabilidadeVolanteDois);

                if (auxHabilidadeVolanteUm >= auxHabilidadeVolanteDois) {
                    equipe1.setVolante(volanteUm);
                    equipe2.setVolante(volanteDois);
                } else {
                    equipe1.setVolante(volanteDois);
                    equipe2.setVolante(volanteUm);
                }

                Intent intent = new Intent(this, EscolhaMeia.class);
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

    private void atualizarHabilidadeVolanteUm(int progressValue) {
        textoHabilidadeVolanteUm.setText(progressValue + " pts");
        auxHabilidadeVolanteUm = progressValue;
    }

    private void atualizarHabilidadeVolanteDois(int progressValue) {
        textoHabilidadeVolanteDois.setText(progressValue + " pts");
        auxHabilidadeVolanteDois = progressValue;
    }
}
