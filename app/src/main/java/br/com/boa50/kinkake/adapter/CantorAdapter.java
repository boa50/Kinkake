package br.com.boa50.kinkake.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.MainActivity;
import br.com.boa50.kinkake.fragment.CantoresFragment;
import br.com.boa50.kinkake.model.Cantor;

public class CantorAdapter extends RecyclerView.Adapter<CantorAdapter.CantorViewHolder> {
    private ArrayList<Cantor> cantores;
    private CantoresFragment fragment;

    public CantorAdapter(ArrayList<Cantor> cantores) {
        this.cantores = cantores;
    }

    @Override
    public CantorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_cantores, parent, false);
        fragment = ((MainActivity) parent.getContext()).getCantoresFragment();
        return new CantorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CantorViewHolder holder, int position) {
        Cantor cantor = cantores.get(position);
        final int posicao = position;

        holder.quantidade.setText(String.valueOf(cantor.getCodigosMusicas().size()));
        holder.nome.setText(cantor.getNome());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.itemClickListener(posicao);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cantores.size();
    }

    static class CantorViewHolder extends RecyclerView.ViewHolder{
        TextView quantidade;
        TextView nome;

        CantorViewHolder(View itemView) {
            super(itemView);
            quantidade = (TextView) itemView.findViewById(R.id.tv_lista_cantores_quantidade);
            nome = (TextView) itemView.findViewById(R.id.tv_lista_cantores_nome);
        }
    }
}
