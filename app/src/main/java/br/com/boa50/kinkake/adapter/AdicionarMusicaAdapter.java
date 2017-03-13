package br.com.boa50.kinkake.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.model.MusicaSelecao;

public class AdicionarMusicaAdapter extends ArrayAdapter<MusicaSelecao>{

    private Context context;
    private ArrayList<MusicaSelecao> musicasSelecao;

    public AdicionarMusicaAdapter(@NonNull Context context, @NonNull ArrayList<MusicaSelecao> objects) {
        super(context, 0, objects);
        this.context = context;
        this.musicasSelecao = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.lista_musicas_adicionar, parent, false);

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_musica_adicionar);
        TextView textView = (TextView) view.findViewById(R.id.tv_lista_musica_adicionar_nome);

        textView.setText(musicasSelecao.get(position).getMusica().getNome());
        checkBox.setChecked(musicasSelecao.get(position).isSelecao());

        return view;
    }
}
