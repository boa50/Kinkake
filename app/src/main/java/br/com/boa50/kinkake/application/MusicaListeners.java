package br.com.boa50.kinkake.application;


import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.boa50.kinkake.model.Cantor;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.util.MusicaUtil;

public class MusicaListeners {

    public static ChildEventListener getListenerMusicaFavorito(final boolean favorito){

        return new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference();

                databaseReference
                        .child(dataSnapshot.getKey())
                        .child("favorito")
                        .setValue(favorito);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

    public static ChildEventListener getListenerMusicas(final ArrayAdapter adapter){

        return new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference();

                Musica musica = null;
                boolean favorito = false;

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(snapshot.getKey().equals("codigo"))
                        musica = MusicaUtil.getMusicaPorCodigo(snapshot.getValue(Integer.class));
                    if(snapshot.getKey().equals("favorito"))
                        favorito = snapshot.getValue(Boolean.class);
                }

                if(musica != null)
                    musica.setFavorito(favorito);

                adapter.notifyDataSetChanged();

                //TODO verificar o excesso de conexões
                Log.i("QWERTY_CH", dataSnapshot.getValue().toString());
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
