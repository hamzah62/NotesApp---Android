package com.hhx.notepadapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String noteTitle;

    @ColumnInfo
    private String noteContent;

    public void setId(int id){
        this.id=id;
    }

    public int getId(){
        return id;
    }

    public void setNoteTitle(String title){
        noteTitle=title;
    }

    public String getNoteTitle(){
        return noteTitle;
    }

    public void setNoteContent(String content){
        noteContent=content;
    }

    public String getNoteContent(){
        return noteContent;
    }
}
