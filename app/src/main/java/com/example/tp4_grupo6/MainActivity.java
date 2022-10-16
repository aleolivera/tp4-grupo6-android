package com.example.tp4_grupo6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.tp4_grupo6.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=(TabLayout) findViewById(R.id.tabLayout_main);
        viewPager=(ViewPager) findViewById(R.id.viewPager_main);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.agregarFragment(new Alta(),"Alta");
        viewPagerAdapter.agregarFragment(new Modificacion(),"Modificacion");
        viewPagerAdapter.agregarFragment(new Listado(),"Listado");
        viewPager.setAdapter(viewPagerAdapter);
    }
}