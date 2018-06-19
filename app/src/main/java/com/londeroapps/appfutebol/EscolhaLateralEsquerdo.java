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

public class EscolhaLateralEsquerdo extends AppCompatActivity {

    private ListView lista;
    private Cursor cursor;
    private ArrayList<Jogador> listaJogadores = new ArrayList<Jogador>();
    private Jogador jogador;
    private String tmpNomeJogador;
    private int tmpHabilidadeJogador;
    private int[] tmp;
    private int idLateralEsquerdo1 = -1;
    private int idLateralEsquerdo2 = -1;
    private Jogador lateralEsquerdo1;
    private Jogador lateralEsquerdo2;
    private Equipe equipe1;
    private Equipe equipe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_lateral_esquerdo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        equipe1 = (Equipe) extras.getSerializable("equipe1");
        equipe2 = (Equipe) extras.getSerializable("equipe2");

        DbController dbController = new DbController(getBaseContext());
        cursor = dbController.carregaJogadorByPosicao("Lateral Esquerdo");
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
                    if(idLateralEsquerdo1 != -1 && idLateralEsquerdo2 != -1){
                        Toast.makeText(EscolhaLateralEsquerdo.this,"Você já selecionou dois laterais esquerdos para a partida!",Toast.LENGTH_SHORT).show();
                    } else {
                        if(idLateralEsquerdo1 == -1){
                            idLateralEsquerdo1 = i;
                            String n1 = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.NOME));
                            int n2 = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbManager.HABILIDADE)));
                            lateralEsquerdo1 = new Jogador(n1,n2);
                        } else {
                            idLateralEsquerdo2 = i;
                            String n1 = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.NOME));
                            int n2 = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbManager.HABILIDADE)));
                            lateralEsquerdo2 = new Jogador(n1,n2);
                        }
                        view.setBackgroundResource(R.drawable.bg_key);
                        view.getBackground().setAlpha(200);
                        tmp[i] = 1;
                    }
                } else {
                    if(idLateralEsquerdo1 == i){
                        idLateralEsquerdo1 = -1;
                    } else {
                        idLateralEsquerdo2 = -1;
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

    public void escolherZagueiros (View v){

        if(idLateralEsquerdo1 != -1 && idLateralEsquerdo2 != -1){
            if(lateralEsquerdo1.getHabilidade() >= lateralEsquerdo2.getHabilidade()){
                equipe1.setLatEsquerdo(lateralEsquerdo1);
                equipe2.setLatEsquerdo(lateralEsquerdo2);
            } else {
                equipe2.setLatEsquerdo(lateralEsquerdo1);
                equipe1.setLatEsquerdo(lateralEsquerdo2);
            }

            Intent intent = new Intent(this, EscolhaZagueiro.class);
            intent.putExtra("equipe1", equipe1);
            intent.putExtra("equipe2", equipe2);
            startActivity(intent);
        } else {
            Toast.makeText(EscolhaLateralEsquerdo.this,"Escolha dois laterais esquerdos para continuar com a escalação!",Toast.LENGTH_LONG).show();
        }
    }

    public void voltarEscalacao(View view) {
        super.onBackPressed();
    }
}
