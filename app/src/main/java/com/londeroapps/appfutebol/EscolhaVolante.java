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

public class EscolhaVolante extends AppCompatActivity {

    private ListView lista;
    private Cursor cursor;
    private ArrayList<Jogador> listaJogadores = new ArrayList<Jogador>();
    private Jogador jogador;
    private String tmpNomeJogador;
    private int tmpHabilidadeJogador;
    private int[] tmp;
    private int idVolante1 = -1;
    private int idVolante2 = -1;
    private Jogador volante1;
    private Jogador volante2;
    private Equipe equipe1;
    private Equipe equipe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_volante);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        equipe1 = (Equipe) extras.getSerializable("equipe1");
        equipe2 = (Equipe) extras.getSerializable("equipe2");

        DbController dbController = new DbController(getBaseContext());
        cursor = dbController.carregaJogadorByPosicao("Volante");
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
                    if(idVolante1 != -1 && idVolante2 != -1){
                        Toast.makeText(EscolhaVolante.this,"Você já selecionou dois volantes para a partida!",Toast.LENGTH_SHORT).show();
                    } else {
                        if(idVolante1 == -1){
                            idVolante1 = i;
                            String n1 = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.NOME));
                            int n2 = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbManager.HABILIDADE)));
                            volante1 = new Jogador(n1,n2);
                        } else {
                            idVolante2 = i;
                            String n1 = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.NOME));
                            int n2 = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbManager.HABILIDADE)));
                            volante2 = new Jogador(n1,n2);
                        }
                        view.setBackgroundResource(R.drawable.bg_key);
                        view.getBackground().setAlpha(200);
                        tmp[i] = 1;
                    }
                } else {
                    if(idVolante1 == i){
                        idVolante1 = -1;
                    } else {
                        idVolante2 = -1;
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

    public void escolherMeias (View v){

        if(idVolante1 != -1 && idVolante2 != -1){
            if(volante1.getHabilidade() >= volante2.getHabilidade()){
                equipe1.setVolante(volante1);
                equipe2.setVolante(volante2);
            } else {
                equipe2.setVolante(volante1);
                equipe1.setVolante(volante2);
            }

            Intent intent = new Intent(this, EscolhaMeia.class);
            intent.putExtra("equipe1", equipe1);
            intent.putExtra("equipe2", equipe2);
            startActivity(intent);
        } else {
            Toast.makeText(EscolhaVolante.this,"Escolha dois volantes para continuar com a escalação!",Toast.LENGTH_LONG).show();
        }

    }

    public void voltarEscalacao(View view) {
        super.onBackPressed();
    }

}
