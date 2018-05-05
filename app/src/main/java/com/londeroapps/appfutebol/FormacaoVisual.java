package com.londeroapps.appfutebol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.londeroapps.appfutebol.model.Equipe;

public class FormacaoVisual extends AppCompatActivity {

    Equipe equipe1;
    Equipe equipe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formacao_visual);

        Bundle extras = getIntent().getExtras();
        equipe1 = (Equipe) extras.getSerializable("equipe1");
        equipe2 = (Equipe) extras.getSerializable("equipe2");

        TextView goleiro1 = (TextView) findViewById(R.id.nomeGoleiro1);
        goleiro1.setText(equipe1.getGoleiro().getNome());

        TextView goleiro2 = (TextView) findViewById(R.id.nomeGoleiro2);
        goleiro2.setText(equipe2.getGoleiro().getNome());

        TextView latDireito1 = (TextView) findViewById(R.id.nomeLatDireito1);
        latDireito1.setText(equipe1.getLatDireito().getNome());

        TextView latDireito2 = (TextView) findViewById(R.id.nomeLatDireito2);
        latDireito2.setText(equipe2.getLatDireito().getNome());

        TextView latEsquerdo1 = (TextView) findViewById(R.id.nomeLatEsquerdo1);
        latEsquerdo1.setText(equipe1.getLatEsquerdo().getNome());

        TextView latEsquerdo2 = (TextView) findViewById(R.id.nomeLatEsquerdo2);
        latEsquerdo2.setText(equipe2.getLatEsquerdo().getNome());

        TextView zagueiro1 = (TextView) findViewById(R.id.nomeZagueiro1);
        zagueiro1.setText(equipe1.getZagueiro().getNome());

        TextView zagueiro2 = (TextView) findViewById(R.id.nomeZagueiro2);
        zagueiro2.setText(equipe2.getZagueiro().getNome());

        TextView volante1 = (TextView) findViewById(R.id.nomeVolante1);
        volante1.setText(equipe1.getVolante().getNome());

        TextView volante2 = (TextView) findViewById(R.id.nomeVolante2);
        volante2.setText(equipe2.getVolante().getNome());

        TextView meia1 = (TextView) findViewById(R.id.nomeMeia1);
        meia1.setText(equipe1.getMeia().getNome());

        TextView meia2 = (TextView) findViewById(R.id.nomeMeia2);
        meia2.setText(equipe2.getMeia().getNome());

        TextView atacante1 = (TextView) findViewById(R.id.nomeAtacante1);
        atacante1.setText(equipe1.getAtacante().getNome());

        TextView atacante2 = (TextView) findViewById(R.id.nomeAtacante2);
        atacante2.setText(equipe2.getAtacante().getNome());
    }
}
