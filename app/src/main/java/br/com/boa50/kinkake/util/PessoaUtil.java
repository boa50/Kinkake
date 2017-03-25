package br.com.boa50.kinkake.util;

import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.boa50.kinkake.application.ConfiguracaoFirebase;
import br.com.boa50.kinkake.application.PessoaListeners;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.model.Pessoa;

public class PessoaUtil {
    private static ArrayList<Pessoa> todasPessoas;
    private static ArrayList<String> nomesAdicionados;
    private static Pessoa pessoaAtiva;

    private static RecyclerView.Adapter adapterMusicasPessoa;
    private static RecyclerView.Adapter adapterPessoa;

    private static DatabaseReference databaseReference;
    private static ChildEventListener listenerPessoas;

    public static ArrayList<Pessoa> getTodasPessoas(){
        return todasPessoas;
    }

    public static void preencherTodasPessoas(ArrayList<Pessoa> pessoas){
        todasPessoas = new ArrayList<>();
        ordenarPessoasPorNome(pessoas);
        todasPessoas.addAll(pessoas);

        nomesAdicionados = new ArrayList<>();
        for(Pessoa pessoa : todasPessoas){
            nomesAdicionados.add(pessoa.getNome());
        }
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

    public static void adicionarListenerPessoas(RecyclerView.Adapter adapter){
        if(databaseReference == null)
            databaseReference = ConfiguracaoFirebase.getReferenciaPessoa();

        listenerPessoas = PessoaListeners.getListenerTodasPessoas(adapter, todasPessoas, nomesAdicionados);
        databaseReference.addChildEventListener(listenerPessoas);
    }

    public static void removerListenerPessoas(){
        databaseReference.removeEventListener(listenerPessoas);
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

    public static RecyclerView.Adapter getAdapterPessoa() {
        return adapterPessoa;
    }

    public static void setAdapterPessoa(RecyclerView.Adapter adapterPessoa) {
        PessoaUtil.adapterPessoa = adapterPessoa;
    }
}
