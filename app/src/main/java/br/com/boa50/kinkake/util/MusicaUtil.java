package br.com.boa50.kinkake.util;

import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.application.ConfiguracaoFirebase;
import br.com.boa50.kinkake.application.MusicaListeners;
import br.com.boa50.kinkake.model.Cantor;
import br.com.boa50.kinkake.model.Musica;

public class MusicaUtil {

    private static ArrayList<Musica> todasMusicas;

    public static View.OnClickListener favoritoClickListener(final Musica musica, final ImageButton ibFavorito){

        final DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference();

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musica.setFavorito(!musica.isFavorito());
                databaseReference.orderByChild("codigo").equalTo(musica.getCodigo())
                        .addChildEventListener(MusicaListeners.getListenerMusicaFavorito(musica.isFavorito()));
                mudaIconeFavorito(ibFavorito, musica.isFavorito());
            }
        };

    }

    public static void mudaIconeFavorito(ImageButton ibFavorito, boolean favorito){
        if(favorito)
            ibFavorito.setBackgroundResource(R.drawable.ic_favorite);
        else
            ibFavorito.setBackgroundResource(R.drawable.ic_favorite_border);
    }

    public static String transformaCodigoString(Integer codigo){
        String retorno = codigo.toString();
        int retornoTamanho = retorno.length();
        String codigoPadrao = "00000";

        retorno = codigoPadrao.substring(0, 5-retornoTamanho) + retorno;

        return retorno;
    }

    public static ArrayList<Musica> listaMusicasPorCantor(Cantor cantor){
        ArrayList<Musica> musicasCantor = new ArrayList<>();

        for(Integer idMusica : cantor.getCodigosMusicas()){
            for(Musica musica : todasMusicas){
                if(idMusica.equals(musica.getCodigo()))
                    musicasCantor.add(musica);
            }
        }

        return musicasCantor;
    }

    public static ArrayList<Musica> ordenaMusicasPorNome(ArrayList<Musica> musicas){
        Collections.sort(musicas, new Comparator<Musica>() {
            @Override
            public int compare(Musica m0, Musica m1) {
                return m0.getNome().compareToIgnoreCase(m1.getNome());
            }
        });

        return musicas;
    }

    public static ArrayList<Musica> getTodasMusicas(){
        return todasMusicas;
    }

    public static void preencheTodasMusicas(ArrayList<Musica> musicas){
        todasMusicas = new ArrayList<>();
        todasMusicas.addAll(musicas);
    }

    public static ArrayList<Musica> filtrar(String texto, boolean apenasFavoritas){
        ArrayList<Musica> retorno = new ArrayList<>();

        if(!texto.isEmpty())
            texto = StringUtil.removerAcentos(texto).toUpperCase();

        if(texto.isEmpty() && !apenasFavoritas){
            return todasMusicas;
        }else if(texto.isEmpty() && apenasFavoritas){
            for(Musica musica : todasMusicas){
                if(musica.isFavorito())
                    retorno.add(musica);
            }
        }else if(!texto.isEmpty() && !apenasFavoritas){
            for(Musica musica : todasMusicas){
                if(StringUtil.removerAcentos(musica.getNome()).contains(texto))
                    retorno.add(musica);
            }
        }else{
            for(Musica musica : todasMusicas){
                if(StringUtil.removerAcentos(musica.getNome()).contains(texto))
                    if(musica.isFavorito())
                        retorno.add(musica);
            }
        }

        return retorno;
    }

    public static ArrayList<Musica> filtrar(Integer idCantor, boolean apenasFavoritas){
        ArrayList<Musica> retorno = new ArrayList<>();
        Cantor cantor = CantorUtil.getCantorPorId(idCantor);

        if(cantor != null){
            if(apenasFavoritas){
                for(Musica musica : listaMusicasPorCantor(cantor)){
                    if(musica.isFavorito())
                        retorno.add(musica);
                }
            }else{
                retorno.addAll(listaMusicasPorCantor(cantor));
            }
        }

        return retorno;
    }

    public static Musica getMusicaPorCodigo(Integer codigo){
        for(Musica musica : todasMusicas){
            if(codigo.intValue() == musica.getCodigo().intValue())
                return musica;
        }

        return null;
    }
}
