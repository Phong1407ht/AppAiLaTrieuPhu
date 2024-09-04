package com.example.appailatrieuphu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appailatrieuphu.App;
import com.example.appailatrieuphu.db.Entities.Question;

import java.util.List;

public class M003PlayVM extends BaseViewModel {
    private final MutableLiveData<Integer> money = new MutableLiveData<>();
    private final MutableLiveData<Integer> time = new MutableLiveData<>(30);
    private LiveData<List<Question>> listQuestion;
    private int answer;
    private Thread thread;

    public MutableLiveData<Integer> getMoney() {return money;}

    public MutableLiveData<Integer> getTime() {return time;}

    public int getAnswer() {return answer;}

    public void setAnswer(int answer) {this.answer = answer;}

    public Thread getThread() {return thread;}

     public LiveData<List<Question>> getListQuestion(){
        listQuestion = App.getInstance().getDb().getDAO().getListQuestion();
        return listQuestion;
    }

    public boolean checkAnswer(Question trueAns) {
        int intAns = Integer.parseInt(trueAns.getTrueCase());
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
