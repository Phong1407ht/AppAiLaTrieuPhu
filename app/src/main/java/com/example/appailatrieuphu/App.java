package com.example.appailatrieuphu;

import android.app.Application;

import androidx.room.Room;

import com.example.appailatrieuphu.db.AppDB;


public class App extends Application {
    private static App instance;
    private com.example.appailatrieuphu.Storage storage;
    private AppDB db;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        storage = new com.example.appailatrieuphu.Storage();
        db = Room.databaseBuilder(this, AppDB.class, "Question").createFromAsset("db/Question.db")
                .build();
    }

    public AppDB getDb() {
        return db;
    }

    public com.example.appailatrieuphu.Storage getStorage() {
        return storage;
    }
}
