package com.bkopellhill.gj.comisionlibre.core.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.bkopellhill.gj.comisionlibre.R;
import com.bkopellhill.gj.comisionlibre.ui.fragments.AdvancedModeFragment;
import com.bkopellhill.gj.comisionlibre.ui.fragments.BasicModeFragment;

/**
 * @author Luis Hernandez
 * @version 0.0.1
 */

public class HomeAdapter extends FragmentPagerAdapter{

    private Context context;

    public HomeAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return BasicModeFragment.newInstance();
            case 1:
                return AdvancedModeFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getString(R.string.basic_mode);
            case 1:
                return context.getString(R.string.advance_mode);
            default:
                return "";
        }
    }
}
