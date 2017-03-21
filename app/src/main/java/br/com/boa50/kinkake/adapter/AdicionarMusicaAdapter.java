package br.com.boa50.kinkake.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.MusicasAdicionarActivity;
import br.com.boa50.kinkake.fragment.AdicionaMusicaPessoaFragment;
import br.com.boa50.kinkake.model.MusicaSelecao;

public class AdicionarMusicaAdapter extends RecyclerView.Adapter<AdicionarMusicaAdapter.AdicionarMusicaViewHolder>{

    private ArrayList<MusicaSelecao> musicasSelecao;
    private AdicionaMusicaPessoaFragment fragment;

    public AdicionarMusicaAdapter(ArrayList<MusicaSelecao> musicasSelecao) {
        this.musicasSelecao = musicasSelecao;
    }

    @Override
    public AdicionarMusicaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_musicas_adicionar, parent, false);
        fragment = ((MusicasAdicionarActivity) parent.getContext()).getAdicionaMusicaPessoaFragment();
        return new AdicionarMusicaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdicionarMusicaViewHolder holder, int position) {
        final MusicaSelecao musicaSelecao = musicasSelecao.get(position);
        holder.checkBox.setChecked(musicaSelecao.isSelecao());
        holder.textView.setText(musicaSelecao.getMusica().getNome());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicaSelecao.setSelecao(!musicaSelecao.isSelecao());
                fragment.atualizarMusicasAdicionar(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicasSelecao.size();
    }


    static class AdicionarMusicaViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        TextView textView;

        AdicionarMusicaViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.cb_musica_adicionar);
            textView = (TextView) itemView.findViewById(R.id.tv_lista_musica_adicionar_nome);
        }
    }
}
