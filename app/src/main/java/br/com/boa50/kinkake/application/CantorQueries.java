package br.com.boa50.kinkake.application;

import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.boa50.kinkake.model.Cantor;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.util.CantorUtil;

public class CantorQueries {
    public static ValueEventListener getListenerCantores(final ArrayList<Cantor> cantores, final ArrayAdapter adapter){
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> cantoresIncluidos = new ArrayList<>();
                cantores.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Musica musica = snapshot.getValue(Musica.class);

                    if(cantoresIncluidos.contains(musica.getCantor())){
                        for(Cantor cantor : cantores){
                            if(cantor.getNome().equalsIgnoreCase(musica.getCantor())) {
                                cantor.getCodigosMusicas().add(musica.getCodigo());
                                break;
                            }
                        }
                    }else{
                        Cantor cantor = new Cantor(
                                musica.getCodigo(),
                                musica.getCantor(),
                                new ArrayList<Integer>()
                        );

                        cantor.getCodigosMusicas().add(musica.getCodigo());

                        cantoresIncluidos.add(cantor.getNome());
                        cantores.add(cantor);
                    }
                }

                CantorUtil.preencheTodosCantores(cantores);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }
}
