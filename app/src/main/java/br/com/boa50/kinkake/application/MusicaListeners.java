package br.com.boa50.kinkake.application;


import android.widget.ArrayAdapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.util.MusicaUtil;

public class MusicaListeners {

    public static ValueEventListener getListenerMusicaFavorito(){
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DatabaseReference databaseReference = ConfiguracaoFirebase.getReferenciaMusica();
                boolean favorito = false;

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    for(DataSnapshot snapshotCampo : snapshot.getChildren()){
                        if(snapshotCampo.getKey().equals("favorito"))
                            favorito = snapshotCampo.getValue(Boolean.class);
                    }

                    databaseReference
                            .child(snapshot.getKey())
                            .child("favorito")
                            .setValue(!favorito);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    public static ChildEventListener getListenerMusicas(final ArrayAdapter adapter){

        return new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Musica musica = MusicaUtil.getMusicaPorKey(dataSnapshot.getKey());
                boolean favorito = false;

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(snapshot.getKey().equals("favorito"))
                        favorito = snapshot.getValue(Boolean.class);
                }

                musica.setFavorito(favorito);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }
}
