package br.com.boa50.kinkake.application;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.boa50.kinkake.model.Cantor;

public class CantorQueries {
    private static final DatabaseReference databaseReference =
            ConfiguracaoFirebase.getDatabaseReference().child("cantor");

    public static ArrayList<Cantor> getTodosCantores(){
        final ArrayList<Cantor> cantores = new ArrayList<Cantor>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    cantores.add(snapshot.getValue(Cantor.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return cantores;
    }
}
