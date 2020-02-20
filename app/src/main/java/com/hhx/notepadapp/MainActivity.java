package com.hhx.notepadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_add)
                .setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this,AddNoteActivity.class));
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getAllNotes();
    }

    public void getAllNotes(){
        final ListView listview=(ListView) findViewById(R.id.items_list);

        class getNotes extends AsyncTask<Void,Void, ArrayList<Note>>{
            @Override
            protected ArrayList<Note> doInBackground(Void... voids) {

                ArrayList<Note> notes = new ArrayList<>( DatabaseClient
                        .getDatabaseInstance(getApplicationContext())
                        .getDatabase()
                        .NoteDao()
                        .getAll());

                return notes;
            }

            @Override
            protected void onPostExecute(ArrayList<Note> notes) {
                super.onPostExecute(notes);
                NoteAdapter adapter = new NoteAdapter(MainActivity.this,notes);
                listview.setAdapter(adapter);
            }
        }

        getNotes gn = new getNotes();
        gn.execute();
    }
}
