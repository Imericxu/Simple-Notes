package com.example.simplenotes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rV_notesList;
    private String[] testStrings = {"Title1", "Title2", "Title3", "Title4", "Title5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.mT_toolbar);
        setSupportActionBar(toolbar);

        rV_notesList = findViewById(R.id.rV_notesList);
        MyAdapter myAdapter = new MyAdapter(this, testStrings);
        rV_notesList.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
}
