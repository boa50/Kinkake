package br.com.boa50.kinkake.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.MusicasReservadasActivity;
import br.com.boa50.kinkake.adapter.PessoaAdapter;
import br.com.boa50.kinkake.model.Pessoa;
import br.com.boa50.kinkake.util.PessoaUtil;

public class MusicasReservadasFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private FloatingActionButton fabAddPessoa;
    private TextView textViewVazio;
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
        View view = inflater.inflate(R.layout.fragment_musicas_reservadas, container, false);

        listView = (ListView) view.findViewById(R.id.lv_fragmento);
        fabAddPessoa = (FloatingActionButton) view.findViewById(R.id.fab_add);
        textViewVazio = (TextView) view.findViewById(R.id.tv_reservadas_vazio);
        pessoas = PessoaUtil.getTodasPessoas();
        adapter = new PessoaAdapter(getActivity(), pessoas);
        pessoasParaExcluir = new ArrayList<>();
        posicoesViewsSelecionadas = new ArrayList<>();
        toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        PessoaUtil.setAdapterPessoa(adapter);
        listView.setAdapter(adapter);
        setHasOptionsMenu(true);
        verificarListaPessoasVazia();

        fabAddPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirDialogoAddPessoa();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adicionarPessoaExcluir(view, position);
                itemExcluir.setVisible(true);
                toolbar.setTitle(R.string.tituloPessoasExcluir);
                toolbar.setDisplayHomeAsUpEnabled(true);
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(pessoasParaExcluir.isEmpty()){
                    Pessoa pessoa = pessoas.get(position);
                    Intent intent = new Intent(getActivity(), MusicasReservadasActivity.class);
                    MusicasPorPessoaFragment.setMusicas(pessoa.getCodigosMusicas());
                    PessoaUtil.setPessoaAtiva(pessoa);
                    startActivity(intent);
                }else{
                    if(posicoesViewsSelecionadas.contains(position)){
                        removerPessoaExcluir(view, position);
                        if(pessoasParaExcluir.isEmpty())
                            voltarEstadoTela();
                    }else{
                        adicionarPessoaExcluir(view, position);
                    }
                }
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
                    PessoaUtil.adicionarPessoa(new Pessoa(textoNome.getText().toString()));
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
                limparListaExclusao();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void removerPessoasSelecionadas(){
        pessoas.removeAll(pessoasParaExcluir);
        ArrayList<Pessoa> teste = new ArrayList<>();
        teste.addAll(pessoasParaExcluir);
        PessoaUtil.atualizarListaPessoas(teste);
        verificarListaPessoasVazia();
        adapter.notifyDataSetChanged();
        voltarEstadoTela();
    }

    private void limparListaExclusao(){
        for(Integer posicao : posicoesViewsSelecionadas){
            listView.getChildAt(posicao).setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.background));
        }
        voltarEstadoTela();
    }

    private void adicionarPessoaExcluir(View view, int position){
        view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.highlightList));
        posicoesViewsSelecionadas.add(position);
        pessoasParaExcluir.add(pessoas.get(position));
    }

    private void removerPessoaExcluir(View view, int position){
        view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.background));
        posicoesViewsSelecionadas.remove(Integer.valueOf(position));
        pessoasParaExcluir.remove(pessoas.get(position));
    }

    private void voltarEstadoTela(){
        pessoasParaExcluir.clear();
        posicoesViewsSelecionadas.clear();
        itemExcluir.setVisible(false);
        toolbar.setTitle(R.string.app_name);
        toolbar.setDisplayHomeAsUpEnabled(false);
    }

    private void verificarListaPessoasVazia(){
        if(pessoas.isEmpty())
            textViewVazio.setText(R.string.reservadasPessoasVazio);
        else
            textViewVazio.setText("");
    }
}
