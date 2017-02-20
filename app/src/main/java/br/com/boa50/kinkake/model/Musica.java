package br.com.boa50.kinkake.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.boa50.kinkake.util.MusicaUtil;

public class Musica implements Parcelable {
    private Integer codigo;//TODO verificar se o código vai ser Long mesmo
    private String nome;
    private String letra;
    private boolean favorito;

    private static final String LETRA_TESTE =
            "Esse meu jeito de viver\n" +
            "Ninguém nunca foi igual\n" +
            "A minha vida é fazer\n" +
            "O bem vencer o mal\n" +
            "\n" +
            "Pelo mundo viajarei\n" +
            "Tentando encontrar\n" +
            "Um Pokémon e com o seu poder\n" +
            "Tudo transformar\n" +
            "\n" +
            "Pokémon! (Temos que pegá-los)\n" +
            "Isso eu sei\n" +
            "Pegá-los eu tentarei! (Pokémon!)\n" +
            "Juntos teremos que\n" +
            "O mundo defender!\n" +
            "\n" +
            "Pokémon! (Temos que pegá-los)\n" +
            "Isso eu sei\n" +
            "Pegá-los eu tentarei\n" +
            "Vai ser grande a emoção\n" +
            "Pokémon!\n" +
            "\n" +
            "Temos que pegar\n" +
            "Temos que pegar!\n" +
            "\n" +
            "Desafios vou encontrar\n" +
            "E os enfrentarei\n" +
            "Lutando pelo meu lugar\n" +
            "Todo dia estarei!\n" +
            "\n" +
            "Vem comigo, vamos formar\n" +
            "Sempre a melhor equipe\n" +
            "E sempre juntos vamos vencer\n" +
            "O sonho é poder\n" +
            "\n" +
            "Pokémon! (Temos que pegá-los)\n" +
            "Isso eu sei\n" +
            "Pegá-los eu tentarei! (Pokémon!)\n" +
            "Juntos teremos que\n" +
            "O mundo defender!\n" +
            "\n" +
            "Pokémon! (Temos que pegá-los)\n" +
            "Isso eu sei\n" +
            "Pegá-los eu tentarei!\n" +
            "Vai ser grande a emoção! Pokémon!\n" +
            "(Temos que pegar)\n" +
            "Temos que pegar\n" +
            "\n" +
            "Temos que pegar\n" +
            "Temos que pegar\n" +
            "\n" +
            "Pokémon! (Temos que pegá-los)\n" +
            "Isso eu sei\n" +
            "Pegá-los eu tentarei! (Pokémon!)\n" +
            "Juntos teremos que\n" +
            "O mundo defender!\n" +
            "\n" +
            "Pokémon! (Temos que pegá-los)\n" +
            "Isso eu sei\n" +
            "Pegá-los eu tentarei!\n" +
            "Vai ser grande a emoção!\n" +
            "Pokémon!\n" +
            "Temos que pegar\n" +
            "(Temos que pegar)\n" +
            "Temos que pegar\n" +
            "\n" +
            "Pokémon!";

    //TODO para remover
    public static ArrayList<Musica> getListaMusicasTeste(){
        ArrayList<Musica> musicas = new ArrayList<>();

        for(int i = 1 ; i < 21 ; i++){
            Musica musica = new Musica();
            musica.codigo = i;
            musica.nome = "Nome " + i;
            musica.letra = LETRA_TESTE;

            if(i%2 == 0)
                musica.favorito = false;
            else
                musica.favorito = true;

            musicas.add(musica);
        }

        return MusicaUtil.ordenaMusicasPorNome(musicas);
    }

    public Musica (){}

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
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

    protected Musica(Parcel in) {
        codigo = in.readInt();
        nome = in.readString();
        letra = in.readString();
        favorito = in.readByte() != 0;
    }

    public static final Creator<Musica> CREATOR = new Creator<Musica>() {
        @Override
        public Musica createFromParcel(Parcel in) {
            return new Musica(in);
        }

        @Override
        public Musica[] newArray(int size) {
            return new Musica[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(codigo);
        parcel.writeString(nome);
        parcel.writeString(letra);
        parcel.writeByte((byte) (favorito ? 1 : 0));
    }
}
