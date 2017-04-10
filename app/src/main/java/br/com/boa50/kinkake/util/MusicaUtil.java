package br.com.boa50.kinkake.util;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.application.ConfiguracaoFirebase;
import br.com.boa50.kinkake.application.MusicaListeners;
import br.com.boa50.kinkake.model.Cantor;
import br.com.boa50.kinkake.model.Musica;

public class MusicaUtil {

    private static ArrayList<Musica> todasMusicas;
    private static ArrayList<Integer> codigosTodasMusicas;

    public static View.OnClickListener favoritoClickListener(final Musica musica, final ImageButton favorito){

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable imagens[] = new Drawable[2];
                imagens[0] = ContextCompat.getDrawable(favorito.getContext(), R.drawable.ic_heart_outline);
                imagens[1] = ContextCompat.getDrawable(favorito.getContext(), R.drawable.ic_heart);
                TransitionDrawable td = new TransitionDrawable(imagens);
                favorito.setImageDrawable(td);

                musica.setFavorito(!musica.isFavorito());
                mudaIconeFavorito(td, musica.isFavorito());

                DatabaseReference databaseReference = ConfiguracaoFirebase.getReferenciaMusica();
                databaseReference.orderByChild("codigo").equalTo(musica.getCodigo())
                        .addListenerForSingleValueEvent(MusicaListeners.getListenerMusicaFavorito());
            }
        };
    }

    private static void mudaIconeFavorito(TransitionDrawable td, boolean favorito){
        int tempoTransition = 500;

        if(favorito){
            td.startTransition(tempoTransition);
        }else{
            td.startTransition(0);
            td.reverseTransition(tempoTransition);
        }
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

    public static ArrayList<Musica> getTodasMusicas(){
        return todasMusicas;
    }

    public static void preencheTodasMusicas(ArrayList<Musica> musicas){
        todasMusicas = new ArrayList<>();
        todasMusicas.addAll(musicas);

        codigosTodasMusicas = new ArrayList<>();
        for (Musica musica : todasMusicas){
            codigosTodasMusicas.add(musica.getCodigo());
        }
    }

    public static void ordenarCodigosMusicasPorNome(ArrayList<Integer> codigosMusicas){
        if (!codigosMusicas.isEmpty()){
            ArrayList<Integer> codigosMusicasOrdenadas = new ArrayList<>();

            for (Integer i : codigosTodasMusicas){
                int indice = codigosMusicas.indexOf(i);
                if (indice >= 0)
                    codigosMusicasOrdenadas.add(i);
            }

            codigosMusicas.clear();
            codigosMusicas.addAll(codigosMusicasOrdenadas);
        }
    }

    public static ArrayList<Musica> filtrar(String texto, boolean apenasFavoritas){
        return filtrar(todasMusicas, texto, apenasFavoritas);
    }

    public static ArrayList<Musica> filtrar(ArrayList<Musica> musicas, String texto, boolean apenasFavoritas){
        ArrayList<Musica> retorno = new ArrayList<>();

        if(!texto.isEmpty())
            texto = StringUtil.removerAcentos(texto).toUpperCase();

        if(texto.isEmpty() && !apenasFavoritas){
            return musicas;
        }else if(texto.isEmpty() && apenasFavoritas){
            for(Musica musica : musicas){
                if(musica.isFavorito())
                    retorno.add(musica);
            }
        }else if(!texto.isEmpty() && !apenasFavoritas){
            for(Musica musica : musicas){
                if(StringUtil.removerAcentos(musica.getNome()).contains(texto))
                    retorno.add(musica);
            }
        }else{
            for(Musica musica : musicas){
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

    static Musica getMusicaPorCodigo(Integer codigo){
        for(Musica musica : todasMusicas){
            if(codigo.intValue() == musica.getCodigo().intValue())
                return musica;
        }

        return null;
    }

    public static ArrayList<Musica> getMusicasPorCodigos(ArrayList<Integer> codigosMusicas){
        ArrayList<Musica> musicas = new ArrayList<>();

        for(Musica musica : todasMusicas){
            for(Integer codigo : codigosMusicas){
                if(codigo.intValue() == musica.getCodigo().intValue())
                    musicas.add(musica);
            }
        }

        return musicas;
    }

    public static ArrayList<Musica> getMusicasDiferentesPorCodigos(ArrayList<Integer> codigosMusicas){
        ArrayList<Musica> musicas = new ArrayList<>();
        boolean adicionaMusica;

        if(codigosMusicas.isEmpty())
            musicas.addAll(todasMusicas);
        else{
            for(Musica musica : todasMusicas){
                adicionaMusica = true;
                for(Integer codigo : codigosMusicas){
                    if(codigo.intValue() == musica.getCodigo().intValue()){
                        adicionaMusica = false;
                        break;
                    }
                }
                if(adicionaMusica)
                    musicas.add(musica);
            }
        }

        return musicas;
    }

    public static Musica getMusicaPorKey(String key){
        return todasMusicas.get(Integer.valueOf(key) - 1);
    }
}
