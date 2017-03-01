package br.com.boa50.kinkake.model;

import java.util.ArrayList;

public class Cantor {
    private Integer id;
    private String nome;
    private ArrayList<Integer> codigosMusicas;

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
