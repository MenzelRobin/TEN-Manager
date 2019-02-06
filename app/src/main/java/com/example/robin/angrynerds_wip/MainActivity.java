package com.example.robin.angrynerds_wip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.utils.Colors;
import com.example.robin.angrynerds_wip.data.models.utils.MockData;
import com.example.robin.angrynerds_wip.data.repository.DatabaseRepository;
import com.example.robin.angrynerds_wip.data.repository.DataContextManager;
import com.example.robin.angrynerds_wip.data.repository.sub_repositories.read.GetAllTensQuery;
import com.example.robin.angrynerds_wip.data.services.Delete;
import com.example.robin.angrynerds_wip.activities.overview.overviewActivity.OverviewInit;

public class MainActivity extends AppCompatActivity {
    /* Yannick-Luca Ruettgers
    Initiates Application, Only for Development.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setColor();
    }

    public void onClickSkip(View view){
        startOverview();
    }

    public void onClickReset(View view){
        Toast.makeText(getApplicationContext(), "Resetting Data...", Toast.LENGTH_SHORT).show();
        DataContextManager.initDatabase(getApplicationContext());
        resetDatabase();
        addTestData();
        startOverview();
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
        this.findViewById(R.id.id_main_linearLayout_skip).setBackgroundColor(color);
        this.findViewById(R.id.id_main_linearLayout_reset).setBackgroundColor(color);
    }
}
