package br.com.boa50.kinkake.model;

import java.util.ArrayList;

public class Pessoa {
    private String nome;
    private ArrayList<String> idMusicas;

    public Pessoa(String nome){
        this.nome = nome;
        this.idMusicas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<String> getIdMusicas() {
        return idMusicas;
    }

    public void setIdMusicas(ArrayList<String> idMusicas) {
        this.idMusicas = idMusicas;
    }
}
