package br.com.boa50.kinkake.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.fragment.CantoresFragment;
import br.com.boa50.kinkake.fragment.MusicasFragment;
import br.com.boa50.kinkake.fragment.MusicasReservadasFragment;

public class TabAdapter extends FragmentStatePagerAdapter{
    private Context context;
    private int[] icones = new int[]{R.drawable.ic_action_mic, R.drawable.ic_action_music_1, R.drawable.ic_people};
    private int tamanhoIcone;
    private CantoresFragment cantoresFragment;
    private MusicasFragment musicasFragment;
    private MusicasReservadasFragment musicasReservadasFragment;

    public TabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        cantoresFragment = new CantoresFragment();
        musicasFragment = new MusicasFragment();
        musicasReservadasFragment = new MusicasReservadasFragment();
        double escala = this.context.getResources().getDisplayMetrics().density;
        tamanhoIcone = (int) (32 * escala);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position) {
            case 0:
                return cantoresFragment;
            case 1:
                return musicasFragment;
            case 2:
                return musicasReservadasFragment;
            default:
                return cantoresFragment;
        }

    }

    @Override
    public int getCount() {
        return icones.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable = ContextCompat.getDrawable(this.context, icones[position]);
        drawable.setBounds(0,0,tamanhoIcone,tamanhoIcone);

        ImageSpan imageSpan = new ImageSpan(drawable);

        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    public String getNomeFragment(int position){
        switch(position) {
            case 0:
                return "cantoresFragment";
            case 1:
                return "musicasFragment";
            case 2:
                return "musicasReservadasFragment";
            default:
                return "cantoresFragment";
        }
    }
}
