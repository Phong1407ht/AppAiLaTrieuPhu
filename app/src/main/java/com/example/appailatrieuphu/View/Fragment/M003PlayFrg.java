package com.example.appailatrieuphu.View.Fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.util.Log;
import android.view.View;


import com.example.appailatrieuphu.R;
import com.example.appailatrieuphu.databinding.M003PlayFrgBinding;
import com.example.appailatrieuphu.db.Entities.Question;
import com.example.appailatrieuphu.viewmodel.M003PlayVM;

import java.util.List;


public class M003PlayFrg extends BaseFragment<M003PlayFrgBinding, M003PlayVM> {
    public static final String TAG = M003PlayFrg.class.getName();
    public Question currentQuestion;
    @Override
    protected void initView() {
        binding.tvChooseA.setOnClickListener(this);
        binding.tvChooseB.setOnClickListener(this);
        binding.tvChooseC.setOnClickListener(this);
        binding.tvChooseD.setOnClickListener(this);

        viewmodel.getListQuestion().observe(this, this::fetchQuestions);
    }

    private void fetchQuestions(List<Question> questions) {
        currentQuestion = questions.get(0);
        Log.i(TAG,"listQuestion" + questions);
        binding.tvChooseA.setText(currentQuestion.getCaseA());
        binding.tvChooseB.setText(currentQuestion.getCaseB());
        binding.tvChooseC.setText(currentQuestion.getCaseC());
        binding.tvChooseD.setText(currentQuestion.getCaseD());
        binding.imgQuestion.setText(currentQuestion.getQuestion());
    }

    @Override
    protected M003PlayFrgBinding initViewBinding(View v) {
        return M003PlayFrgBinding.bind(v);
    }

    @Override
    protected Class<M003PlayVM> initViewModel() {
        return M003PlayVM.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m003_play_frg;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void clickView(View v) {
        switch (v.getId()) {
            case R.id.tv_choose_a:
                viewmodel.setAnswer(1);
                showTrueAns(v);
                break;
            case R.id.tv_choose_b:
                viewmodel.setAnswer(2);
                showTrueAns(v);
                break;
            case R.id.tv_choose_c:
                viewmodel.setAnswer(3);
                showTrueAns(v);
                break;
            case R.id.tv_choose_d:
                viewmodel.setAnswer(4);
                showTrueAns(v);
                break;
        }
    }

    public void showTrueAns(final View view) {
        new Handler().postDelayed(() -> {
            if (viewmodel.checkAnswer(currentQuestion)) {
                if (view.getId() == R.id.tv_choose_a || view.getId() == R.id.tv_choose_c) {
                    view.setBackgroundResource(R.drawable.ic_true_l);
                }
                if (view.getId() == R.id.tv_choose_b || view.getId() == R.id.tv_choose_d) {
                    view.setBackgroundResource(R.drawable.ic_true_r);
                }
            } else {
                if (view.getId() == R.id.tv_choose_a || view.getId() == R.id.tv_choose_c) {
                    view.setBackgroundResource(R.drawable.ic_false_l);
                }
                if (view.getId() == R.id.tv_choose_b || view.getId() == R.id.tv_choose_d) {
                    view.setBackgroundResource(R.drawable.ic_false_r);
                }
            }
        }, 1000);
    }
}


