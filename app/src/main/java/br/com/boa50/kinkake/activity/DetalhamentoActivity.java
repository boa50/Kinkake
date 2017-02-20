package br.com.boa50.kinkake.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.fragment.DetalhamentoFragment;
import br.com.boa50.kinkake.fragment.MusicasFragment;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.util.ExtrasNomes;

public class DetalhamentoActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhamento);

        toolbar = (Toolbar) findViewById(R.id.tb_detalhamento);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Musica musica = new Musica();
        Bundle extras = getIntent().getExtras();
        if(extras != null)
            musica = extras.getParcelable(ExtrasNomes.MUSICA.getValor());

        toolbar.setTitle(musica.getNome());

        DetalhamentoFragment detalhamentoFragment = new DetalhamentoFragment();
        detalhamentoFragment.setMusica(musica);
        fragmentTransaction.add(R.id.ll_detalhamento, detalhamentoFragment);
        fragmentTransaction.commit();
    }

}
