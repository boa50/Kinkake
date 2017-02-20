package br.com.boa50.kinkake.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.boa50.kinkake.model.Cantor;

public class CantorUtil {

    public static ArrayList<Cantor> ordenaCantoresPorNome(ArrayList<Cantor> cantores){
        Collections.sort(cantores, new Comparator<Cantor>() {
            @Override
            public int compare(Cantor c0, Cantor c1) {
                return c0.getNome().compareToIgnoreCase(c1.getNome());
            }
        });

        return cantores;
    }

}
