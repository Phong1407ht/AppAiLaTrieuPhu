package com.example.appailatrieuphu.viewmodel;


import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.example.appailatrieuphu.db.Entities.Question;

import java.util.List;

public class M003PlayVM extends BaseViewModel {
    private final MutableLiveData<Question> ansA = new MutableLiveData<>();
    private final MutableLiveData<Question> ansB = new MutableLiveData<>();
    private final MutableLiveData<Question> ansC = new MutableLiveData<>();
    private final MutableLiveData<Question> ansD = new MutableLiveData<>();
    private final MutableLiveData<Question> trueAns = new MutableLiveData<>();
    private final MutableLiveData<Integer> time = new MutableLiveData<>(30);
    private final MutableLiveData<Question> question = new MutableLiveData<>();
    private int answer;
    private Thread thread;

    public MutableLiveData<Question> getAnsA() {
        return ansA;
    }

    public MutableLiveData<Question> getAnsB() {
        return ansB;
    }

    public MutableLiveData<Question> getAnsC() {
        return ansC;
    }

    public MutableLiveData<Question> getAnsD() {
        return ansD;
    }

    public MutableLiveData<Question> getTrueAns() {
        return trueAns;
    }

    public MutableLiveData<Question> getQuestion() {return question;}

    public MutableLiveData<Integer> getTime() {
        return time;
    }

    public int getAnswer() {return answer;}

    public void setAnswer(int answer) {this.answer = answer;}

    public Thread getThread() {return thread;}

    public boolean checkAnswer(Question trueAns) {
        int intAns = Integer.parseInt(trueAns.trueCase);
        if(intAns == this.answer){
            return true;
        }
        return false;
    }

    public void gameOver() {
        thread.interrupt();
        time.postValue(0);
    }

    public void countDownTime() {
        if (!thread.isAlive() || thread == null) {
            thread = new Thread(() -> {
                while (time.getValue() != null && time.getValue() > 0) {
                    try {
                        Thread.sleep(1000);
                        time.postValue(time.getValue() - 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            });
            thread.start();
        }
    }
}
