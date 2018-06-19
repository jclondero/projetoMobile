package com.londeroapps.appfutebol;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.londeroapps.appfutebol.dao.DbController;
import com.londeroapps.appfutebol.dao.DbManager;
import com.londeroapps.appfutebol.model.Equipe;
import com.londeroapps.appfutebol.model.Jogador;

import java.util.ArrayList;

public class EscolhaAtacante extends AppCompatActivity {

    private ListView lista;
    private Cursor cursor;
    private ArrayList<Jogador> listaJogadores = new ArrayList<Jogador>();
    private Jogador jogador;
    private String tmpNomeJogador;
    private int tmpHabilidadeJogador;
    private int[] tmp;
    private int idAtacante1 = -1;
    private int idAtacante2 = -1;
    private Jogador atacante1;
    private Jogador atacante2;
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

        DbController dbController = new DbController(getBaseContext());
        cursor = dbController.carregaJogadorByPosicao("Atacante");
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

                if(tmp[i] != 1){
                    if(idAtacante1 != -1 && idAtacante2 != -1){
                        Toast.makeText(EscolhaAtacante.this,"Você já selecionou dois atacantes para a partida!",Toast.LENGTH_SHORT).show();
                    } else {
                        if(idAtacante1 == -1){
                            idAtacante1 = i;
                            String n1 = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.NOME));
                            int n2 = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbManager.HABILIDADE)));
                            atacante1 = new Jogador(n1,n2);
                        } else {
                            idAtacante2 = i;
                            String n1 = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.NOME));
                            int n2 = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbManager.HABILIDADE)));
                            atacante2 = new Jogador(n1,n2);
                        }
                        view.setBackgroundResource(R.drawable.bg_key);
                        view.getBackground().setAlpha(200);
                        tmp[i] = 1;
                    }
                } else {
                    if(idAtacante1 == i){
                        idAtacante1 = -1;
                    } else {
                        idAtacante2 = -1;
                    }
                    view.getBackground().setAlpha(0);
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

    public void fecharEscalacao (View v){

        if(idAtacante1 != -1 && idAtacante2 != -1){
            if(atacante1.getHabilidade() >= atacante2.getHabilidade()){
                equipe1.setAtacante(atacante1);
                equipe2.setAtacante(atacante2);
            } else {
                equipe2.setAtacante(atacante1);
                equipe1.setAtacante(atacante2);
            }

            Intent intent = new Intent(this, Formacao.class);
            intent.putExtra("equipe1", equipe1);
            intent.putExtra("equipe2", equipe2);
            startActivity(intent);
        } else {
            Toast.makeText(EscolhaAtacante.this,"Escolha dois atacantes para continuar com a escalação!",Toast.LENGTH_LONG).show();
        }
    }

    public void voltarEscalacao(View view) {
        super.onBackPressed();
    }

}
