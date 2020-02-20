package com.hhx.notepadapp;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient dbClient;

    private Database db;

    private DatabaseClient(Context ctx){
        mCtx=ctx;

        db= Room.databaseBuilder(mCtx,Database.class,"NotepadAppDB").build();
    }

    public static synchronized DatabaseClient getDatabaseInstance(Context ctx){
        if(dbClient==null){
            dbClient=new DatabaseClient(ctx);
        }
        return dbClient;
    }

    public Database getDatabase(){
        return db;
    }
}
