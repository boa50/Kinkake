package br.com.boa50.kinkake.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.fragment.MusicasFragment;
import br.com.boa50.kinkake.model.Cantor;
import br.com.boa50.kinkake.util.ExtrasNomes;
import br.com.boa50.kinkake.util.FiltroUtil;

public class MusicasCantorActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicas_cantor);

        Cantor cantor = new Cantor();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            cantor.setNome(extras.getString(ExtrasNomes.NOME_CANTOR.getValor()));
            cantor.setCodigosMusicas(extras.getIntegerArrayList(ExtrasNomes.LISTA_MUSICAS_CANTOR.getValor()));
        }

        toolbar = (Toolbar) findViewById(R.id.tb_main);
        toolbar.setTitle(cantor.getNome());
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        MusicasFragment musicasFragment = new MusicasFragment();
        musicasFragment.setCantor(cantor);
        fragmentTransaction.add(R.id.fl_fragmento_musicas_cantor, musicasFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem busca = menu.findItem(R.id.item_busca);
        busca.setEnabled(false);
        busca.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.item_filtro:
                abrirFiltro();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void abrirFiltro(){
        AlertDialog.Builder dialogFiltro = new AlertDialog.Builder(this);
        View viewFiltro = this.getLayoutInflater().inflate(R.layout.filtro, null);

        Toolbar toolbarFiltro = (Toolbar) viewFiltro.findViewById(R.id.tb_filtro);
        CheckBox favorito = (CheckBox) viewFiltro.findViewById(R.id.cb_filtro_favorito);
        toolbarFiltro.setTitle("Filtro");

        favorito.setChecked(FiltroUtil.isFavoritoFiltro());

        favorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                FiltroUtil.setFavoritoFiltro(b);
            }
        });

        dialogFiltro.setView(viewFiltro);
        dialogFiltro.create();
        dialogFiltro.show();
    }
}
