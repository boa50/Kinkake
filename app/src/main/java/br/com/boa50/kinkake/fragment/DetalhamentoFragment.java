package br.com.boa50.kinkake.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.util.MusicaUtil;

public class DetalhamentoFragment extends Fragment {

    private Musica musica;

    public DetalhamentoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalhamento, container, false);

        TextView codigo = (TextView) view.findViewById(R.id.tv_detalhamento_musica_codigo);
        TextView letra = (TextView) view.findViewById(R.id.tv_detalhamento_musica_letra);
        ImageButton favorito = (ImageButton) view.findViewById(R.id.ib_detalhamento_musica_favorto);

        codigo.setText(MusicaUtil.transformaCodigoString(musica.getCodigo()));
        letra.setText(musica.getLetra());

        if(musica.isFavorito())
            favorito.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_heart));

        favorito.setOnClickListener(MusicaUtil.favoritoClickListener(musica, favorito));

        return view;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }
}
