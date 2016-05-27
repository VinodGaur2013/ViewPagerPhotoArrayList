package com.viewpagerphotoarraylist;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Vinod on 09-May-16.
 */
public class Pager_Adapter extends FragmentStatePagerAdapter {

    private ArrayList<Integer> adapterList;
    private Context context;


    public Pager_Adapter(FragmentManager fm, ArrayList<Integer> imagesList) {
        super(fm);
        this.adapterList = imagesList;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.getInstance(adapterList.get(position));
    }

    @Override
    public int getCount() {
        return adapterList.size();
    }


    @Override
    public void destroyItem(View container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}