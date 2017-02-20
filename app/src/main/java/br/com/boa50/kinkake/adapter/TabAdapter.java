package br.com.boa50.kinkake.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.boa50.kinkake.fragment.CantoresFragment;
import br.com.boa50.kinkake.fragment.MusicasFragment;

public class TabAdapter extends FragmentStatePagerAdapter{

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position) {
            case 0:
                return new CantoresFragment();
            case 1:
                return new MusicasFragment();
            default:
                return new CantoresFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
