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

public class EscolhaMeia extends AppCompatActivity {

    private ListView lista;
    private Cursor cursor;
    private ArrayList<Jogador> listaJogadores = new ArrayList<Jogador>();
    private Jogador jogador;
    private String tmpNomeJogador;
    private int tmpHabilidadeJogador;
    private int[] tmp;
    private int idMeia1 = -1;
    private int idMeia2 = -1;
    private Jogador meia1;
    private Jogador meia2;
    private Equipe equipe1;
    private Equipe equipe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_meia);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        equipe1 = (Equipe) extras.getSerializable("equipe1");
        equipe2 = (Equipe) extras.getSerializable("equipe2");

        DbController dbController = new DbController(getBaseContext());
        cursor = dbController.carregaJogadorByPosicao("Meia");
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
                    if(idMeia1 != -1 && idMeia2 != -1){
                        Toast.makeText(EscolhaMeia.this,"Você já selecionou dois meias para a partida!",Toast.LENGTH_SHORT).show();
                    } else {
                        if(idMeia1 == -1){
                            idMeia1 = i;
                            String n1 = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.NOME));
                            int n2 = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbManager.HABILIDADE)));
                            meia1 = new Jogador(n1,n2);
                        } else {
                            idMeia2 = i;
                            String n1 = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.NOME));
                            int n2 = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbManager.HABILIDADE)));
                            meia2 = new Jogador(n1,n2);
                        }
                        view.setBackgroundResource(R.drawable.bg_key);
                        view.getBackground().setAlpha(200);
                        tmp[i] = 1;
                    }
                } else {
                    if(idMeia1 == i){
                        idMeia1 = -1;
                    } else {
                        idMeia2 = -1;
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

    public void escolherAtacantes (View v){

        if(idMeia1 != -1 && idMeia2 != -1){
            if(meia1.getHabilidade() >= meia2.getHabilidade()){
                equipe1.setMeia(meia1);
                equipe2.setMeia(meia2);
            } else {
                equipe2.setMeia(meia1);
                equipe1.setMeia(meia2);
            }

            Intent intent = new Intent(this, EscolhaAtacante.class);
            intent.putExtra("equipe1", equipe1);
            intent.putExtra("equipe2", equipe2);
            startActivity(intent);
        } else {
            Toast.makeText(EscolhaMeia.this,"Escolha dois meias para continuar com a escalação!",Toast.LENGTH_LONG).show();
        }
    }

    public void voltarEscalacao(View view) {
        super.onBackPressed();
    }

}
