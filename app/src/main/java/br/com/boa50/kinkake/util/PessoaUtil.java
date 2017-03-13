package br.com.boa50.kinkake.util;

import java.util.ArrayList;

import br.com.boa50.kinkake.model.Pessoa;

public class PessoaUtil {
    private static ArrayList<Pessoa> todasPessoas;

    public static ArrayList<Pessoa> getTodasPessoas(){
        if(todasPessoas == null)
            todasPessoas = new ArrayList<>();

        return todasPessoas;
    }

    public static void adicionaPessoa(Pessoa pessoa){
        if(todasPessoas != null)
            todasPessoas.add(pessoa);
    }
}
