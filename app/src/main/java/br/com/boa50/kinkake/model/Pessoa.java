package br.com.boa50.kinkake.model;

import java.util.ArrayList;

public class Pessoa {
    private String nome;
    private ArrayList<Integer> codigosMusicas;

    public Pessoa(){}

    public Pessoa(String nome){
        this.nome = nome;
        this.codigosMusicas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Integer> getCodigosMusicas() {
        if(codigosMusicas == null)
            codigosMusicas = new ArrayList<>();

        return codigosMusicas;
    }
}
