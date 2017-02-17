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
        final ImageButton favorito = (ImageButton) view.findViewById(R.id.ib_lista_musica_favorto);

        final Musica musica = musicas.get(position);

        codigo.setText(musica.getCodigo().toString());
        nome.setText(musica.getNome());
        mudaIconeFavorito(favorito, musica.isFavorito());

        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musica.setFavorito(!musica.isFavorito());
                mudaIconeFavorito(favorito, musica.isFavorito());
            }
        });

        return view;
    }

    private void mudaIconeFavorito(ImageButton ibFavorito, boolean favorito){
        if(favorito)
            ibFavorito.setBackground(context.getDrawable(R.drawable.ic_favorite));
        else
            ibFavorito.setBackground(context.getDrawable(R.drawable.ic_favorite_border));
    }
}
