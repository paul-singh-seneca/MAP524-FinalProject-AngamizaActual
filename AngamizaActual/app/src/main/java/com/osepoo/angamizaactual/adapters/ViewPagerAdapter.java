package com.osepoo.angamizaactual.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.osepoo.angamizaactual.utils.CarLogFragment;
import com.osepoo.angamizaactual.utils.PersonLogFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(
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
            fragment = new PersonLogFragment();
        else if (position == 1)
            fragment = new CarLogFragment();


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
            title = "Person Log";
        else if (position == 1)
            title = "Car Log";
        return title;
    }
}
