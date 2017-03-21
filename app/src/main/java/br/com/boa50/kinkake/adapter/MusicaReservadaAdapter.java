package br.com.boa50.kinkake.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.MusicasReservadasActivity;
import br.com.boa50.kinkake.fragment.MusicasPorPessoaFragment;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.util.MusicaUtil;

public class MusicaReservadaAdapter extends RecyclerView.Adapter<MusicaReservadaAdapter.MusicaReservadaViewHolder>{
    private ArrayList<Musica> musicas;
    private MusicasPorPessoaFragment fragment;

    public MusicaReservadaAdapter(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public MusicaReservadaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_musicas_reservadas, parent, false);
        fragment = ((MusicasReservadasActivity) parent.getContext()).getMusicasPorPessoaFragment();
        return new MusicaReservadaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicaReservadaViewHolder holder, int position) {
        Musica musica = musicas.get(position);
        final int posicao = position;

        holder.codigo.setText(MusicaUtil.transformaCodigoString(musica.getCodigo()));
        holder.nome.setText(musica.getNome());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.clickListener(v, posicao);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                fragment.longClickListener(v, posicao);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicas.size();
    }

//    private Context context;
//    private ArrayList<Musica> musicas;
//
//    public MusicaReservadaAdapter(@NonNull Context context, @NonNull ArrayList<Musica> objects) {
//        super(context, 0, objects);
//        this.context = context;
//        this.musicas = objects;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View view = inflater.inflate(R.layout.lista_musicas_reservadas, parent, false);
//
//        TextView codigo = (TextView) view.findViewById(R.id.tv_lista_musica_reservada_codigo);
//        TextView nome = (TextView) view.findViewById(R.id.tv_lista_musica_reservada_nome);
//
//        Musica musica = musicas.get(position);
//
//        codigo.setText(MusicaUtil.transformaCodigoString(musica.getCodigo()));
//        nome.setText(musica.getNome());
//
//        return view;
//    }

    static class MusicaReservadaViewHolder extends RecyclerView.ViewHolder{
        TextView codigo;
        TextView nome;

        public MusicaReservadaViewHolder(View itemView) {
            super(itemView);
            codigo = (TextView) itemView.findViewById(R.id.tv_lista_musica_reservada_codigo);
            nome = (TextView) itemView.findViewById(R.id.tv_lista_musica_reservada_nome);
        }
    }
}
