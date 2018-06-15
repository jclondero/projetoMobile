package com.londeroapps.appfutebol;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.londeroapps.appfutebol.dao.DbController;
import com.londeroapps.appfutebol.dao.DbManager;

public class GerenciarJogadores extends AppCompatActivity {

    private ListView lista;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_jogadores);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        carregaDados();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GerenciarJogadores.this,InserirJogador.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregaDados();
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

    public void carregaDados(){

        DbController dbController = new DbController(getBaseContext());
        cursor = dbController.carregaJogadores();

        String[] nomeCampos = new String[]{DbManager.ID,DbManager.NOME, DbManager.HABILIDADE,DbManager.POSICAO};
        int[] idViews = new int[]{R.id.idJogador,R.id.nomeJogador,R.id.habilidadeJogador,R.id.posicaoJogador};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_layout_jogadores,cursor,nomeCampos,idViews,0);
        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id;
                cursor.moveToPosition(i);

                id = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.ID));
                Intent intent = new Intent(GerenciarJogadores.this,UpdateJogador.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

    }
}
