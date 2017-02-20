package br.com.boa50.kinkake.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.util.MusicaUtil;

public class MusicaAdapter extends ArrayAdapter<Musica> {

    private Context context;
    private ArrayList<Musica> musicas;

    public MusicaAdapter(Context context, ArrayList<Musica> objects) {
        super(context, 0, objects);
        this.context = context;
        this.musicas = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.lista_musicas, parent, false);

        TextView codigo = (TextView) view.findViewById(R.id.tv_lista_musica_codigo);
        TextView nome = (TextView) view.findViewById(R.id.tv_lista_musica_nome);
        ImageButton favorito = (ImageButton) view.findViewById(R.id.ib_lista_musica_favorto);

        Musica musica = musicas.get(position);

        codigo.setText(MusicaUtil.transformaCodigoString(musica.getCodigo()));
        nome.setText(musica.getNome());
        MusicaUtil.mudaIconeFavorito(favorito, musica.isFavorito());

        favorito.setOnClickListener(MusicaUtil.favoritoClickListener(musica, favorito));

        return view;
    }
}
