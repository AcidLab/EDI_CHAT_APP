package co.acidlabs.lanala.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import co.acidlabs.lanala.MainActivity;
import co.acidlabs.lanala.fragments.BalanceFragment;
import co.acidlabs.lanala.fragments.HistoryFragment;
import co.acidlabs.lanala.fragments.ImpayedFragment;
import co.acidlabs.lanala.fragments.PlaceholderFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if (position == 0) {
            return new BalanceFragment();
        }

        if (position == 1) {
            return  new ImpayedFragment();
        }

        if (position == 2) {
            return  new HistoryFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}
