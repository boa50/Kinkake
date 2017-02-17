package br.com.boa50.kinkake.model;

import java.util.ArrayList;

public class Cantor {
    private Long id;
    private String nome;
    private ArrayList<Long> codigosMusicas;

    //TODO para remover
    public static ArrayList<Cantor> getCantoresListaTeste(){
        ArrayList<Cantor> cantores = new ArrayList<>();

        for(int i = 1 ; i <= 2 ; i++){
            Cantor cantor = new Cantor();
            cantor.id = Long.parseLong(String.valueOf(i));
            cantor.nome = "Cantor " + i;
            cantor.codigosMusicas = new ArrayList<>();

            for(int j = 1 ; j <= 8 ; j ++){
                if(i == 1 && j <= 5)
                    cantor.codigosMusicas.add(Long.parseLong(j + "000"));
                else if(i == 2 && j > 5)
                    cantor.codigosMusicas.add(Long.parseLong(j + "000"));
            }
            cantores.add(cantor);
        }

        return cantores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Long> getCodigosMusicas() {
        return codigosMusicas;
    }

    public void setCodigosMusicas(ArrayList<Long> codigosMusicas) {
        this.codigosMusicas = codigosMusicas;
    }
}
