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

    public static ArrayList<Cantor> filtrar(String texto){
        ArrayList<Cantor> retorno = new ArrayList<>();

        if(texto.isEmpty())
            return todosCantores;

        for(Cantor cantor : todosCantores){
            if(cantor.getNome().toLowerCase().contains(texto.toLowerCase())){
                retorno.add(cantor);
            }
        }

        return retorno;
    }

    public static ArrayList<Cantor> getTodosCantores(){
        if(todosCantores == null){
            todosCantores = Cantor.getCantoresListaTeste();
        }

        return todosCantores;
    }

}
