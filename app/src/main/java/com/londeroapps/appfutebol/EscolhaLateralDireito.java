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

public class EscolhaLateralDireito extends AppCompatActivity {

    private SeekBar habilidadeLatDireitoUm;
    private SeekBar habilidadeLatDireitoDois;
    private TextView textoHabilidadeLatDireitoUm;
    private TextView textoHabilidadeLatDireitoDois;
    private int auxHabilidadeLatDireitoUm = 0;
    private int auxHabilidadeLatDireitoDois = 0;
    private TextView textLatDireitoUm;
    private TextView textLatDireitoDois;
    private Equipe equipe1;
    private Equipe equipe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_lateral_direito);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        equipe1 = (Equipe) extras.getSerializable("equipe1");
        equipe2 = (Equipe) extras.getSerializable("equipe2");

        habilidadeLatDireitoUm = (SeekBar)findViewById(R.id.controleHabilidadeLatDireitoUmSeekBar);
        textoHabilidadeLatDireitoUm = (TextView) findViewById(R.id.habilidadeLatDireitoUm);
        atualizarHabilidadeLatDireitoUm(habilidadeLatDireitoUm.getProgress());
        habilidadeLatDireitoUm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeLatDireitoUm(seekBar.getProgress());
                auxHabilidadeLatDireitoUm = (int) seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        habilidadeLatDireitoDois = (SeekBar)findViewById(R.id.controleHabilidadeLatDireitoDoisSeekBar);
        textoHabilidadeLatDireitoDois = (TextView) findViewById(R.id.habilidadeLatDireitoDois);
        atualizarHabilidadeLatDireitoDois(habilidadeLatDireitoDois.getProgress());
        habilidadeLatDireitoDois.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeLatDireitoDois(seekBar.getProgress());
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

        textLatDireitoUm = (TextView) findViewById(R.id.latdireito1);
        textLatDireitoDois = (TextView) findViewById(R.id.latdireito2);

        if(textLatDireitoUm.getText().toString().length() > 0 && textLatDireitoDois.getText().toString().length() > 0) {
            if(auxHabilidadeLatDireitoUm > 0 && auxHabilidadeLatDireitoDois > 0) {

                String nomeLatDireitoUm = textLatDireitoUm.getText().toString();
                Jogador latDireitoUm = new Jogador(nomeLatDireitoUm, auxHabilidadeLatDireitoUm);

                String nomeLatDireitoDois = textLatDireitoDois.getText().toString();
                Jogador latDireitoDois = new Jogador(nomeLatDireitoDois, auxHabilidadeLatDireitoDois);

                if (auxHabilidadeLatDireitoUm >= auxHabilidadeLatDireitoDois) {
                    equipe1.setLatDireito(latDireitoUm);
                    equipe2.setLatDireito(latDireitoDois);
                } else {
                    equipe1.setLatDireito(latDireitoDois);
                    equipe2.setLatDireito(latDireitoUm);
                }

                Intent intent = new Intent(this, EscolhaLateralEsquerdo.class);
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

    private void atualizarHabilidadeLatDireitoUm(int progressValue) {
        textoHabilidadeLatDireitoUm.setText(progressValue + " pts");
        auxHabilidadeLatDireitoUm = progressValue;
    }

    private void atualizarHabilidadeLatDireitoDois(int progressValue) {
        textoHabilidadeLatDireitoDois.setText(progressValue + " pts");
        auxHabilidadeLatDireitoDois = progressValue;
    }
}
