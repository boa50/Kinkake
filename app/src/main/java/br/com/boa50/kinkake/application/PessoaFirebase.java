package br.com.boa50.kinkake.application;

import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import br.com.boa50.kinkake.model.Pessoa;
import br.com.boa50.kinkake.util.VariaveisEstaticas;

public class PessoaFirebase {
    private static DatabaseReference databaseReference;
    private static ChildEventListener listenerPessoas;
    private static ChildEventListener listenerMusicasPessoas;


    private static void verificarReferenciaNula(){
        if (databaseReference == null)
            databaseReference = ConfiguracaoFirebase.getReferenciaPessoa();
    }

    public static void adicionarPessoa(Pessoa pessoa){
        verificarReferenciaNula();

        databaseReference.push().setValue(pessoa);
    }

    public static void removerPessoas(ArrayList<Pessoa> pessoasParaExcluir){
        verificarReferenciaNula();

        databaseReference.addListenerForSingleValueEvent(
                PessoaListeners.getListenerRemoverPessoas(pessoasParaExcluir));
    }

    public static void adicionarListenerPessoas(RecyclerView.Adapter adapter){
        verificarReferenciaNula();

        if (listenerPessoas != null)
            databaseReference.removeEventListener(listenerPessoas);

        listenerPessoas = PessoaListeners.getListenerTodasPessoas(
                adapter, VariaveisEstaticas.getTodasPessoas(), VariaveisEstaticas.getNomesAdicionados()
        );

        databaseReference.addChildEventListener(listenerPessoas);
    }

    public static void adicionarListenerMusicasPessoa(RecyclerView.Adapter adapter, Pessoa pessoa){
        verificarReferenciaNula();

        if (listenerMusicasPessoas != null)
            databaseReference.removeEventListener(listenerMusicasPessoas);

        listenerMusicasPessoas = PessoaListeners.getListenerMusicasPessoa(
                adapter, pessoa, pessoa.getCodigosMusicas()
        );

        databaseReference.orderByChild("nome").equalTo(pessoa.getNome())
                .addChildEventListener(listenerMusicasPessoas);
    }

    public static void atulizarListaMusicasPessoaAtiva(){
        verificarReferenciaNula();

        databaseReference.orderByChild("nome").equalTo(VariaveisEstaticas.getPessoaAtiva().getNome())
                .addListenerForSingleValueEvent(PessoaListeners.getListenerUpdateMusicasPessoaAtiva());
    }

}
