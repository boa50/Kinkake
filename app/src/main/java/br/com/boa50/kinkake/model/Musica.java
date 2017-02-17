package br.com.boa50.kinkake.model;

import java.util.ArrayList;

public class Musica {
    private Long codigo;//TODO verificar se o código vai ser Long mesmo
    private String nome;
    private String letra;
    private boolean favorito;

    //TODO para remover
    public static ArrayList<Musica> getListaMusicasTeste(){
        ArrayList<Musica> musicas = new ArrayList<>();

        for(int i = 1 ; i < 21 ; i++){
            Musica musica = new Musica();
            musica.codigo = Long.parseLong(i + "000");
            musica.nome = "Nome " + i;
            musica.letra = "Teste da letra " + i;

            if(i%2 == 0)
                musica.favorito = false;
            else
                musica.favorito = true;

            musicas.add(musica);
        }

        return musicas;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
