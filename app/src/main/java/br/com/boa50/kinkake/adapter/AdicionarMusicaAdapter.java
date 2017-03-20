package br.com.boa50.kinkake.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.model.MusicaSelecao;

public class AdicionarMusicaAdapter extends RecyclerView.Adapter<AdicionarMusicaAdapter.AdicionarMusicaViewHolder> /*ArrayAdapter<MusicaSelecao>*/{

    private ArrayList<MusicaSelecao> musicasSelecao;

    public AdicionarMusicaAdapter(ArrayList<MusicaSelecao> musicasSelecao) {
        this.musicasSelecao = musicasSelecao;
    }

    @Override
    public AdicionarMusicaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_musicas_adicionar, parent, false);
        return new AdicionarMusicaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdicionarMusicaViewHolder holder, int position) {
        MusicaSelecao musicaSelecao = musicasSelecao.get(position);
        holder.checkBox.setChecked(musicaSelecao.isSelecao());
        holder.textView.setText(musicaSelecao.getMusica().getNome());
    }

    @Override
    public int getItemCount() {
        return musicasSelecao.size();
    }

//    private Context context;
//    private ArrayList<MusicaSelecao> musicasSelecao;
//
//    public AdicionarMusicaAdapter(@NonNull Context context, @NonNull ArrayList<MusicaSelecao> objects) {
//        super(context, 0, objects);
//        this.context = context;
//        this.musicasSelecao = objects;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.lista_musicas_adicionar, parent, false);
//
//        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_musica_adicionar);
//        TextView textView = (TextView) view.findViewById(R.id.tv_lista_musica_adicionar_nome);
//
//        textView.setText(musicasSelecao.get(position).getMusica().getNome());
//        checkBox.setChecked(musicasSelecao.get(position).isSelecao());
//
//        return view;
//    }

    protected static class AdicionarMusicaViewHolder extends RecyclerView.ViewHolder{

        protected CheckBox checkBox;
        protected TextView textView;

        public AdicionarMusicaViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.cb_musica_adicionar);
            textView = (TextView) itemView.findViewById(R.id.tv_lista_musica_adicionar_nome);
        }
    }
}
