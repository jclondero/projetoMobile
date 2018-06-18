package com.londeroapps.appfutebol;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.londeroapps.appfutebol.dao.DbController;
import com.londeroapps.appfutebol.dao.DbManager;
import com.londeroapps.appfutebol.model.Equipe;
import com.londeroapps.appfutebol.model.Jogador;

import java.util.ArrayList;

public class EscolhaGoleiro extends AppCompatActivity {

    private ListView lista;
    private Cursor cursor;
    private ArrayList<Jogador> listaJogadores = new ArrayList<Jogador>();
    private Jogador jogador;
    private String tmpNomeJogador;
    private int tmpHabilidadeJogador;
    private int[] tmp;
    private int idGoleiro1 = -1;
    private int idGoleiro2 = -1;
    private Jogador goleiro1;
    private Jogador goleiro2;
    private Equipe equipe1 = new Equipe();
    private Equipe equipe2 = new Equipe();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_goleiro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();

        DbController dbController = new DbController(getBaseContext());
        cursor = dbController.carregaJogadorByPosicao("Goleiro");
        int temp = 0;

        while(!cursor.isAfterLast()) {
            tmpNomeJogador = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.NOME));
            tmpHabilidadeJogador = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbManager.HABILIDADE)));
            jogador = new Jogador(tmpNomeJogador,tmpHabilidadeJogador);
            listaJogadores.add(jogador);
            temp += 1;
            cursor.moveToNext();
        }

        tmp = new int[temp];
        for(int i = 0; i < temp; i++){
            tmp[i] = 0;
        }

        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(new EscalacaoAdapter(this,listaJogadores));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nomeTmp, habilidadeTmp;
                cursor.moveToPosition(i);

                nomeTmp = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.NOME));
                habilidadeTmp = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.HABILIDADE));

                if(tmp[i] != 1){
                    if(idGoleiro1 != -1 && idGoleiro2 != -1){
                        Toast.makeText(EscolhaGoleiro.this,"Você já selecionou dois goleiros para a partida!",Toast.LENGTH_SHORT).show();
                    } else {
                        if(idGoleiro1 == -1){
                            idGoleiro1 = i;
                            String n1 = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.NOME));
                            int n2 = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbManager.HABILIDADE)));
                            goleiro1 = new Jogador(n1,n2);
                        } else {
                            idGoleiro2 = i;
                            String n1 = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.NOME));
                            int n2 = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbManager.HABILIDADE)));
                            goleiro2 = new Jogador(n1,n2);
                        }
                        view.setBackgroundResource(R.drawable.bg_key);
                        view.getBackground().setAlpha(200);
                        tmp[i] = 1;
                    }
                } else {
                    if(idGoleiro1 == i){
                        idGoleiro1 = -1;
                    } else {
                        idGoleiro2 = -1;
                    }
                    view.getBackground().setAlpha(102);
                    tmp[i] = 0;
                }
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
        if(idGoleiro1 != -1 && idGoleiro2 != -1){
            if(goleiro1.getHabilidade() >= goleiro2.getHabilidade()){
                equipe1.setGoleiro(goleiro1);
                equipe2.setGoleiro(goleiro2);
            } else {
                equipe2.setGoleiro(goleiro1);
                equipe1.setGoleiro(goleiro2);
            }

            Intent intent = new Intent(this, EscolhaLateralDireito.class);
            intent.putExtra("equipe1", equipe1);
            intent.putExtra("equipe2", equipe2);
            startActivity(intent);
        } else {
            Toast.makeText(EscolhaGoleiro.this,"Escolha dois goleiros para continuar com a escalação!",Toast.LENGTH_LONG).show();
        }
    }

    public void voltarEscalacao(View view) {
        super.onBackPressed();
    }

}
