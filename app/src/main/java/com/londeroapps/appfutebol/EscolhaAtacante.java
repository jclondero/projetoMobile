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

public class EscolhaAtacante extends AppCompatActivity {

    private SeekBar habilidadeAtacanteUm;
    private SeekBar habilidadeAtacanteDois;
    private TextView textoHabilidadeAtacanteUm;
    private TextView textoHabilidadeAtacanteDois;
    private int auxHabilidadeAtacanteUm = 0;
    private int auxHabilidadeAtacanteDois = 0;
    private TextView textAtacanteUm;
    private TextView textAtacanteDois;
    private Equipe equipe1;
    private Equipe equipe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_atacante);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        equipe1 = (Equipe) extras.getSerializable("equipe1");
        equipe2 = (Equipe) extras.getSerializable("equipe2");

        habilidadeAtacanteUm = (SeekBar)findViewById(R.id.controleHabilidadeAtacanteUmSeekBar);
        textoHabilidadeAtacanteUm = (TextView) findViewById(R.id.habilidadeAtacanteUm);
        atualizarHabilidadeAtacanteUm(habilidadeAtacanteUm.getProgress());
        habilidadeAtacanteUm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeAtacanteUm(seekBar.getProgress());
                auxHabilidadeAtacanteUm = (int) seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        habilidadeAtacanteDois = (SeekBar)findViewById(R.id.controleHabilidadeAtacanteDoisSeekBar);
        textoHabilidadeAtacanteDois = (TextView) findViewById(R.id.habilidadeAtacanteDois);
        atualizarHabilidadeAtacanteDois(habilidadeAtacanteDois.getProgress());
        habilidadeAtacanteDois.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeAtacanteDois(seekBar.getProgress());
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

    public void fecharEscalacao (View v){

        textAtacanteUm = (TextView) findViewById(R.id.atacante1);
        textAtacanteDois = (TextView) findViewById(R.id.atacante2);

        if(textAtacanteUm.getText().toString().length() > 0 && textAtacanteDois.getText().toString().length() > 0) {
            if(auxHabilidadeAtacanteUm > 0 && auxHabilidadeAtacanteDois > 0) {

                String nomeAtacanteUm = textAtacanteUm.getText().toString();
                Jogador atacanteUm = new Jogador(nomeAtacanteUm, auxHabilidadeAtacanteUm);

                String nomeAtacanteDois = textAtacanteDois.getText().toString();
                Jogador atacanteDois = new Jogador(nomeAtacanteDois, auxHabilidadeAtacanteDois);

                if (auxHabilidadeAtacanteUm < auxHabilidadeAtacanteDois) {
                    equipe1.setAtacante(atacanteUm);
                    equipe2.setAtacante(atacanteDois);
                } else {
                    equipe1.setAtacante(atacanteDois);
                    equipe2.setAtacante(atacanteUm);
                }

                Intent intent = new Intent(this, Formacao.class);
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

    private void atualizarHabilidadeAtacanteUm(int progressValue) {
        textoHabilidadeAtacanteUm.setText(progressValue + " pts");
        auxHabilidadeAtacanteUm = progressValue;
    }

    private void atualizarHabilidadeAtacanteDois(int progressValue) {
        textoHabilidadeAtacanteDois.setText(progressValue + " pts");
        auxHabilidadeAtacanteDois = progressValue;
    }
}
