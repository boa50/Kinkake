package br.com.boa50.kinkake.model;

public class MusicaSelecao {
    private Musica musica;
    private boolean selecao;

    public MusicaSelecao(){}

    public MusicaSelecao(Musica musica){
        this.musica = musica;
        this.selecao = false;
    }

    public MusicaSelecao(Musica musica, boolean selecao){
        this.musica = musica;
        this.selecao = selecao;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public boolean isSelecao() {
        return selecao;
    }

    public void setSelecao(boolean selecao) {
        this.selecao = selecao;
    }
}
