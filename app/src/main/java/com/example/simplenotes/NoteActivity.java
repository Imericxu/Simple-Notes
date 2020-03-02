package com.example.simplenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.material.appbar.MaterialToolbar;

public class NoteActivity extends AppCompatActivity {
    private String noteName = "Placeholder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_notes);
        setSupportActionBar(toolbar);
        setTitle(noteName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu, menu);
        return true;
    }
}
