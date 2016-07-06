package com.nikhil.bookshare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ViewPager tabViews = (ViewPager)findViewById(R.id.tabViews);
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AvailableBooks());
        fragments.add(new Profile());
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if(position==0)
                    return "Available Books";
                else
                    return "Your Books";
            }
        };
        tabViews.setAdapter(fragmentPagerAdapter);
    }


}
