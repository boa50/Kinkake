package br.com.boa50.kinkake.util;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

import br.com.boa50.kinkake.model.Pessoa;

public class PessoaUtil {
    private static ArrayList<Pessoa> todasPessoas;
    private static Pessoa pessoaAtiva;

    private static ArrayAdapter adapterMusicasPessoa;
    private static ArrayAdapter adapterPessoa;

    public static ArrayList<Pessoa> getTodasPessoas(){
        if(todasPessoas == null)
            todasPessoas = new ArrayList<>();

        return todasPessoas;
    }

    public static void adicionaPessoa(Pessoa pessoa){
        if(todasPessoas != null)
            todasPessoas.add(pessoa);
    }

    public static Pessoa getPessoaPorNome(String nome){
        for(Pessoa pessoa : todasPessoas){
            if(pessoa.getNome().equalsIgnoreCase(nome))
                return pessoa;
        }

        return null;
    }

    public static boolean isNomePessoaExistente(String nome){
        for(Pessoa pessoa : todasPessoas){
            if(nome.equalsIgnoreCase(pessoa.getNome()))
                return true;
        }

        return false;
    }

    public static void setPessoaAtiva(Pessoa pessoa){
        pessoaAtiva = pessoa;
    }

    public static Pessoa getPessoaAtiva(){
        return pessoaAtiva;
    }

    public static ArrayAdapter getAdapterMusicasPessoa() {
        return adapterMusicasPessoa;
    }

    public static void setAdapterMusicasPessoa(ArrayAdapter adapterMusicasPessoa) {
        PessoaUtil.adapterMusicasPessoa = adapterMusicasPessoa;
    }

    public static ArrayAdapter getAdapterPessoa() {
        return adapterPessoa;
    }

    public static void setAdapterPessoa(ArrayAdapter adapterPessoa) {
        PessoaUtil.adapterPessoa = adapterPessoa;
    }
}
