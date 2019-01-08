package com.example.robin.angrynerds_wip;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.utils.MockData;
import com.example.robin.angrynerds_wip.data.repository.DatabaseManager;
import com.example.robin.angrynerds_wip.data.repository.Repository;
import com.example.robin.angrynerds_wip.overview.overviewActivity.OverviewInit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseManager.initDatabase(this.getApplicationContext());
    }


    public void onClickMain(View view) {
        Intent intent = new Intent(this, OverviewInit.class);
        //intent.putExtra("ID", 5); // Hier können eigene Parameter hinzugefügt werden.
        this.startActivity(intent); // Activity Starten
    }

    public void onClickEvent(View view) {
        Intent intent = new Intent(this, com.example.robin.angrynerds_wip.activities.event.Init.class);

        //intent.putExtra("ID", 5); // Hier können eigene Parameter hinzugefügt werden.
        this.startActivity(intent); // Activity Starten
    }

    public void onClickTodo(View view) {
        Intent intent = new Intent(this, OverviewInit.class);
        //intent.putExtra("ID", 5); // Hier können eigene Parameter hinzugefügt werden.
        this.startActivity(intent); // Activity Starten
    }

    public void onClickNote(View view) {
        Intent intent = new Intent(this, NoteActivity.class);
        //intent.putExtra("ID", 5); // Hier können eigene Parameter hinzugefügt werden.
        this.startActivity(intent); // Activity Starten*/
    }

    public void onAddTestDataToDatabase(View view) {
        Log.i("Testdata", "Testdata will be added to DB (clicked)");
        Repository repository = new Repository();


        if(DatabaseManager.getNumberOfDocuments()<15){

            MockData md = new MockData(this);
            ArrayList<TEN> tens = md.getMockData();
            for(TEN ten: tens) {
                repository.insertTEN(ten);
            }
        }
        Log.i("Testdata", "Database Content will be displayed as Strings");

        repository.getAllTENs();

        Log.i("Testdata", "Adding of Testdata is finalized.");

    }

    public void onClickResetDatabase (View view){
        Log.i("Testdata", "All Documents will be removed from the Database (clicked)");
        DatabaseManager.resetDatabase();
        Log.i("Testdata", "Removal finished.");
    }
}
