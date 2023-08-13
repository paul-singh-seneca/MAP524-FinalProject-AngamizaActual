package com.osepoo.angamizaactual.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.osepoo.angamizaactual.utils.CarSearchFragment;
import com.osepoo.angamizaactual.utils.PersonSearchFragment;

public class ViewPagerAdapterSearch extends FragmentPagerAdapter {

    public ViewPagerAdapterSearch(
            @NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        if (position == 0)
            fragment = new PersonSearchFragment();
        else if (position == 1)
            fragment = new CarSearchFragment();


        return fragment;
    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "Person Search";
        else if (position == 1)
            title = "Car Search";
        return title;
    }
}
