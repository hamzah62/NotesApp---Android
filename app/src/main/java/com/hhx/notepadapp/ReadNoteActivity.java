package com.hhx.notepadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ReadNoteActivity extends AppCompatActivity {
    TextView title,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);

        title=findViewById(R.id.noteTitle);
        content=findViewById(R.id.noteContent);

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNote();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getNote();
    }

    public void getNote(){
        class Get extends AsyncTask<Void,Void,Note>{
            @Override
            protected Note doInBackground(Void... voids) {
                Intent i = getIntent();
                int id = i.getIntExtra("id",0);

                Note note = DatabaseClient.getDatabaseInstance(getApplicationContext())
                        .getDatabase()
                        .NoteDao()
                        .getNote(id);
                return note;
            }

            @Override
            protected void onPostExecute(Note note) {
                super.onPostExecute(note);
                if(note != null){
                title.setText(note.getNoteTitle());
                content.setText(note.getNoteContent());
                }
            }
        }
        Get get = new Get();
        get.execute();
    }

    public void deleteNote(){
        class delete extends AsyncTask<Void,Void,Boolean>{
            @Override
            protected Boolean doInBackground(Void... voids) {
                int id = getIntent().getIntExtra("id",-1);
                if(id!=-1){
                    DatabaseClient.getDatabaseInstance(getApplicationContext()).getDatabase()
                            .NoteDao().deleteById(id);
                    return true;
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean b) {
                super.onPostExecute(b);
                if(b){
                    Toast.makeText(getBaseContext(),"Deleted!",Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(ReadNoteActivity.this,MainActivity.class));
                }
                else
                {
                    Toast.makeText(getBaseContext(),"Could not delete!",Toast.LENGTH_LONG).show();
                }
            }
        }
        delete del = new delete();
        del.execute();
    }
}
