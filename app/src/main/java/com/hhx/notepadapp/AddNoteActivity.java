package com.hhx.notepadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    private EditText titleView,contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleView=(EditText) findViewById(R.id.noteTitle);
        contentView=(EditText) findViewById(R.id.noteContent);

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTask();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void AddTask(){
        final String title = titleView.getText().toString();
        final String content = contentView.getText().toString();

        class Add extends AsyncTask<Void,Void,Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                Note note = new Note();
                note.setNoteContent(content);
                note.setNoteTitle(title);

                DatabaseClient.getDatabaseInstance(getApplicationContext())
                        .getDatabase()
                        .NoteDao()
                        .insert(note);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(AddNoteActivity.this,MainActivity.class));
            }
        }

        Add add = new Add();
        add.execute();
    }
}
