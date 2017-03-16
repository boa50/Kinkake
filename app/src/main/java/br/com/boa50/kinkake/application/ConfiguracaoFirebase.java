package br.com.boa50.kinkake.application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class ConfiguracaoFirebase {
    private static DatabaseReference databaseReference;

    private static DatabaseReference getDatabaseReference(){
        if(databaseReference == null)
            databaseReference = FirebaseDatabase.getInstance().getReference();

        return databaseReference;
    }

    public static DatabaseReference getReferenciaMusica(){
        return getDatabaseReference().child("musica");
    }

    public static DatabaseReference getReferenciaPessoa(){
        return getDatabaseReference().child("pessoa");
    }

}
