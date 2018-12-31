package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.robin.angrynerds_wip.R;

import java.util.ArrayList;

import static com.example.robin.angrynerds_wip.R.color.bgColor1;

public class OverviewGui {

    private OverviewInit mActivity;

    private LinearLayout mContainer1;
    private LinearLayout mContainer2;


    OverviewGui(OverviewInit pActivity){
        mActivity = pActivity;
        mActivity.setContentView(R.layout.activity_overview);

        mContainer1 = mActivity.findViewById(R.id.id_overview_linearLayout_container1);
        mContainer2 = mActivity.findViewById(R.id.id_overview_linearLayout_container2);
    }

    public void insertFragments(ArrayList<Fragment> pFragments){
        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for(Fragment fragment : pFragments) {
            //Todo: Ziellayout variable machen irgendwie
            fragmentTransaction.add(R.id.id_overview_linearLayout_container1, fragment);
        }
        fragmentTransaction.commit();
        //Todo: Auf beide Container aufteilen @Runtime
    }
}
