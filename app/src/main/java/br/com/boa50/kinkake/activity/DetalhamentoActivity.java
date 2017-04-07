package br.com.boa50.kinkake.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.adapter.PessoaAdapter;
import br.com.boa50.kinkake.application.PessoaFirebase;
import br.com.boa50.kinkake.fragment.DetalhamentoFragment;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.model.Pessoa;
import br.com.boa50.kinkake.util.ExtrasNomes;
import br.com.boa50.kinkake.util.ItemClickSupport;
import br.com.boa50.kinkake.util.PessoaUtil;
import br.com.boa50.kinkake.util.VariaveisEstaticas;

public class DetalhamentoActivity extends AppCompatActivity {
    private Musica musica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_fragment);
        Toolbar toolbar;

        toolbar = (Toolbar) findViewById(R.id.tb_fragmento);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        musica = new Musica();
        Bundle extras = getIntent().getExtras();
        if(extras != null)
            musica = extras.getParcelable(ExtrasNomes.MUSICA.getValor());

        toolbar.setTitle(musica.getNome());
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DetalhamentoFragment detalhamentoFragment = new DetalhamentoFragment();
        detalhamentoFragment.setMusica(musica);
        fragmentTransaction.add(R.id.fl_fragmento, detalhamentoFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalhamento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.item_reserva:
                if (VariaveisEstaticas.getTodasPessoas().isEmpty())
                    mostrarSnackbar(R.string.musicaReservarSemPessoaCadastrada);
                else{
                    ArrayList<Pessoa> pessoasDisponiveis = PessoaUtil.getPessoasSemMusicaPorCodigo(musica.getCodigo());
                    if (pessoasDisponiveis.isEmpty())
                        mostrarSnackbar(R.string.musicaReservarSemPessoaDisponivel);
                    else
                        abrirDialogReserva(pessoasDisponiveis);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void abrirDialogReserva(ArrayList<Pessoa> pessoasDisponiveis){
        AlertDialog.Builder dialogReserva = new AlertDialog.Builder(this);
        View viewReserva = this.getLayoutInflater().inflate(R.layout.dialog_reserva, null);

        Toolbar toolbarReserva = (Toolbar) viewReserva.findViewById(R.id.tb_dialog_reserva);
        toolbarReserva.setTitle("Reservar");

        RecyclerView recyclerView = (RecyclerView) viewReserva.findViewById(R.id.rv_dialog_reserva);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter;
        final ArrayList<Pessoa> pessoas = new ArrayList<>();
        pessoas.addAll(pessoasDisponiveis);
        adapter = new PessoaAdapter(pessoas);
        recyclerView.setAdapter(adapter);

        dialogReserva.setView(viewReserva);
        dialogReserva.create();
        final AlertDialog dialog = dialogReserva.show();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                ArrayList<Integer> codigoMusicaAdicionar = new ArrayList<Integer>();
                codigoMusicaAdicionar.add(musica.getCodigo());

                VariaveisEstaticas.setPessoaAtiva(pessoas.get(position));
                PessoaFirebase.adicionarMusicasPessoaAtiva(codigoMusicaAdicionar);

                dialog.dismiss();
                mostrarSnackbar(R.string.musicaReservadaSucesso);
            }
        });
    }

    private void mostrarSnackbar(int mensagemId){
        Snackbar snackbar = Snackbar.make(this.findViewById(R.id.fl_fragmento), mensagemId, Snackbar.LENGTH_LONG);
        ((TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text))
                .setTextColor(ContextCompat.getColor(this, R.color.yellow600));
        snackbar.show();
    }
}
