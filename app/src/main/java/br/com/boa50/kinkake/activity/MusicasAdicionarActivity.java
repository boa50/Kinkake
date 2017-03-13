package br.com.boa50.kinkake.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.fragment.AdicionaMusicaPessoaFragment;

public class MusicasAdicionarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AdicionaMusicaPessoaFragment adicionaMusicaPessoaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_fragment);

        toolbar = (Toolbar) findViewById(R.id.tb_fragmento);
        adicionaMusicaPessoaFragment = new AdicionaMusicaPessoaFragment();

        toolbar.setTitle("Selecione as músicas:");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_fragmento, adicionaMusicaPessoaFragment);
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
