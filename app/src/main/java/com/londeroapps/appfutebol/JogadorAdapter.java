package com.londeroapps.appfutebol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class JogadorAdapter extends BaseAdapter {

    private Context context;
    private String[] jogadores;
    private int[] camisas = {R.drawable.camisa_goleiro_um,R.drawable.camisa_lat_direito_um, R.drawable.camisa_lat_esquerdo_um, R.drawable.camisa_zagueiro_um,R.drawable.camisa_volante_um,R.drawable.camisa_meia_um,R.drawable.camisa_atacante_um,
            R.drawable.camisa_goleiro_dois,R.drawable.camisa_lat_direito_dois, R.drawable.camisa_lat_esquerdo_dois,R.drawable.camisa_zagueiro_dois,R.drawable.camisa_volante_dois,R.drawable.camisa_meia_dois,R.drawable.camisa_atacante_dois};

    public  JogadorAdapter(Context context, String[] jogadores){
        this.context = context;
        this.jogadores = jogadores;
    }

    @Override
    public int getCount() {
        return camisas.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        View view = LayoutInflater.from(this.context).inflate(R.layout.adapter_jogador, viewGroup, false);

        ImageView img = (ImageView) view.findViewById(R.id.imgJogador);
        img.setImageResource(camisas[i]);

        TextView textView = (TextView) view.findViewById(R.id.txtJogador);
        textView.setText(jogadores[i]);

        return view;
    }
}
