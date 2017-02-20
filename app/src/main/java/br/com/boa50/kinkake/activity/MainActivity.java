package br.com.boa50.kinkake.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.adapter.TabAdapter;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tb_main);
        viewPager = (ViewPager) findViewById(R.id.vp_fragmentos_main);

        toolbar.setTitle(getResources().getString(R.string.app_name));

        adapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

//        MusicasFragment musicasFragment = new MusicasFragment();
//        CantoresFragment cantoresFragment = new CantoresFragment();
//        fragmentTransaction.add(R.id.vp_fragmentos_main, cantoresFragment);
//        fragmentTransaction.commit();
    }
}
