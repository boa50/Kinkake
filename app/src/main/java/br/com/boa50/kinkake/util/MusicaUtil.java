package br.com.boa50.kinkake.util;

import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.model.Cantor;
import br.com.boa50.kinkake.model.Musica;

public class MusicaUtil {

    private static ArrayList<Musica> todasMusicas;

    public static View.OnClickListener favoritoClickListener(Musica musica, ImageButton ibFavorito){

        final Musica musicaTemp = musica;
        final ImageButton favoritoTemp = ibFavorito;

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicaTemp.setFavorito(!musicaTemp.isFavorito());
                mudaIconeFavorito(favoritoTemp, musicaTemp.isFavorito());
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

    public static void preencheTodasMusicas(ArrayList<Musica> musicas){
        todasMusicas = new ArrayList<>();
        todasMusicas.addAll(musicas);
    }

    public static ArrayList<Musica> filtrar(String texto, boolean apenasFavoritas){
        ArrayList<Musica> retorno = new ArrayList<>();

        if(texto.isEmpty() && !apenasFavoritas){
            return todasMusicas;
        }else if(texto.isEmpty() && apenasFavoritas){
            for(Musica musica : todasMusicas){
                if(musica.isFavorito())
                    retorno.add(musica);
            }
        }else if(!texto.isEmpty() && !apenasFavoritas){
            for(Musica musica : todasMusicas){
                if(musica.getNome().toLowerCase().contains(texto.toLowerCase()))
                    retorno.add(musica);
            }
        }else{
            for(Musica musica : todasMusicas){
                if(musica.getNome().toLowerCase().contains(texto.toLowerCase()))
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
