package br.com.boa50.kinkake.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.boa50.kinkake.model.Cantor;

public class CantorUtil {

    private static ArrayList<Cantor> todosCantores;

    private static ArrayList<Cantor> ordenaCantoresPorNome(ArrayList<Cantor> cantores){
        Collections.sort(cantores, new Comparator<Cantor>() {
            @Override
            public int compare(Cantor c0, Cantor c1) {
                return StringUtil.removerAcentos(c0.getNome())
                        .compareToIgnoreCase(StringUtil.removerAcentos(c1.getNome()));
            }
        });

        return cantores;
    }

    public static ArrayList<Cantor> filtrar(String texto, boolean apenasFavoritas){
        ArrayList<Cantor> retorno = new ArrayList<>();

        if(!texto.isEmpty())
            texto = StringUtil.removerAcentos(texto).toLowerCase();

        if(texto.isEmpty() && !apenasFavoritas){
            return todosCantores;
        }else if(texto.isEmpty() && apenasFavoritas){
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
        }else if(!texto.isEmpty() && !apenasFavoritas){
            for(Cantor cantor : todosCantores){
                if(StringUtil.removerAcentos(cantor.getNome()).toLowerCase().contains(texto)){
                    retorno.add(cantor);
                }
            }
        }else{
            for(Cantor cantor : todosCantores){
                if(StringUtil.removerAcentos(cantor.getNome()).toLowerCase().contains(texto)){
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
        return todosCantores;
    }

    public static void preencheTodosCantores(ArrayList<Cantor> cantores){
        todosCantores = new ArrayList<>();
        todosCantores.addAll(ordenaCantoresPorNome(cantores));
    }

    public static Cantor getCantorPorId(Integer id){
        for(Cantor cantor : todosCantores){
            if(id.intValue() == cantor.getId().intValue())
                return cantor;
        }

        return null;
    }

}
