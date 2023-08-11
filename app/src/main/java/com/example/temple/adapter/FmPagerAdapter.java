package com.example.temple.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FmPagerAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> mFragments;

    public FmPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    public FmPagerAdapter(@NonNull @NotNull FragmentManager fm, int behavior, List<Fragment> mFragments) {
        super(fm, behavior);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


}
