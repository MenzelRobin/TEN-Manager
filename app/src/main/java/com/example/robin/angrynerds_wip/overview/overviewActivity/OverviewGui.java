package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.robin.angrynerds_wip.R;

import static com.example.robin.angrynerds_wip.R.color.bgColor1;

public class OverviewGui {

    private OverviewInit mActivity;

    private LinearLayout mContainer1;
    private LinearLayout mContainer2;

    private TextView mTextView;


    OverviewGui(OverviewInit pActivity){
        mActivity = pActivity;
        mActivity.setContentView(R.layout.activity_overview);

        mContainer1 = mActivity.findViewById(R.id.id_overview_linearLayout_container1);
        mContainer2 = mActivity.findViewById(R.id.id_overview_linearLayout_container2);

        mTextView = mActivity.findViewById(R.id.textView);
    }

    //TODO: Löschen
    public void setmTextView(String pText){
        mTextView.setText(pText);
    }
}
