package br.com.boa50.kinkake.application;

import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

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

    public static void adicionarListenerMusicasPessoa(final RecyclerView.Adapter adapter, final Pessoa pessoa){
        verificarReferenciaNula();

        databaseReference.orderByChild("nome").equalTo(pessoa.getNome())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (listenerMusicasPessoas != null)
                            databaseReference.child(dataSnapshot.getChildren().iterator().next().getKey()).child("codigosMusicas")
                                    .removeEventListener(listenerMusicasPessoas);

                        listenerMusicasPessoas = PessoaListeners.getListenerMusicasPessoa(
                                adapter, pessoa.getCodigosMusicas()
                        );

                        databaseReference.child(dataSnapshot.getChildren().iterator().next().getKey()).child("codigosMusicas")
                                .addChildEventListener(listenerMusicasPessoas);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
    }

    public static void removerMusicasPessoaAtiva(ArrayList<Integer> codigosMusicasExcluir){
        ArrayList<Integer> codigosMusicasPessoa = new ArrayList<>();
        codigosMusicasPessoa.addAll(VariaveisEstaticas.getPessoaAtiva().getCodigosMusicas());
        codigosMusicasPessoa.removeAll(codigosMusicasExcluir);

        atulizarListaMusicasPessoaAtiva(codigosMusicasPessoa);
    }

    public static void adicionarMusicasPessoaAtiva(ArrayList<Integer> codigosMusicasAdicionar){
        ArrayList<Integer> codigosMusicasPessoa = new ArrayList<>();
        codigosMusicasPessoa.addAll(VariaveisEstaticas.getPessoaAtiva().getCodigosMusicas());
        codigosMusicasPessoa.addAll(codigosMusicasAdicionar);

        atulizarListaMusicasPessoaAtiva(codigosMusicasPessoa);
    }

    private static void atulizarListaMusicasPessoaAtiva(ArrayList<Integer> codigosMusicasPessoa){
        verificarReferenciaNula();

        databaseReference.orderByChild("nome").equalTo(VariaveisEstaticas.getPessoaAtiva().getNome())
                .addListenerForSingleValueEvent(PessoaListeners.getListenerUpdateMusicasPessoaAtiva(codigosMusicasPessoa));
    }

}
