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

    public String getTrueCase() {return trueCase;}

    public void setTrueCase(String trueCase) {
        this.trueCase = trueCase;
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCaseA() {
        return caseA;
    }

    public void setCaseA(String caseA) {
        this.caseA = caseA;
    }

    public String getCaseB() {
        return caseB;
    }

    public void setCaseB(String caseB) {
        this.caseB = caseB;
    }

    public String getCaseC() {
        return caseC;
    }

    public void setCaseC(String caseC) {
        this.caseC = caseC;
    }

    public String getCaseD() {
        return caseD;
    }

    public void setCaseD(String caseD) {
        this.caseD = caseD;
    }

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
