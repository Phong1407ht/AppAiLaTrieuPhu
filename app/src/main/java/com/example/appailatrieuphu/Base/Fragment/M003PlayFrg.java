package com.example.appailatrieuphu.Base.Fragment;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.appailatrieuphu.R;
import com.example.appailatrieuphu.databinding.M003PlayFrgBinding;
import com.example.appailatrieuphu.viewmodel.M003PlayVM;


public class M003PlayFrg extends BaseFragment<M003PlayFrgBinding, M003PlayVM> {
    public static final String TAG = M003PlayFrg.class.getName();

    @Override
    protected void initView() {
        binding.tvChooseA.setOnClickListener(this);
        binding.tvChooseB.setOnClickListener(this);
        binding.tvChooseC.setOnClickListener(this);
        binding.tvChooseD.setOnClickListener(this);
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

    @Override
    protected void clickView(View v) {
        switch (v.getId()) {
            case R.id.tv_choose_a:
                binding.tvChooseA.setBackgroundResource(R.drawable.ic_false_l);
                viewmodel.setAnswer(1);
            case R.id.tv_choose_b:
                binding.tvChooseA.setBackgroundResource(R.drawable.ic_false_r);
                viewmodel.setAnswer(2);
            case R.id.tv_choose_c:
                binding.tvChooseA.setBackgroundResource(R.drawable.ic_false_l);
                viewmodel.setAnswer(3);
            case R.id.tv_choose_d:
                binding.tvChooseA.setBackgroundResource(R.drawable.ic_false_r);
                viewmodel.setAnswer(4);

        }
    }

    public void showTrueAns(final TextView textView) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (true) {
                    if (textView.getId() == R.id.tv_choose_a || textView.getId() == R.id.tv_choose_c) {
                        textView.setBackgroundResource(R.drawable.ic_true_l);
                    }
                    if (textView.getId() == R.id.tv_choose_b || textView.getId() == R.id.tv_choose_d) {
                        textView.setBackgroundResource(R.drawable.ic_true_r);
                    }
                } else {
                    if (textView.getId() == R.id.tv_choose_a || textView.getId() == R.id.tv_choose_c) {
                        textView.setBackgroundResource(R.drawable.ic_false_l);
                    }
                    if (textView.getId() == R.id.tv_choose_b || textView.getId() == R.id.tv_choose_d) {
                        textView.setBackgroundResource(R.drawable.ic_false_r);
                    }
                }
            }
        }, 1000);
    }
}


