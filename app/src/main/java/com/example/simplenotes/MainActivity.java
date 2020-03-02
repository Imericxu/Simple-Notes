package com.example.simplenotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnNoteListener {

    private RecyclerView rV_notesList;
    private String[] testStrings = {"Title1", "Title2", "Title3", "Title4", "Title5", "Title6", "Title7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set toolbar
        MaterialToolbar toolbar = findViewById(R.id.mT_toolbar);
        setSupportActionBar(toolbar);

        // Create notes list
        rV_notesList = findViewById(R.id.rV_notesList);
        MyAdapter myAdapter = new MyAdapter(this, testStrings, this);
        rV_notesList.setAdapter(myAdapter);
        rV_notesList.setLayoutManager(new LinearLayoutManager(rV_notesList.getContext()));

        // Add dividers between notes
        DividerItemDecoration itemDecoration = new DividerItemDecoration(rV_notesList.getContext(),
                LinearLayout.VERTICAL);
        rV_notesList.addItemDecoration(itemDecoration);
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
}
