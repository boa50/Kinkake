package br.com.boa50.kinkake.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.MainActivity;
import br.com.boa50.kinkake.activity.MusicasReservadasActivity;
import br.com.boa50.kinkake.fragment.MusicasPorPessoaFragment;
import br.com.boa50.kinkake.fragment.MusicasReservadasFragment;
import br.com.boa50.kinkake.model.Pessoa;
import br.com.boa50.kinkake.util.VariaveisEstaticas;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>{
    private ArrayList<Pessoa> pessoas;

    public PessoaAdapter(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public PessoaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pessoas, parent, false);
        return new PessoaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PessoaViewHolder holder, int position) {
        Pessoa pessoa = pessoas.get(position);

        holder.quantidade.setText(String.valueOf(pessoa.getCodigosMusicas().size()));
        holder.nome.setText(pessoa.getNome());
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
