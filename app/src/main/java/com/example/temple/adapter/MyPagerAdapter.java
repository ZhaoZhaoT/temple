package com.example.temple.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private String[] mTitles;

    public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.fragments=list;
    }

    public MyPagerAdapter(@NonNull @NotNull FragmentManager fm, int behavior, ArrayList<Fragment> fragments) {
        super(fm, behavior);
        this.fragments = fragments;
    }

    public void setmTitles(String[] mTitles) {
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
