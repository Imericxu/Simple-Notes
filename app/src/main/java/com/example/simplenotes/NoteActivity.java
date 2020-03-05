package com.example.simplenotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class NoteActivity extends AppCompatActivity implements RenameDialog.ExampleDialogListener {

    private String title;
    private EditText note;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_notes);
        setSupportActionBar(toolbar);

        Intent in = getIntent();
        savedInstanceState = in.getExtras();

        Log.d("Eric", "Checkpoint reached");

        if (savedInstanceState != null) {
            title = Objects.requireNonNull(savedInstanceState.get("TITLE")).toString();
            index = savedInstanceState.getInt("INDEX");
            Log.d("ERIC", "Index received");
        }

        note = findViewById(R.id.eT_note);
        setTitle(title);

        // Read file if there is one
        load();
    }

    private void load() {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = openFileInput(title);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = bufferedReader.readLine()) != null) {
                sb.append(text).append("\n");
            }

            note.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() throws IOException {
        FileOutputStream fOS = null;

        try {
            fOS = openFileOutput(title, MODE_PRIVATE);
            fOS.write(note.getText().toString().getBytes());
            Log.d("ERIC", "" + getFilesDir());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert fOS != null;
            fOS.close();
        }
    }

    public void rename(MenuItem item) {
        RenameDialog renameDialog = new RenameDialog();
        renameDialog.show(getSupportFragmentManager(), "Rename Dialog");
    }

    @Override
    public void applyText(String newName) {
        File file = new File(getFilesDir(), title);
        boolean deleted = file.delete();
        title = newName;
        setTitle(title);
        MainActivity.setNoteName(title, index);
    }
}
