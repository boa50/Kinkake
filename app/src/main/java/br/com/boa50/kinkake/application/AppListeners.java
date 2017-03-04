package br.com.boa50.kinkake.application;

import android.app.Activity;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.boa50.kinkake.model.Cantor;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.util.CantorUtil;
import br.com.boa50.kinkake.util.MusicaUtil;

public class AppListeners {
    public static ValueEventListener getListenerPreencher(final Intent intent, final Activity activity){
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> cantoresIncluidos = new ArrayList<>();
                ArrayList<Cantor> cantores = new ArrayList<>();
                ArrayList<Musica> musicas = new ArrayList<>();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Musica musica = snapshot.getValue(Musica.class);
                    musicas.add(musica);

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

                MusicaUtil.preencheTodasMusicas(musicas);
                CantorUtil.preencheTodosCantores(cantores);

                activity.startActivity(intent);
                activity.finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }
}
