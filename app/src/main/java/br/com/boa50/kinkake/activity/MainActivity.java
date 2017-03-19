package br.com.boa50.kinkake.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.adapter.TabAdapter;
import br.com.boa50.kinkake.fragment.CantoresFragment;
import br.com.boa50.kinkake.fragment.MusicasFragment;
import br.com.boa50.kinkake.util.FiltroUtil;
import br.com.boa50.kinkake.util.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabAdapter adapter;

    private String buscaFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SlidingTabLayout slidingTabLayout;

        FiltroUtil.setFavoritoFiltro(false);
        buscaFiltro = "";

        toolbar = (Toolbar) findViewById(R.id.tb_main);
        viewPager = (ViewPager) findViewById(R.id.vp_fragmentos_main);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_main);

        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        adapter = new TabAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);

        slidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.text_item_tab);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.white));
        slidingTabLayout.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                MenuItem busca = toolbar.getMenu().findItem(R.id.item_busca);
                MenuItem filtro = toolbar.getMenu().findItem(R.id.item_filtro);

                if(!((TabAdapter) viewPager.getAdapter()).getNomeFragment(position).equals("musicasReservadasFragment")){
                    busca.setVisible(true);
                    filtro.setVisible(true);
                    gerenciarBusca(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
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
        toolbarFiltro.setTitle(getResources().getString(R.string.filtroTitulo));

        favorito.setChecked(FiltroUtil.isFavoritoFiltro());

        favorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FiltroUtil.setFavoritoFiltro(isChecked);
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
            cantoresFragment.filtrar(buscaFiltro, FiltroUtil.isFavoritoFiltro());
        }else{
            musicasFragment.filtrar(buscaFiltro, FiltroUtil.isFavoritoFiltro());
        }
    }
}
