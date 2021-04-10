package com.example.latertodo.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();

    private List<String> titleList = new ArrayList<>();


    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    /**
     * 获得碎片
     */
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * 获得标题
     */
    @Override
    public CharSequence getPageTitle(int position){
        return titleList.get(position);
    }
}
