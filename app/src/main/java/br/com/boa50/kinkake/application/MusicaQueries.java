package br.com.boa50.kinkake.application;


import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.util.MusicaUtil;

public class MusicaQueries {

    public static ValueEventListener getListenerMusicas(final ArrayList<Musica> musicas, final ArrayAdapter adapter){
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                musicas.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    musicas.add(snapshot.getValue(Musica.class));
                }

                MusicaUtil.preencheTodasMusicas(musicas);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }
}
