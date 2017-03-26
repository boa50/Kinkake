package br.com.boa50.kinkake.util;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import br.com.boa50.kinkake.model.Pessoa;

public class VariaveisEstaticas{
    //Pessoas
    private static ArrayList<Pessoa> todasPessoas;
    private static ArrayList<String> nomesAdicionados;
    private static Pessoa pessoaAtiva;
    private static RecyclerView.Adapter adapterMusicasPessoa;



    public static ArrayList<Pessoa> getTodasPessoas(){
        return todasPessoas;
    }

    public static void setTodasPessoas(ArrayList<Pessoa> pessoas){
        todasPessoas = pessoas;
    }

    public static ArrayList<String> getNomesAdicionados() {
        return nomesAdicionados;
    }

    public static void setNomesAdicionados(ArrayList<String> nomesAdicionados) {
        VariaveisEstaticas.nomesAdicionados = nomesAdicionados;
    }

    public static Pessoa getPessoaAtiva() {
        return pessoaAtiva;
    }

    public static void setPessoaAtiva(Pessoa pessoaAtiva) {
        VariaveisEstaticas.pessoaAtiva = pessoaAtiva;
    }

    public static RecyclerView.Adapter getAdapterMusicasPessoa() {
        return adapterMusicasPessoa;
    }

    public static void setAdapterMusicasPessoa(RecyclerView.Adapter adapterMusicasPessoa) {
        VariaveisEstaticas.adapterMusicasPessoa = adapterMusicasPessoa;
    }
}
