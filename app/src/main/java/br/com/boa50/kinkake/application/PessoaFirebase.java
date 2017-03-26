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

        listenerPessoas = PessoaListeners.getListenerTodasPessoas(
                adapter, VariaveisEstaticas.getTodasPessoas(), VariaveisEstaticas.getNomesAdicionados()
        );
        databaseReference.addChildEventListener(listenerPessoas);
    }

    public static void removerListenerPessoas(){
        databaseReference.removeEventListener(listenerPessoas);
    }

    public static void atulizarListaMusicasPessoaAtiva(){
        verificarReferenciaNula();

        databaseReference.orderByChild("nome").equalTo(VariaveisEstaticas.getPessoaAtiva().getNome())
                .addListenerForSingleValueEvent(PessoaListeners.getListenerUpdateMusicasPessoaAtiva());
    }

}
