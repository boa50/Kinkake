package br.com.boa50.kinkake.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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
import br.com.boa50.kinkake.fragment.AdicionaMusicaPessoaFragment;

public class MusicasAdicionarActivity extends AppCompatActivity {

    private AdicionaMusicaPessoaFragment adicionaMusicaPessoaFragment;
    private String buscaFiltro;
    private boolean favoritoFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_fragment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_fragmento);
        adicionaMusicaPessoaFragment = new AdicionaMusicaPessoaFragment();
        buscaFiltro = "";
        favoritoFiltro = false;

        toolbar.setTitle(R.string.tituloMusicasPessoaAdicionar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_fragmento, adicionaMusicaPessoaFragment);
        fragmentTransaction.commit();
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
                gerenciarBusca();
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                return true;
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

        favorito.setChecked(favoritoFiltro);

        favorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                favoritoFiltro = isChecked;
                gerenciarBusca();
            }
        });

        dialogFiltro.setView(viewFiltro);
        dialogFiltro.create();
        dialogFiltro.show();
    }

    private void gerenciarBusca(){
        adicionaMusicaPessoaFragment.filtrar(buscaFiltro, favoritoFiltro);
    }

    public AdicionaMusicaPessoaFragment getAdicionaMusicaPessoaFragment(){
        return adicionaMusicaPessoaFragment;
    }
}
