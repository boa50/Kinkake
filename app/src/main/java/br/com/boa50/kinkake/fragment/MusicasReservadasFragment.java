package br.com.boa50.kinkake.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.MusicasReservadasActivity;
import br.com.boa50.kinkake.adapter.PessoaAdapter;
import br.com.boa50.kinkake.model.Pessoa;
import br.com.boa50.kinkake.util.ExtrasNomes;
import br.com.boa50.kinkake.util.PessoaUtil;

public class MusicasReservadasFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Pessoa> pessoas;
    private FloatingActionButton fabAddPessoa;

    public MusicasReservadasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_musicas_reservadas, container, false);

        listView = (ListView) view.findViewById(R.id.lv_fragmento);
        fabAddPessoa = (FloatingActionButton) view.findViewById(R.id.fab_add);
//        pessoas = new ArrayList<>();
//        pessoas.addAll(PessoaUtil.getTodasPessoas());
        pessoas = PessoaUtil.getTodasPessoas();
        adapter = new PessoaAdapter(getActivity(), pessoas);

        listView.setAdapter(adapter);

        fabAddPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirDialogoAddPessoa();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pessoa pessoa = pessoas.get(i);

                Intent intent = new Intent(getActivity(), MusicasReservadasActivity.class);
                intent.putExtra(ExtrasNomes.NOME_PESSOA.getValor(), pessoa.getNome());
                intent.putExtra(ExtrasNomes.LISTA_MUSICAS_PESSOA.getValor(), pessoa.getCodigosMusicas());
                startActivity(intent);
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
                if (textoNome.getText() != null && textoNome.getText().length() > 0) {
                    pessoas.add(new Pessoa(textoNome.getText().toString()));
                    adapter.notifyDataSetChanged();
                    textoNome.setText("");
//                    Snackbar snackbar = Snackbar.make(getView(), "teste add", Snackbar.LENGTH_LONG);
//                    snackbar.show();
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
        dialogAddPessoa.show();


    }
}
