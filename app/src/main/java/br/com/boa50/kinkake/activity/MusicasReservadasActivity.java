package br.com.boa50.kinkake.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.fragment.MusicasPorPessoaFragment;
import br.com.boa50.kinkake.model.Pessoa;
import br.com.boa50.kinkake.util.VariaveisEstaticas;

public class MusicasReservadasActivity extends AppCompatActivity {
    private MusicasPorPessoaFragment musicasPorPessoaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_fragment);
        Toolbar toolbar;

        toolbar = (Toolbar) findViewById(R.id.tb_fragmento);
        musicasPorPessoaFragment = new MusicasPorPessoaFragment();

        Pessoa pessoa = VariaveisEstaticas.getPessoaAtiva();

        toolbar.setTitle("Músicas de " + pessoa.getNome());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_fragmento, musicasPorPessoaFragment);
        fragmentTransaction.commit();
    }
}
