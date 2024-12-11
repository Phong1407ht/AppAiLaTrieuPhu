package com.example.appailatrieuphu.View.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;


import com.example.appailatrieuphu.R;
import com.example.appailatrieuphu.View.Act.MainActivity;
import com.example.appailatrieuphu.databinding.M003PlayFrgBinding;
import com.example.appailatrieuphu.db.Entities.Question;
import com.example.appailatrieuphu.viewmodel.M003PlayVM;

import java.util.List;


public class M003PlayFrg extends BaseFragment<M003PlayFrgBinding, M003PlayVM> {
    public static final String TAG = M003PlayFrg.class.getName();
    public Question currentQuestion;
    private List<Question> list;
    private int level;
    private int cash;

    @Override
    protected void initView() {
        binding.tvChooseA.setOnClickListener(this);
        binding.tvChooseB.setOnClickListener(this);
        binding.tvChooseC.setOnClickListener(this);
        binding.tvChooseD.setOnClickListener(this);

        viewmodel.getListQuestion().observe(this, questions -> {
            fetchQuestions(questions);
        });
        viewmodel.getTime().observe(this, times -> {
            viewmodel.countDownTime();
            if (times < 0) return;
            binding.tvTime.setText(String.format("%s", times));
            if (times == 0) {
                showDialogPlayAgain(Gravity.CENTER);
            }
        });
    }

    private void fetchQuestions(List<Question> questions) {
        list = questions;
        currentQuestion = list.get(level);
        Log.i(TAG, "listQuestion" + questions);
        showQuestion();
    }

    private void showDialogPlayAgain(int gravity) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.view_playagain);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        Button btnPlayAgain = dialog.findViewById(R.id.btn_playagain);
        Button btnSave = dialog.findViewById(R.id.btn_save);
        TextView tvAchive = dialog.findViewById(R.id.tv_achivement);
        tvAchive.setText(String.format("%s,000 VND",viewmodel.getMoney().getValue()));
        dialog.show();
        btnPlayAgain.setOnClickListener(view -> {
            dialog.dismiss();
            doPlayAgain();
        });

        btnSave.setOnClickListener(view -> {
            MainActivity act = (MainActivity) mContext;
            act.onBackPressed();
            dialog.dismiss();
            doSave();
        });
    }

    private void doPlayAgain() {
        initView();
        cash = 0;
        binding.tvMoney.setText(String.format("%s,000", cash));
    }

    private void doSave() {

    }

    @SuppressLint("ResourceType")
    private void showAchivement() {
            switch (level) {
                case 1:
                    viewmodel.getMoney().setValue(200);
                    cash = 200;
                    binding.tvMoney.setText(String.format("%s,000", cash));
                    break;
                case 2:
                    viewmodel.getMoney().setValue(400);
                    cash = 400;
                    binding.tvMoney.setText(String.format("%s,000", cash));
                    break;
                case 3:
                    viewmodel.getMoney().setValue(600);
                    cash = 600;
                    binding.tvMoney.setText(String.format("%s,000", cash));
                    break;
                case 4:
                    viewmodel.getMoney().setValue(1000);
                    cash = 1000;
                    binding.tvMoney.setText(String.format("%s,000", cash));
                    break;
            }
    }

    private void nextQuestion() {
        level++;
        new Handler().postDelayed(() -> {
            currentQuestion = list.get(level);
            showQuestion();
        }, 1000);
        viewmodel.getTime().setValue(30);
        viewmodel.getTime().observe(this,times ->{
            viewmodel.countDownTime();
        });
    }

    private void showQuestion() {
        binding.tvChooseA.setText(currentQuestion.getCaseA());
        binding.tvChooseB.setText(currentQuestion.getCaseB());
        binding.tvChooseC.setText(currentQuestion.getCaseC());
        binding.tvChooseD.setText(currentQuestion.getCaseD());
        binding.imgQuestion.setText(currentQuestion.getQuestion());

        binding.tvChooseA.setBackgroundResource(R.drawable.ic_choose_l);
        binding.tvChooseB.setBackgroundResource(R.drawable.ic_choose_r);
        binding.tvChooseC.setBackgroundResource(R.drawable.ic_choose_l);
        binding.tvChooseD.setBackgroundResource(R.drawable.ic_choose_r);
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
                    nextQuestion();
                    showAchivement();
                }
                if (view.getId() == R.id.tv_choose_b || view.getId() == R.id.tv_choose_d) {
                    view.setBackgroundResource(R.drawable.ic_true_r);
                    nextQuestion();
                    showAchivement();
                }
            } else {
                if (view.getId() == R.id.tv_choose_a || view.getId() == R.id.tv_choose_c) {
                    view.setBackgroundResource(R.drawable.ic_false_l);
                    showDialogPlayAgain(Gravity.CENTER);
                    cash = 0;
                    level = 0;
                }
                if (view.getId() == R.id.tv_choose_b || view.getId() == R.id.tv_choose_d) {
                    view.setBackgroundResource(R.drawable.ic_false_r);
                    showDialogPlayAgain(Gravity.CENTER);
                    cash = 0;
                    level = 0;
                }
            }
        }, 1000);
    }
}


