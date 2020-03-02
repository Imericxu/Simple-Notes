package com.example.simplenotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnNoteListener {

    private RecyclerView rV_notesList;
    private ArrayList<String> testStrings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        //** Create notes list **************************************
        // Set testStrings
        for (int i = 0; i < 12; i++) {
            testStrings.add("Title " + (i + 1));
        }

        // Create list
        rV_notesList = findViewById(R.id.rV_notesList);
        MyAdapter myAdapter = new MyAdapter(this, testStrings.toArray(new String[0]), this);
        rV_notesList.setAdapter(myAdapter);
        rV_notesList.setLayoutManager(new LinearLayoutManager(rV_notesList.getContext()));

        // Add dividers between notes
        DividerItemDecoration itemDecoration = new DividerItemDecoration(rV_notesList.getContext(),
                LinearLayout.VERTICAL);
        rV_notesList.addItemDecoration(itemDecoration);

        //** Fab ****************************************************
        FloatingActionButton fab = findViewById(R.id.fAB_newNote);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onNoteClicked(int position) {
//        notes.get(position);
//        Intent intent = new Intent(this, NoteActivity.class);
//        startActivity(intent);
    }

    public void newNote(View view) {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }
}
