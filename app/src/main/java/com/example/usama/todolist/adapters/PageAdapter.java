package com.example.usama.todolist.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.usama.todolist.fragments.TabFragment1;
import com.example.usama.todolist.fragments.TabFragment2;
import com.example.usama.todolist.fragments.TabFragment3;

/**
 * Created by Usama on 11/7/2017.
 */

public class PageAdapter extends FragmentStatePagerAdapter{
    int numOfTabs;
    public PageAdapter(FragmentManager fm,int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                TabFragment1 tab1 = new TabFragment1();
                return tab1;

            case 1:
                TabFragment2 tab2 = new TabFragment2();
                return tab2;

            case 2:
                TabFragment3 tab3 = new TabFragment3();
                return tab3;

            default:
               return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
