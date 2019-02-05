package com.example.robin.angrynerds_wip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.utils.MockData;
import com.example.robin.angrynerds_wip.data.repository.DatabaseRepository;
import com.example.robin.angrynerds_wip.data.repository.DataContextManager;
import com.example.robin.angrynerds_wip.data.repository.sub_repositories.read.GetAllTensQuery;
import com.example.robin.angrynerds_wip.data.services.Delete;
import com.example.robin.angrynerds_wip.activities.overview.overviewActivity.OverviewInit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataContextManager.initDatabase(this.getApplicationContext());
    }


    public void onClickMain(View view) {
        Intent intent = new Intent(this, OverviewInit.class);
        //intent.putExtra("ID", 5); // Hier können eigene Parameter hinzugefügt werden.
        this.startActivity(intent); // Activity Starten
    }

    public void onClickEvent(View view) {
        Intent intent = new Intent(this, com.example.robin.angrynerds_wip.activities.event.Init.class);

        intent.putExtra("ID", "7e08f7b4-1afc-4562-9b70-21c1c6ccbb01"); // Hier können eigene Parameter hinzugefügt werden.
        this.startActivity(intent); // Activity Starten
    }

    public void onClickTodo(View view) {
        Intent intent = new Intent(this, com.example.robin.angrynerds_wip.activities.todo.Init.class);
                //intent.putExtra("ID", 5); // Hier können eigene Parameter hinzugefügt werden.
        this.startActivity(intent); // Activity Starten
    }

    public void onClickNote(View view) {
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("ID", "bb741ab0-c636-4218-b188-3246d8ac4cbd");
        this.startActivity(intent);
    }

    public void onAddTestDataToDatabase(View view) {
        Log.i("Testdata", "Testdata will be added to DB (clicked)");
        DatabaseRepository databaseRepository = new DatabaseRepository();


        if (DataContextManager.getNumberOfDocuments() < 15) {

            MockData md = new MockData(this);
            md.addMockDataToDatabase();
        }
        Log.i("Testdata", "Database Content will be displayed as Strings");

        databaseRepository.getAllTENs();

        Log.i("Testdata", "Adding of Testdata is finalized.");

    }

    public void onClickResetDatabase(View view) {
        Log.i("Testdata", "All Documents will be removed from the Database (clicked)");
        GetAllTensQuery getAllTensQuery = new GetAllTensQuery();
        for(TEN ten : getAllTensQuery.getAllTENs()){
            Delete.deleteTEN(ten.getID());
        }
        Log.i("Testdata", "Removal finished.");
    }
}
