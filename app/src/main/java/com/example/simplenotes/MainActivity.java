package com.example.simplenotes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.simplenotes.adapters.NotesAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Main screen of app; contains the list of notes. There's a FAB that you can click to create a new note.
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "ERIC";
    private static final String EXTRA_INDEX = "index";
    private static final String EXTRA_TITLE = "title";
    public static List<String> noteNames = new ArrayList<>();
    private final String EDITOR_KEY = "myNotes";
    private NotesAdapter notesAdapter;
    private SharedPreferences sharedPreferences;

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    Lifecycle/System methods
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //** Load data **********************************************
        Context applicationContext = getApplicationContext();
        String PREFERENCE_KEY = "myAppKey";
        sharedPreferences = applicationContext.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
        load();

        //** Setup toolbar ******************************************
        MaterialToolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        //** Create RecyclerView ************************************
        // Create list
        RecyclerView rV_notesList = findViewById(R.id.rV_notesList);
        notesAdapter = new NotesAdapter(this, noteNames);
        rV_notesList.setAdapter(notesAdapter);
        rV_notesList.setLayoutManager(new LinearLayoutManager(rV_notesList.getContext()));

        // Add dividers between notes
        DividerItemDecoration itemDecoration = new DividerItemDecoration(rV_notesList.getContext(),
                LinearLayout.VERTICAL);
        rV_notesList.addItemDecoration(itemDecoration);
    }

    /**
     * Updates RecyclerView whenever MainActivity is refocused
     */
    @Override
    protected void onResume() {
        super.onResume();
        notesAdapter.notifyDataSetChanged();
    }

    /**
     * Saves data when MainActivity loses focus
     */
    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    /**
     * Custom android menu
     *
     * @param menu the activity's menu
     * @return if the menu has been created
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    Touch interactions
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Creates a new note (called by FAB)
     *
     * @param view <code>View</code> that performs action
     */
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

        intent.putExtra(EXTRA_TITLE, name);
        intent.putExtra(EXTRA_INDEX, noteNames.size() - 1);
        Log.d(TAG, "Index sent to NoteActivity");
        startActivity(intent);
        Log.d(TAG, "Activity started");

        notesAdapter.notifyItemInserted(0);
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    Data Handling
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Loads ArrayList of notes from Shared Preferences
     */
    private void load() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(EDITOR_KEY, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        noteNames = gson.fromJson(json, type);

        if (noteNames == null) {
            noteNames = new ArrayList<>();
        }
    }

    /**
     * Saves ArrayList of notes to Shared Preferences
     */
    private void save() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(noteNames);
        editor.putString(EDITOR_KEY, json);
        editor.apply();
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    Getters and Setters
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static String getExtraIndex() {
        return EXTRA_INDEX;
    }

    public static String getExtraTitle() {
        return EXTRA_TITLE;
    }

    /**
     * Changes a note's name in the ArrayList
     *
     * @param text  new name
     * @param index index of note in ArrayList
     */
    public static void setNoteName(String text, int index) {
        noteNames.set(index, text);
    }
}
