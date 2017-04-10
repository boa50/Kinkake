package br.com.boa50.kinkake.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.MusicasReservadasActivity;
import br.com.boa50.kinkake.adapter.PessoaAdapter;
import br.com.boa50.kinkake.application.PessoaFirebase;
import br.com.boa50.kinkake.model.Pessoa;
import br.com.boa50.kinkake.util.ItemClickSupport;
import br.com.boa50.kinkake.util.PessoaUtil;
import br.com.boa50.kinkake.util.VariaveisEstaticas;

public class MusicasReservadasFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView textViewVazio;
    private FloatingActionButton fabAddPessoa;
    private ArrayList<Pessoa> pessoas;
    private ArrayList<Pessoa> pessoasParaExcluir;
    private ArrayList<Integer> posicoesViewsSelecionadas;
    private ActionBar toolbar;
    private MenuItem itemExcluir;

    public MusicasReservadasFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycle_fab, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_fab);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        fabAddPessoa = (FloatingActionButton) view.findViewById(R.id.fab_fragment_recycle);
        textViewVazio = (TextView) view.findViewById(R.id.tv_fragment_recycle_fab);
        pessoas = VariaveisEstaticas.getTodasPessoas();
        adapter = new PessoaAdapter(pessoas);
        pessoasParaExcluir = new ArrayList<>();
        posicoesViewsSelecionadas = new ArrayList<>();
        toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        recyclerView.setAdapter(adapter);
        setHasOptionsMenu(true);
        verificarListaPessoasVazia();

        fabAddPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirDialogoAddPessoa();
            }
        });

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                if(pessoasParaExcluir.isEmpty()){
                    Pessoa pessoa = pessoas.get(position);
                    Intent intent = new Intent(getActivity(), MusicasReservadasActivity.class);
                    MusicasPorPessoaFragment.setMusicas(pessoa.getCodigosMusicas());
                    VariaveisEstaticas.setPessoaAtiva(pessoa);
                    startActivity(intent);
                }else{
                    if(posicoesViewsSelecionadas.contains(position)){
                        removerPessoaExcluir(v, position);
                        if(pessoasParaExcluir.isEmpty())
                            voltarEstadoTela();
                    }else{
                        adicionarPessoaExcluir(v, position);
                    }
                }
            }
        });

        ItemClickSupport.addTo(recyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                adicionarPessoaExcluir(v, position);
                itemExcluir.setVisible(true);
                toolbar.setTitle(R.string.tituloPessoasExcluir);
                toolbar.setDisplayHomeAsUpEnabled(true);
                return true;
            }
        });

        return view;
    }

    private void abrirDialogoAddPessoa(){
        final AlertDialog.Builder dialogAddPessoa = new AlertDialog.Builder(getActivity(), R.style.AlertDialogCustom);
        View viewAddPessoa = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_pessoa, null);

        dialogAddPessoa.setCancelable(true);

        Toolbar toolbarAddPessoa = (Toolbar) viewAddPessoa.findViewById(R.id.tb_dialog_add_pessoa);
        toolbarAddPessoa.setTitle(getResources().getString(R.string.addPessoaTitulo));
        final EditText textoNome = (EditText) viewAddPessoa.findViewById(R.id.et_dialog_add_pessoa);

        dialogAddPessoa.setPositiveButton(R.string.btAdicionar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(textoNome.getText() == null || textoNome.getText().toString().isEmpty()){
                    Snackbar snackbar = Snackbar.make(getView(), R.string.erroAddPessoaSemNome, Snackbar.LENGTH_LONG);
                    ((TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text))
                            .setTextColor(ContextCompat.getColor(getActivity(), R.color.yellow600));
                    snackbar.show();
                }else if(PessoaUtil.isNomePessoaExistente(textoNome.getText().toString())){
                    Snackbar snackbar = Snackbar.make(getView(), R.string.erroAddPessoaNomeExistente, Snackbar.LENGTH_LONG);
                    ((TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text))
                            .setTextColor(ContextCompat.getColor(getActivity(), R.color.yellow600));
                    snackbar.show();
                    textoNome.setText("");
                }else if (textoNome.getText() != null && textoNome.getText().length() > 0) {
                    PessoaFirebase.adicionarPessoa(new Pessoa(textoNome.getText().toString()));
                    verificarListaPessoasVazia();
                    adapter.notifyDataSetChanged();
                    textoNome.setText("");
                }
            }
        });

        dialogAddPessoa.setNegativeButton(R.string.btCancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                textoNome.setText("");
            }
        });

        dialogAddPessoa.setView(viewAddPessoa);
        dialogAddPessoa.create();
        AlertDialog alertDialog = dialogAddPessoa.show();
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_reservadas, menu);
        super.onCreateOptionsMenu(menu, inflater);

        itemExcluir = menu.findItem(R.id.item_excluir);
        menu.findItem(R.id.item_busca).setVisible(false);
        menu.findItem(R.id.item_busca).collapseActionView();
        menu.findItem(R.id.item_filtro).setVisible(false);
        itemExcluir.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_excluir:
                removerPessoasSelecionadas();
                return true;
            case android.R.id.home:
                voltarEstadoTela();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void removerPessoasSelecionadas(){
        ArrayList<Pessoa> teste = new ArrayList<>();
        teste.addAll(pessoasParaExcluir);
        PessoaFirebase.removerPessoas(teste);
        verificarListaPessoasVazia();
        voltarEstadoTela();
    }

    public void adicionarPessoaExcluir(View view, int position){
        view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.highlightList));
        posicoesViewsSelecionadas.add(position);
        pessoasParaExcluir.add(pessoas.get(position));
    }

    public void removerPessoaExcluir(View view, int position){
        view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.background));
        posicoesViewsSelecionadas.remove(Integer.valueOf(position));
        pessoasParaExcluir.remove(pessoas.get(position));
    }

    public void voltarEstadoTela(){
        for(Integer posicao : posicoesViewsSelecionadas){
            recyclerView.getChildAt(posicao).setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.background));
        }
        pessoasParaExcluir.clear();
        posicoesViewsSelecionadas.clear();
        itemExcluir.setVisible(false);
        toolbar.setTitle(R.string.app_name);
        toolbar.setDisplayHomeAsUpEnabled(false);
    }

    private void verificarListaPessoasVazia(){
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(pessoas.isEmpty())
                    textViewVazio.setText(R.string.reservadasPessoasVazio);
                else
                    textViewVazio.setText("");
            }
        }, 50);
    }

    public void esconderFab(){
        if(fabAddPessoa != null)
            fabAddPessoa.hide();
    }

    public void mostrarFabDelay(){
        if (fabAddPessoa.isShown())
            fabAddPessoa.hide();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fabAddPessoa.show();
            }
        }, 500);
    }

    @Override
    public void onPause() {
        super.onPause();
        esconderFab();
    }

    @Override
    public void onStart() {
        super.onStart();
        PessoaFirebase.adicionarListenerPessoas(adapter);
        mostrarFabDelay();
    }
}
