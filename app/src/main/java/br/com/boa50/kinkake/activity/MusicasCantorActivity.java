package br.com.boa50.kinkake.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.fragment.CantoresFragment;
import br.com.boa50.kinkake.fragment.MusicasFragment;
import br.com.boa50.kinkake.model.Cantor;
import br.com.boa50.kinkake.util.ExtrasNomes;

public class MusicasCantorActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicas_cantor);

        Cantor cantor = new Cantor();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            cantor.setNome(extras.getString(ExtrasNomes.NOME_CANTOR.getValor()));
            cantor.setCodigosMusicas(extras.getIntegerArrayList(ExtrasNomes.LISTA_MUSICAS_CANTOR.getValor()));
        }

        toolbar = (Toolbar) findViewById(R.id.tb_main);
        toolbar.setTitle(cantor.getNome());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        MusicasFragment musicasFragment = new MusicasFragment();
        musicasFragment.setCantor(cantor);
        fragmentTransaction.add(R.id.fl_fragmento_musicas_cantor, musicasFragment);
        fragmentTransaction.commit();
    }
}
