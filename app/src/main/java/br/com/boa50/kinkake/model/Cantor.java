package br.com.boa50.kinkake.model;

import java.util.ArrayList;

import br.com.boa50.kinkake.util.CantorUtil;

public class Cantor {
    private Integer id;
    private String nome;
    private ArrayList<Integer> codigosMusicas;

    //TODO para remover
    public static ArrayList<Cantor> getCantoresListaTeste(){
        ArrayList<Cantor> cantores = new ArrayList<>();

        Cantor cant = new Cantor();
        cant.id = 123;
        cant.nome = "zCantor";
        cant.codigosMusicas = new ArrayList<>();
        cant.codigosMusicas.add(10);
        cant.codigosMusicas.add(11);
        cantores.add(cant);

        for(int i = 1 ; i <= 2 ; i++){
            Cantor cantor = new Cantor();
            cantor.id = i;
            cantor.nome = "Cantor " + i;
            cantor.codigosMusicas = new ArrayList<>();

            for(int j = 1 ; j <= 8 ; j ++){
                if(i == 1 && j <= 5)
                    cantor.codigosMusicas.add(j);
                else if(i == 2 && j > 5)
                    cantor.codigosMusicas.add(j);
            }
            cantores.add(cantor);
        }

        return CantorUtil.ordenaCantoresPorNome(cantores);
    }

    public Cantor(){}

    public Cantor(Integer id, String nome, ArrayList<Integer> codigosMusicas){
        this.id = id;
        this.nome = nome;
        this.codigosMusicas = codigosMusicas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Integer> getCodigosMusicas() {
        return codigosMusicas;
    }

    public void setCodigosMusicas(ArrayList<Integer> codigosMusicas) {
        this.codigosMusicas = codigosMusicas;
    }
}
