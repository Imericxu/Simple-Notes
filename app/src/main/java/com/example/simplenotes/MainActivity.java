package com.example.simplenotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnNoteListener {

    private List<String> noteNames = new ArrayList<>();
    private RecyclerView rV_notesList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        //** Create RecyclerView ************************************
        // Create list
        rV_notesList = findViewById(R.id.rV_notesList);
        myAdapter = new MyAdapter(this, noteNames, this);
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
    protected void onRestart() {
        super.onRestart();
//        MyAdapter myAdapter = new MyAdapter(rV_notesList.getContext(), noteNames, this);
//        rV_notesList.setAdapter(myAdapter);
    }

    @Override
    public void onNoteClicked(int position) {
//        notes.get(position);
//        Intent intent = new Intent(this, NoteActivity.class);
//        startActivity(intent);
    }

    public void newNote(View view) {
        Intent intent = new Intent(this, NoteActivity.class);

        //** Find a new name ****************************************
        int i = 1;
        String name = "Note ";

        while (noteNames.contains(name + i)) {
            i++;
        }

        //***********************************************************

        noteNames.add(0, name += i);

        intent.putExtra("TITLE", name);
        startActivity(intent);

        myAdapter.notifyItemInserted(0);
    }
}
