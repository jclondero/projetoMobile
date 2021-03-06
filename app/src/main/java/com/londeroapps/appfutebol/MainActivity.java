package com.londeroapps.appfutebol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.londeroapps.appfutebol.dao.DbManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void montarTime(View v){
        Intent intent = new Intent(this, EscolhaGoleiro.class);
        startActivity(intent);
    }

    public void gerenciarJogadores(View v){
        Intent intent = new Intent(this, GerenciarJogadores.class);
        startActivity(intent);
    }

    public void sairDoApp(View v){
        finishAffinity();
    }
}
