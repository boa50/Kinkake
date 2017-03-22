package br.com.boa50.kinkake.adapter;

import android.graphics.drawable.TransitionDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.MainActivity;
import br.com.boa50.kinkake.activity.MusicasCantorActivity;
import br.com.boa50.kinkake.fragment.MusicasFragment;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.util.MusicaUtil;

public class MusicaAdapter extends RecyclerView.Adapter<MusicaAdapter.MusicaViewHolder> {
    private ArrayList<Musica> musicas;
    private MusicasFragment fragment;

    public MusicaAdapter(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public MusicaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_musicas, parent, false);
        try {
            fragment = ((MainActivity) parent.getContext()).getMusicasFragment();
        }catch (Exception e){
            fragment = ((MusicasCantorActivity) parent.getContext()).getMusicasFragment();
        }

        return new MusicaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicaViewHolder holder, int position) {
        Musica musica = musicas.get(position);
        final int posicao = position;

        holder.codigo.setText(MusicaUtil.transformaCodigoString(musica.getCodigo()));
        holder.nome.setText(musica.getNome());
        if(musica.isFavorito())
            holder.td.startTransition(0);
        else
            holder.td.resetTransition();

        holder.favorito.setOnClickListener(MusicaUtil.favoritoClickListener(musica, holder.td));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.itemClickListener(posicao);
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicas.size();
    }

    static class MusicaViewHolder extends RecyclerView.ViewHolder{
        TextView codigo;
        TextView nome;
        ImageButton favorito;
        TransitionDrawable td;

        MusicaViewHolder(View itemView) {
            super(itemView);
            codigo = (TextView) itemView.findViewById(R.id.tv_lista_musica_codigo);
            nome = (TextView) itemView.findViewById(R.id.tv_lista_musica_nome);
            favorito = (ImageButton) itemView.findViewById(R.id.ib_lista_musica_favorto);
            td = (TransitionDrawable) favorito.getDrawable();
            favorito.setImageDrawable(td);
        }
    }
}
