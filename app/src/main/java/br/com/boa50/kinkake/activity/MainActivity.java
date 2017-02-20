package br.com.boa50.kinkake.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.fragment.CantoresFragment;
import br.com.boa50.kinkake.fragment.MusicasFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tb_main);
        toolbar.setTitle(getResources().getString(R.string.app_name));

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

//        MusicasFragment musicasFragment = new MusicasFragment();
        CantoresFragment cantoresFragment = new CantoresFragment();
        fragmentTransaction.add(R.id.ll_fragmentos_main, cantoresFragment);
        fragmentTransaction.commit();
    }
}
