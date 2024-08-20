package com.example.appailatrieuphu.db.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Question {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    @NotNull
    public String id;
    @ColumnInfo(name = "question")
    public String question;
    @ColumnInfo(name = "level")
    public int level;
    @ColumnInfo(name = "casea")
    public String caseA;
    @ColumnInfo(name = "caseb")
    public String caseB;
    @ColumnInfo(name = "casec")
    public String caseC;
    @ColumnInfo(name = "cased")
    public String caseD;
    @ColumnInfo(name = "truecase")
    public String trueCase;

    public Question(String caseA, String caseB, String caseC, String caseD, @NotNull String id, int level, String question, String trueCase) {
        this.caseA = caseA;
        this.caseB = caseB;
        this.caseC = caseC;
        this.caseD = caseD;
        this.id = id;
        this.level = level;
        this.question = question;
        this.trueCase = trueCase;
    }

    @Override
    public String toString() {
        return "Question{" +
                ", id='" + id + '\'' +
                ", question='" + question + '\'' +
                ", level=" + level +
                "caseA='" + caseA + '\'' +
                ", caseB='" + caseB + '\'' +
                ", caseC='" + caseC + '\'' +
                ", caseD='" + caseD + '\'' +
                ", trueCase='" + trueCase + '\'' +
                '}';
    }
}
