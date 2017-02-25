package br.com.boa50.kinkake.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.boa50.kinkake.model.Cantor;

public class CantorUtil {

    private static ArrayList<Cantor> todosCantores;

    public static ArrayList<Cantor> ordenaCantoresPorNome(ArrayList<Cantor> cantores){
        Collections.sort(cantores, new Comparator<Cantor>() {
            @Override
            public int compare(Cantor c0, Cantor c1) {
                return c0.getNome().compareToIgnoreCase(c1.getNome());
            }
        });

        return cantores;
    }

    public static ArrayList<Cantor> filtrar(String texto, boolean apenasFavoritas){
        ArrayList<Cantor> retorno = new ArrayList<>();

        if(texto.isEmpty() && apenasFavoritas == false){
            return todosCantores;
        }else if(texto.isEmpty() && apenasFavoritas == true){
            for(Cantor cantor : todosCantores){
                ArrayList<Integer> codigosMusicas = getCodigosMusicasFavoritas(cantor);

                if(codigosMusicas != null){
                    Cantor cantorTemp = new Cantor(
                            cantor.getId(),
                            cantor.getNome(),
                            codigosMusicas
                    );
                    retorno.add(cantorTemp);
                }
            }
        }else if(!texto.isEmpty() && apenasFavoritas == false){
            for(Cantor cantor : todosCantores){
                if(cantor.getNome().toLowerCase().contains(texto.toLowerCase())){
                    retorno.add(cantor);
                }
            }
        }else{
            for(Cantor cantor : todosCantores){
                if(cantor.getNome().toLowerCase().contains(texto.toLowerCase())){
                    ArrayList<Integer> codigosMusicas = getCodigosMusicasFavoritas(cantor);

                    if(codigosMusicas != null){
                        Cantor cantorTemp = new Cantor(
                                cantor.getId(),
                                cantor.getNome(),
                                codigosMusicas
                        );
                        retorno.add(cantorTemp);
                    }
                }
            }
        }

        return retorno;
    }

    private static ArrayList<Integer> getCodigosMusicasFavoritas(Cantor cantor){
        ArrayList<Integer> retorno = new ArrayList<>();

        for(Integer codigo : cantor.getCodigosMusicas()){
            if(MusicaUtil.getMusicaPorCodigo(codigo).isFavorito())
                retorno.add(codigo);
        }

        if(!retorno.isEmpty())
            return retorno;
        else
            return null;
    }

    public static ArrayList<Cantor> getTodosCantores(){
        if(todosCantores == null){
            todosCantores = Cantor.getCantoresListaTeste();
        }

        return todosCantores;
    }

}