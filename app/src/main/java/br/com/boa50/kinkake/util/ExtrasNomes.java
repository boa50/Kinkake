package br.com.boa50.kinkake.util;

public enum ExtrasNomes {
    ID_CANTOR("idCantor"),
    NOME_CANTOR("nomeCantor"),
    LISTA_MUSICAS_CANTOR("listaMusicasCantor"),
    MUSICA("musica");

    private final String valor;

    ExtrasNomes(String valor){
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
