package com.example.simplenotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplenotes.dialogs.RenameDialog;
import com.example.simplenotes.dialogs.TextAnalysisDialog;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity implements RenameDialog.ExampleDialogListener {

    private String title;
    private EditText note;
    private int index;

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    Lifecycle/System methods
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_notes);
        setSupportActionBar(toolbar);

        Intent in = getIntent();
        savedInstanceState = in.getExtras();

        Log.d(MainActivity.TAG, "Checkpoint reached");

        if (savedInstanceState != null) {
            title = savedInstanceState.getString(MainActivity.getExtraTitle());
            index = savedInstanceState.getInt(MainActivity.getExtraIndex());
            Log.d(MainActivity.TAG, "Index received");
        }

        note = findViewById(R.id.eT_note);
        setTitle(title);

        // Read file if there is one
        load();
    }

    /**
     * Saves note to respective txt file
     */
    @Override
    protected void onPause() {
        super.onPause();

        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Custom options menu
     *
     * @param menu NoteActivity's menu
     * @return if the menu was created
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu, menu);
        return true;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    Data handling
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Loads text from respective .txt file
     */
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

    /**
     * Saves text to respective .txt file
     *
     * @throws IOException
     */
    private void save() throws IOException {
        FileOutputStream fOS = null;

        try {
            fOS = openFileOutput(title, MODE_PRIVATE);
            fOS.write(note.getText().toString().getBytes());
            Log.d(MainActivity.TAG, "" + getFilesDir());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert fOS != null;
            fOS.close();
        }
    }

    @Override
    public void applyText(String newName) {
        File file = new File(getFilesDir(), title);
        boolean deleted = file.delete();
        title = newName;
        setTitle(title);
        MainActivity.setNoteName(title, index);
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    Touch interactions
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Called by menuItem_rename
     */
    public void rename(MenuItem item) {
        RenameDialog renameDialog = new RenameDialog();
        renameDialog.show(getSupportFragmentManager(), "Rename Dialog");
    }

    /**
     * Called by menuItem_analyze
     */
    public void analyze(MenuItem item) {
        String text = note.getText().toString();
        int words;
        int sentences;

        String[] noWhiteSpaceArray = text.toLowerCase().split("\\s+");
        words = noWhiteSpaceArray.length;
        List<String> sentenceArray = new ArrayList<>();
        String[] dontCountList = {"mr.", "mrs.", "ms.", "eg.", "ig."};

        for (String str : noWhiteSpaceArray) {
            if (str.matches("(.*)[.?!](.*)")) {
                boolean doAdd = true;
                for (String abbrev : dontCountList) {
                    if (str.contains(abbrev)) {
                        doAdd = false;
                        break;
                    }
                }
                if (doAdd) {
                    sentenceArray.add(str);
                }
            }
        }

        sentences = sentenceArray.size();

        TextAnalysisDialog analysisDialog = new TextAnalysisDialog(words, sentences);
        analysisDialog.show(getSupportFragmentManager(), "Analysis Dialog");
    }
}
