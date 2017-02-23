package br.com.boa50.kinkake.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.adapter.TabAdapter;
import br.com.boa50.kinkake.fragment.CantoresFragment;
import br.com.boa50.kinkake.fragment.MusicasFragment;

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
        setSupportActionBar(toolbar);

        adapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem busca = menu.findItem(R.id.item_busca);
        SearchView searchView = (SearchView) busca.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                CantoresFragment cantoresFragment = (CantoresFragment) adapter.getItem(0);
                MusicasFragment musicasFragment = (MusicasFragment) adapter.getItem(1);

                if(viewPager.getCurrentItem() == 0){
                    cantoresFragment.filtrar(newText);
                    musicasFragment.filtrar("");
                }else{
                    musicasFragment.filtrar(newText);
                    cantoresFragment.filtrar("");
                }
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_busca:
                return super.onOptionsItemSelected(item);
            case R.id.item_filtro:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
