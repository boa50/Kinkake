package br.com.boa50.kinkake.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.boa50.kinkake.adapter.PessoaAdapter;
import br.com.boa50.kinkake.application.PessoaFirebase;
import br.com.boa50.kinkake.model.Pessoa;

public class PessoaUtil {
    public static void preencherTodasPessoas(ArrayList<Pessoa> pessoas){
        VariaveisEstaticas.setTodasPessoas(new ArrayList<Pessoa>());
        ordenarPessoasPorNome(pessoas);
        VariaveisEstaticas.getTodasPessoas().addAll(pessoas);

        VariaveisEstaticas.setNomesAdicionados(new ArrayList<String>());
        for(Pessoa pessoa : VariaveisEstaticas.getTodasPessoas()){
            VariaveisEstaticas.getNomesAdicionados().add(pessoa.getNome());
        }

        PessoaFirebase.adicionarListenerPessoas(new PessoaAdapter(VariaveisEstaticas.getTodasPessoas()));
    }

    public static void ordenarPessoasPorNome(ArrayList<Pessoa> pessoas){
        Collections.sort(pessoas, new Comparator<Pessoa>() {
            @Override
            public int compare(Pessoa p0, Pessoa p1) {
                return StringUtil.removerAcentos(p0.getNome())
                        .compareToIgnoreCase(StringUtil.removerAcentos(p1.getNome()));
            }
        });
    }

    public static boolean isNomePessoaExistente(String nome){
        return VariaveisEstaticas.getNomesAdicionados().contains(nome);

    }

    public static ArrayList<Pessoa> getPessoasSemMusicaPorCodigo(Integer codigoMusica){
        ArrayList<Pessoa> retorno = new ArrayList<>();

        for (Pessoa pessoa : VariaveisEstaticas.getTodasPessoas()){
            if (!pessoa.getCodigosMusicas().contains(codigoMusica))
                retorno.add(pessoa);
        }

        return retorno;
    }
}
