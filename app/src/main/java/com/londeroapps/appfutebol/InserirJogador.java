package com.londeroapps.appfutebol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.londeroapps.appfutebol.dao.DbController;

import java.util.ArrayList;
import java.util.List;

public class InserirJogador extends AppCompatActivity {

    EditText nome;
    String posicao;
    private SeekBar habilidadeJogador;
    private TextView textoHabilidadeJogador;
    private int auxHabilidadeJogador = 0;
    private TextView textJogador;
    String [] posicoes = new String[]{"Selecione a posição","Goleiro","Lateral Direito","Lateral Esquerdo","Zagueiro","Volante","Meia","Atacante"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_jogador);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        habilidadeJogador = (SeekBar)findViewById(R.id.controleHabilidadeJogadorSeekBar);
        textoHabilidadeJogador = (TextView) findViewById(R.id.habilidadeJogador);
        atualizarHabilidadeJogador(habilidadeJogador.getProgress());
        habilidadeJogador.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atualizarHabilidadeJogador(seekBar.getProgress());
                auxHabilidadeJogador = (int) seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Spinner spn = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, posicoes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                posicao = posicoes[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void voltar(View view){
        finish();
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

    private void atualizarHabilidadeJogador(int progressValue) {
        textoHabilidadeJogador.setText(progressValue + " pts");
        auxHabilidadeJogador = progressValue;
    }

    public void finalizarCadastro (View v){

        textJogador = (TextView) findViewById(R.id.nomeJogador);
        if(textJogador.length() > 0) {
            if (!posicao.equals(posicoes[0])) {
                if (auxHabilidadeJogador > 0) {
                    DbController dbController = new DbController(getBaseContext());
                    String resultado = dbController.inserirJogador(textJogador.getText().toString(), auxHabilidadeJogador, posicao);
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(this, "Cada Jogador deve possuir pelo menos 1 ponto de habilidade!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Selecione uma posição!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this,"É obrigatório o uso de nomes para cada Jogador!",Toast.LENGTH_LONG).show();
        }
    }
}
