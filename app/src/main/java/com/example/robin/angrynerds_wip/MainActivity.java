package com.example.robin.angrynerds_wip;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.utils.Colors;
import com.example.robin.angrynerds_wip.data.models.utils.MockData;
import com.example.robin.angrynerds_wip.data.repository.DataContextManager;
import com.example.robin.angrynerds_wip.data.repository.sub_repositories.read.GetAllTensQuery;
import com.example.robin.angrynerds_wip.data.services.Delete;
import com.example.robin.angrynerds_wip.activities.overview.overviewActivity.OverviewInit;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    /* Yannick-Luca Ruettgers
    Initiates Application, Only for Development.
     */

    boolean mLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setColor();
        setImage();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                if(!mLoading) startOverview();
            }
        }, 2000);
    }

    @Override
    public void onBackPressed(){
        mLoading = true;
        super.onBackPressed();
    }

    public void onClickSkip(View view){
        if(!mLoading) {
            mLoading = true;
            startOverview();
        }
    }

    public void onClickReset(View view){
        if(!mLoading) {
            mLoading = true;
            DataContextManager.initDatabase(getApplicationContext());
            resetDatabase();
            addTestData();
            startOverview();
        }
    }

    public void addTestData(){
        //DatabaseRepository databaseRepository = new DatabaseRepository();
        if (DataContextManager.getNumberOfDocuments() < 15) {
            MockData md = new MockData(this);
            md.addMockDataToDatabase();
        }
    }

    public void resetDatabase(){
        GetAllTensQuery getAllTensQuery = new GetAllTensQuery();
        for(TEN ten : getAllTensQuery.getAllTENs()){
            Delete.deleteTEN(ten.getID());
        }
    }

    public void startOverview(){
        Intent intent = new Intent(this, OverviewInit.class);
        this.startActivity(intent);
    }

    public void setColor(){
        int colorIndex = Colors.getRandomColorIndex();
        int color = Colors.COLORS[colorIndex];
        this.findViewById(R.id.id_main_relativeLayout_main).setBackgroundColor(color);
    }

    public void setImage(){
        int[] birbs = {R.drawable.yelowbird_scaled_transparent, R.drawable.flapybird_scaled_transparent};
        ((ImageView) this.findViewById(R.id.id_main_imageView_image)).setImageResource(birbs[new Random().nextInt(birbs.length)]);
    }
}
