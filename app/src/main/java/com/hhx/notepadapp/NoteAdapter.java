package com.hhx.notepadapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ArrayAdapter {

    public NoteAdapter(Activity ctx, ArrayList<Note> notes){
        super(ctx,0,notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if(item==null){
            item= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        final Note currentNote=(Note)getItem(position);

        TextView itemTextView= (TextView) item.findViewById(R.id.list_item);

        itemTextView.setText(currentNote.getNoteTitle());
        final int id = currentNote.getId();

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReadNoteActivity.class);
                intent.putExtra("id",id);
                getContext().startActivity(intent);
            }
        });

        return item;
    }

}
