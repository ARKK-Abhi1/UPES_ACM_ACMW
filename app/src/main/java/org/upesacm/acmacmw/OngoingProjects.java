package org.upesacm.acmacmw;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class OngoingProjects extends AppCompatActivity {


    FragmentManager fragmentManager;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_projects);

        fragmentManager=getSupportFragmentManager();


        frameLayout=findViewById(R.id.frame_layout);

        FragmentTransaction ft=fragmentManager.beginTransaction();
        ft.replace(R.id.frameLayout,OngoinProjectFragment.newInstance(fragmentManager),"ongoingprojectfragment");
        ft.commit();

    }
}
