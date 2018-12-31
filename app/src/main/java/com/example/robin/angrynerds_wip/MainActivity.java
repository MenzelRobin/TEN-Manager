package com.example.robin.angrynerds_wip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.robin.angrynerds_wip.overview.overviewActivity.OverviewInit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickMain(View view) {
        Intent intent = new Intent(this, OverviewInit.class);
        //intent.putExtra("ID", 5); // Hier können eigene Parameter hinzugefügt werden.
        this.startActivity(intent); // Activity Starten
    }

    public void onClickEvent(View view) {
        Intent intent = new Intent(this, OverviewInit.class);
        //intent.putExtra("ID", 5); // Hier können eigene Parameter hinzugefügt werden.
        this.startActivity(intent); // Activity Starten
    }

    public void onClickTodo(View view) {
        Intent intent = new Intent(this, OverviewInit.class);
        //intent.putExtra("ID", 5); // Hier können eigene Parameter hinzugefügt werden.
        this.startActivity(intent); // Activity Starten
    }

    public void onClickNote(View view) {
        Intent intent = new Intent(this, OverviewInit.class);
        //intent.putExtra("ID", 5); // Hier können eigene Parameter hinzugefügt werden.
        this.startActivity(intent); // Activity Starten*/
    }
}
