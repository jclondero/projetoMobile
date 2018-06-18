package com.londeroapps.appfutebol;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.londeroapps.appfutebol.R;
import com.londeroapps.appfutebol.dao.DbManager;
import com.londeroapps.appfutebol.model.Jogador;

import java.util.ArrayList;

public class EscalacaoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Jogador> listaJogadores;

    public EscalacaoAdapter(Context context, ArrayList<Jogador> listaJogadores){
        super();
        this.context = context;
        this.listaJogadores = listaJogadores;
    }

    @Override
    public int getCount() {
        return listaJogadores.size();
    }

    @Override
    public Object getItem(int i) {
        return listaJogadores.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        String nome = listaJogadores.get(i).getNome();
        String habilidade = String.valueOf(listaJogadores.get(i).getHabilidade());
        View v = LayoutInflater.from(context).inflate(R.layout.list_layout_escolha,viewGroup,false);
        TextView txtNome = (TextView) v.findViewById(R.id.nomeJogador);
        txtNome.setText(nome);
        TextView txtHabilidade = (TextView) v.findViewById(R.id.habilidadeJogador);
        txtHabilidade.setText(habilidade);
        return v;
    }
}
