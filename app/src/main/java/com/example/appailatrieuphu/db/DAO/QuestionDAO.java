package com.example.appailatrieuphu.db.DAO;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.appailatrieuphu.db.Entities.Question;

import java.util.List;

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM (SELECT * FROM (SELECT * FROM QUESTION WHERE level = 1 ORDER BY RANDOM() LIMIT 1)\n"+
            "UNION SELECT * FROM (SELECT * FROM QUESTION WHERE level = 2 ORDER BY RANDOM() LIMIT 1)\n"+
            "UNION SELECT * FROM (SELECT * FROM QUESTION WHERE level = 3 ORDER BY RANDOM() LIMIT 1)\n"+
            "UNION SELECT * FROM (SELECT * FROM QUESTION WHERE level = 4 ORDER BY RANDOM() LIMIT 1)\n"+
            "UNION SELECT * FROM (SELECT * FROM QUESTION WHERE level = 5 ORDER BY RANDOM() LIMIT 1)\n"+
            "UNION SELECT * FROM (SELECT * FROM QUESTION WHERE level = 6 ORDER BY RANDOM() LIMIT 1)\n"+
            "UNION SELECT * FROM (SELECT * FROM QUESTION WHERE level = 7 ORDER BY RANDOM() LIMIT 1)\n"+
            "UNION SELECT * FROM (SELECT * FROM QUESTION WHERE level = 8 ORDER BY RANDOM() LIMIT 1)\n"+
            "UNION SELECT * FROM (SELECT * FROM QUESTION WHERE level = 9 ORDER BY RANDOM() LIMIT 1)\n"+
            "UNION SELECT * FROM (SELECT * FROM QUESTION WHERE level = 10 ORDER BY RANDOM() LIMIT 1)\n"+
            "UNION SELECT * FROM (SELECT * FROM QUESTION WHERE level = 11 ORDER BY RANDOM() LIMIT 1)\n"+
            "UNION SELECT * FROM (SELECT * FROM QUESTION WHERE level = 12 ORDER BY RANDOM() LIMIT 1)\n"+
            "UNION SELECT * FROM (SELECT * FROM QUESTION WHERE level = 13 ORDER BY RANDOM() LIMIT 1)\n"+
            "UNION SELECT * FROM (SELECT * FROM QUESTION WHERE level = 14 ORDER BY RANDOM() LIMIT 1)\n"+
            "UNION SELECT * FROM (SELECT * FROM QUESTION WHERE level = 15 ORDER BY RANDOM() LIMIT 1)\n"+
            ")ORDER BY level ASC")
    List<Question> getListQuestion();

    @Query("SELECT * FROM Question WHERE level = :level ORDER BY RANDOM() LIMIT 1")
    List<Question> getQuestionByLevel(int level);
}
