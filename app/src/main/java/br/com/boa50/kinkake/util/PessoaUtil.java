package br.com.boa50.kinkake.util;

import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.boa50.kinkake.application.ConfiguracaoFirebase;
import br.com.boa50.kinkake.application.PessoaListeners;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.model.Pessoa;

public class PessoaUtil {
    private static ArrayList<Pessoa> todasPessoas;
    private static Pessoa pessoaAtiva;

    private static RecyclerView.Adapter adapterMusicasPessoa;
    private static ArrayAdapter adapterPessoa;

    public static ArrayList<Pessoa> getTodasPessoas(){
        return todasPessoas;
    }

    public static void preencherTodasPessoas(ArrayList<Pessoa> pessoas){
        todasPessoas = new ArrayList<>();
        ordenarPessoasPorNome(pessoas);
        todasPessoas.addAll(pessoas);
    }

    private static void ordenarPessoasPorNome(ArrayList<Pessoa> pessoas){
        Collections.sort(pessoas, new Comparator<Pessoa>() {
            @Override
            public int compare(Pessoa p0, Pessoa p1) {
                return StringUtil.removerAcentos(p0.getNome())
                        .compareToIgnoreCase(StringUtil.removerAcentos(p1.getNome()));
            }
        });
    }

    public static void adicionarPessoa(Pessoa pessoa){
        todasPessoas.add(pessoa);
        ordenarPessoasPorNome(todasPessoas);
        ConfiguracaoFirebase.getReferenciaPessoa().push().setValue(pessoa);
    }

    public static boolean isNomePessoaExistente(String nome){
        for(Pessoa pessoa : todasPessoas){
            if(nome.equalsIgnoreCase(pessoa.getNome()))
                return true;
        }

        return false;
    }

    public static void removerMusicasPessoaAtiva(ArrayList<Musica> musicas){
        for(Musica musica : musicas){
            pessoaAtiva.getCodigosMusicas().remove(musica.getCodigo());
        }
        atulizarListaMusicasPessoaAtiva();
    }

    public static void atulizarListaMusicasPessoaAtiva(){
        ConfiguracaoFirebase.getReferenciaPessoa().orderByChild("nome").equalTo(pessoaAtiva.getNome())
                .addListenerForSingleValueEvent(PessoaListeners.getListenerUpdateMusicasPessoaAtiva());
    }

    public static void atualizarListaPessoas(ArrayList<Pessoa> pessoasParaExcluir){
        ConfiguracaoFirebase.getReferenciaPessoa().addListenerForSingleValueEvent(PessoaListeners.getListenerUpdateListaPessoas(pessoasParaExcluir));
    }

    public static void setPessoaAtiva(Pessoa pessoa){
        pessoaAtiva = pessoa;
    }

    public static Pessoa getPessoaAtiva(){
        return pessoaAtiva;
    }

    public static RecyclerView.Adapter getAdapterMusicasPessoa() {
        return adapterMusicasPessoa;
    }

    public static void setAdapterMusicasPessoa(RecyclerView.Adapter adapterMusicasPessoa) {
        PessoaUtil.adapterMusicasPessoa = adapterMusicasPessoa;
    }

    public static ArrayAdapter getAdapterPessoa() {
        return adapterPessoa;
    }

    public static void setAdapterPessoa(ArrayAdapter adapterPessoa) {
        PessoaUtil.adapterPessoa = adapterPessoa;
    }
}
