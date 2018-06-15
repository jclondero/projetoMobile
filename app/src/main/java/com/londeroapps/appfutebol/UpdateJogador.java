package com.londeroapps.appfutebol;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
import com.londeroapps.appfutebol.dao.DbManager;

public class UpdateJogador extends AppCompatActivity {

    String id;
    EditText nome;
    String posicao;
    private SeekBar habilidadeJogador;
    private TextView textoHabilidadeJogador;
    private int auxHabilidadeJogador;
    private TextView textJogador;
    String [] posicoes = new String[]{"Selecione a posição","Goleiro","Lateral Direito","Lateral Esquerdo","Zagueiro","Volante","Meia","Atacante"};
    DbController dbController;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_jogador);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = this.getIntent().getStringExtra("id");
        dbController = new DbController(getBaseContext());
        nome = (EditText) findViewById(R.id.nomeJogador);

        cursor = dbController.carregaJogadorById(Integer.parseInt(id));
        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow(DbManager.NOME)));
        auxHabilidadeJogador = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbManager.HABILIDADE)));
        posicao = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.POSICAO));

        habilidadeJogador = (SeekBar)findViewById(R.id.controleHabilidadeJogadorSeekBar);
        habilidadeJogador.setProgress(auxHabilidadeJogador);
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
                atualizarHabilidadeJogador(seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final Spinner spn = (Spinner) findViewById(R.id.spinner);
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

        int tmp = verificaPosicao();
        if(tmp > -1){
            spn.setSelection(tmp);
        }
    }

    public int verificaPosicao(){
        for(int i = 0; i < posicoes.length; i++){
            if(posicao.equals(posicoes[i])){
                return i;
            }
        }
        return -1;
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

    public void finalizarAlteracao (View v){

        textJogador = (TextView) findViewById(R.id.nomeJogador);
        if(textJogador.length() > 0) {
            if(textJogador.length() <= 20) {
                if (!posicao.equals(posicoes[0])) {
                    if (auxHabilidadeJogador > 0) {
                        DbController dbController = new DbController(getBaseContext());
                        String resultado = dbController.alteraRegistro(Integer.parseInt(id),textJogador.getText().toString(), auxHabilidadeJogador, posicao);
                        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Cada Jogador deve possuir pelo menos 1 ponto de habilidade!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Selecione uma posição!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this,"O nome do jogador deve possuir no máximo 20 caracteres!",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this,"É obrigatório o uso de nomes para cada Jogador!",Toast.LENGTH_LONG).show();
        }
    }

    public void finalizarExclusao(View view){
        final AlertDialog alerta;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir Jogador");
        builder.setMessage("Você deseja realmente excluir " + nome.getText().toString() + "?");

        //Confirma Exclusão
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                String resultado = dbController.deletaRegistro(Integer.parseInt(id));
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                finish();
            }
        });

        //Cancela Exclusão
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        alerta = builder.create();
        alerta.show();
    }
}
