package br.com.boa50.kinkake.application;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.boa50.kinkake.model.Musica;

public class MusicaQueries {
    private static final DatabaseReference databaseReference =
            ConfiguracaoFirebase.getDatabaseReference().child("musica");

    public static ArrayList<Musica> getTodasMusicas(){
        final ArrayList<Musica> musicas = new ArrayList<Musica>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    musicas.add(snapshot.getValue(Musica.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return musicas;
    }
}
