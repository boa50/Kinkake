package br.com.boa50.kinkake.util;

public class FiltroUtil {
    private static boolean favoritoFiltro;

    public static boolean isFavoritoFiltro() {
        return favoritoFiltro;
    }

    public static void setFavoritoFiltro(boolean favoritoFiltro) {
        FiltroUtil.favoritoFiltro = favoritoFiltro;
    }
}
