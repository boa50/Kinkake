package br.com.boa50.kinkake.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.model.Cantor;

public class CantorAdapter extends ArrayAdapter<Cantor> {

    private Context context;
    private ArrayList<Cantor> cantores;

    public CantorAdapter(Context context, ArrayList<Cantor> objects) {
        super(context, 0, objects);
        this.context = context;
        this.cantores = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.lista_cantores, parent, false);

        TextView quantidade = (TextView) view.findViewById(R.id.tv_lista_cantores_quantidade);
        TextView nome = (TextView) view.findViewById(R.id.tv_lista_cantores_nome);

        Cantor cantor = cantores.get(position);

        quantidade.setText(String.valueOf(cantor.getCodigosMusicas().size()));
        nome.setText(cantor.getNome());

        return view;
    }
}