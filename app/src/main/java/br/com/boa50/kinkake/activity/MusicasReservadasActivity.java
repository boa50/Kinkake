package br.com.boa50.kinkake.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.fragment.MusicasReservadasFragment;

public class MusicasReservadasActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MusicasReservadasFragment musicasReservadasFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_fragment);

        toolbar = (Toolbar) findViewById(R.id.tb_fragmento);
        musicasReservadasFragment = new MusicasReservadasFragment();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_fragmento, musicasReservadasFragment);
        fragmentTransaction.commit();
    }
}
