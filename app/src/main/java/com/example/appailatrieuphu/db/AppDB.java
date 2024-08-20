package com.example.appailatrieuphu.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.appailatrieuphu.db.DAO.QuestionDAO;
import com.example.appailatrieuphu.db.Entities.Question;

@Database(entities = {Question.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract QuestionDAO getDAO();

}
