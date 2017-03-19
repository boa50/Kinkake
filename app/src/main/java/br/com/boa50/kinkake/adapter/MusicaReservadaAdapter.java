package br.com.boa50.kinkake.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.util.MusicaUtil;

public class MusicaReservadaAdapter extends ArrayAdapter<Musica>{

    private Context context;
    private ArrayList<Musica> musicas;

    public MusicaReservadaAdapter(@NonNull Context context, @NonNull ArrayList<Musica> objects) {
        super(context, 0, objects);
        this.context = context;
        this.musicas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.lista_musicas_reservadas, parent, false);

        TextView codigo = (TextView) view.findViewById(R.id.tv_lista_musica_reservada_codigo);
        TextView nome = (TextView) view.findViewById(R.id.tv_lista_musica_reservada_nome);

        Musica musica = musicas.get(position);

        codigo.setText(MusicaUtil.transformaCodigoString(musica.getCodigo()));
        nome.setText(musica.getNome());

        return view;
    }
}
