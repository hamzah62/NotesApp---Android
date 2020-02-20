package com.hhx.notepadapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {
    @Query("SELECT * FROM Notes")
     List<Note> getAll();

    @Insert
     void insert(Note note);

    @Update
     void update(Note note);

    @Delete
     void delete(Note note);

    @Query("SELECT * FROM Notes WHERE id=:id LIMIT 1")
     Note getNote(int id);

    @Query("DELETE FROM Notes WHERE id=:id")
    void deleteById(int id);
}
