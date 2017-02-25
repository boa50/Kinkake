package br.com.boa50.kinkake.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.adapter.TabAdapter;
import br.com.boa50.kinkake.fragment.CantoresFragment;
import br.com.boa50.kinkake.fragment.MusicasFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabAdapter adapter;

    private String buscaFiltro;
    private boolean favoritoFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favoritoFiltro = false;
        buscaFiltro = "";

        toolbar = (Toolbar) findViewById(R.id.tb_main);
        viewPager = (ViewPager) findViewById(R.id.vp_fragmentos_main);

        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        adapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                gerenciarBusca(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
                buscaFiltro = newText;
                gerenciarBusca(viewPager.getCurrentItem());
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
                abrirFiltro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirFiltro(){
        AlertDialog.Builder dialogFiltro = new AlertDialog.Builder(this);
        View viewFiltro = this.getLayoutInflater().inflate(R.layout.filtro, null);

        Toolbar toolbarFiltro = (Toolbar) viewFiltro.findViewById(R.id.tb_filtro);
        CheckBox favorito = (CheckBox) viewFiltro.findViewById(R.id.cb_filtro_favorito);
        toolbarFiltro.setTitle("Filtro");

        favorito.setChecked(favoritoFiltro);

        favorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                favoritoFiltro = b;
                gerenciarBusca(viewPager.getCurrentItem());
            }
        });

        dialogFiltro.setView(viewFiltro);
        dialogFiltro.create();
        dialogFiltro.show();
    }

    private void gerenciarBusca(int position){
        CantoresFragment cantoresFragment = (CantoresFragment) adapter.getItem(0);
        MusicasFragment musicasFragment = (MusicasFragment) adapter.getItem(1);

        if(position == 0){
            cantoresFragment.filtrar(buscaFiltro, favoritoFiltro);
        }else{
            musicasFragment.filtrar(buscaFiltro);
        }
    }
}
