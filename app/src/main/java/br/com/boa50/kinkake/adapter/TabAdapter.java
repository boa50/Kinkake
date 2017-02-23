package br.com.boa50.kinkake.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.boa50.kinkake.fragment.CantoresFragment;
import br.com.boa50.kinkake.fragment.MusicasFragment;

public class TabAdapter extends FragmentStatePagerAdapter{

    private CantoresFragment cantoresFragment;
    private MusicasFragment musicasFragment;

    public TabAdapter(FragmentManager fm) {
        super(fm);
        cantoresFragment = new CantoresFragment();
        musicasFragment = new MusicasFragment();
    }

    @Override
    public Fragment getItem(int position) {

        switch(position) {
            case 0:
                return cantoresFragment;
            case 1:
                return musicasFragment;
            default:
                return cantoresFragment;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
