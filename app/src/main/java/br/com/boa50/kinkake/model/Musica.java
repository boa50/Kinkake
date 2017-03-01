package br.com.boa50.kinkake.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Musica implements Parcelable {
    private Integer codigo;
    private String nome;
    private String letra;
    private String cantor;
    private boolean favorito;

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

    public String getCantor() {
        return cantor;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    protected Musica(Parcel in) {
        codigo = in.readInt();
        nome = in.readString();
        letra = in.readString();
        cantor = in .readString();
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
        parcel.writeString(cantor);
        parcel.writeByte((byte) (favorito ? 1 : 0));
    }
}
