package br.com.boa50.kinkake.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DetalhamentoFragment detalhamentoFragment = new DetalhamentoFragment();
        detalhamentoFragment.setMusica(musica);
        fragmentTransaction.add(R.id.ll_detalhamento, detalhamentoFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
