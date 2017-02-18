package br.com.boa50.kinkake.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.model.Musica;

public class DetalhamentoFragment extends Fragment {

    private TextView codigo;
    private TextView letra;
    private ImageButton favorito;
    private Musica musica;

    public DetalhamentoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalhamento, container, false);

        codigo = (TextView) view.findViewById(R.id.tv_detalhamento_musica_codigo);
        letra = (TextView) view.findViewById(R.id.tv_detalhamento_musica_letra);
        favorito = (ImageButton) view.findViewById(R.id.ib_detalhamento_musica_favorto);


        if(musica.getCodigo() != null)
            codigo.setText(musica.getCodigo().toString());
        else
            Log.d("QWERTY", "Código: " + musica.getCodigo());
        letra.setText(musica.getLetra());
        mudaIconeFavorito(favorito, musica.isFavorito());

        return view;
    }

    private void mudaIconeFavorito(ImageButton ibFavorito, boolean favorito){
        if(favorito)
            ibFavorito.setBackgroundResource(R.drawable.ic_favorite);
        else
            ibFavorito.setBackgroundResource(R.drawable.ic_favorite_border);
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }
}
