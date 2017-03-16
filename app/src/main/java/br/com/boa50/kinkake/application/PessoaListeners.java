package br.com.boa50.kinkake.application;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.boa50.kinkake.model.Pessoa;
import br.com.boa50.kinkake.util.PessoaUtil;

public class PessoaListeners {
    public static ValueEventListener getListenerUpdateMusicasPessoaAtiva(){
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> updateMusicas = new HashMap<>();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    updateMusicas.put("codigosMusicas", PessoaUtil.getPessoaAtiva().getCodigosMusicas());
                    ConfiguracaoFirebase.getReferenciaPessoa().child(snapshot.getKey()).updateChildren(updateMusicas);
                    break;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    public static ValueEventListener getListenerUpdateListaPessoas(final ArrayList<Pessoa> pessoasParaExcluir){
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(Pessoa pessoa : pessoasParaExcluir){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        if(pessoa.getNome().equalsIgnoreCase(snapshot.child("nome").getValue().toString())){
                            ConfiguracaoFirebase.getReferenciaPessoa().child(snapshot.getKey()).removeValue();
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }
}
