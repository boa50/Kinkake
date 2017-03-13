package br.com.boa50.kinkake.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.model.Pessoa;

public class PessoaAdapter extends ArrayAdapter<Pessoa>{
    private Context context;
    private ArrayList<Pessoa> pessoas;

    public PessoaAdapter(@NonNull Context context, @NonNull ArrayList<Pessoa> objects) {
        super(context, 0, objects);
        this.context = context;
        this.pessoas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.lista_pessoas, parent, false);

        TextView quantidade = (TextView) view.findViewById(R.id.tv_lista_pessoas_quantidade);
        TextView nome = (TextView) view.findViewById(R.id.tv_lista_pessoas_nome);
        Pessoa pessoa = pessoas.get(position);

        quantidade.setText(String.valueOf(pessoa.getCodigosMusicas().size()));
        nome.setText(pessoa.getNome());

        return view;
    }
}
