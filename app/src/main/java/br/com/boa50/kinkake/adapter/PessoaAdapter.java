package br.com.boa50.kinkake.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.MainActivity;
import br.com.boa50.kinkake.fragment.MusicasReservadasFragment;
import br.com.boa50.kinkake.model.Pessoa;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>{
    private ArrayList<Pessoa> pessoas;
    private MusicasReservadasFragment fragment;

    public PessoaAdapter(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public PessoaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pessoas, parent, false);
        fragment = ((MainActivity) parent.getContext()).getMusicasReservadasFragment();
        return new PessoaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PessoaViewHolder holder, int position) {
        Pessoa pessoa = pessoas.get(position);
        final int posicao = position;

        holder.quantidade.setText(String.valueOf(pessoa.getCodigosMusicas().size()));
        holder.nome.setText(pessoa.getNome());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.itemClickListener(v, posicao);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                fragment.itemLongClickListener(v, posicao);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return pessoas.size();
    }

    static class PessoaViewHolder extends RecyclerView.ViewHolder{
        TextView quantidade;
        TextView nome;

        PessoaViewHolder(View itemView) {
            super(itemView);
            quantidade = (TextView) itemView.findViewById(R.id.tv_lista_pessoas_quantidade);
            nome = (TextView) itemView.findViewById(R.id.tv_lista_pessoas_nome);
        }
    }
}
