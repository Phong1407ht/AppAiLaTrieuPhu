package com.example.appailatrieuphu.Base.Fragment;

import android.view.View;

import com.example.appailatrieuphu.Base.Act.MainActivity;
import com.example.appailatrieuphu.MediaManager;
import com.example.appailatrieuphu.R;
import com.example.appailatrieuphu.databinding.M001MainFrgBinding;
import com.example.appailatrieuphu.viewmodel.M001MainViewModel;

public class M001MainFrg extends BaseFragment<M001MainFrgBinding, M001MainViewModel> {
    public static final String TAG = M001MainFrg.class.getName();

    @Override
    protected void initView() {
        MediaManager.getInstance().playBG(R.raw.song_intro);
        binding.ivPlay.setOnClickListener(this);
        binding.ivCup.setOnClickListener(this);
        binding.ivInfo.setOnClickListener(this);
        binding.ivSetting.setOnClickListener(this);
    }

    @Override
    protected M001MainFrgBinding initViewBinding(View v) {
        return M001MainFrgBinding.bind(v);
    }

    @Override
    protected Class<M001MainViewModel> initViewModel() {
        return M001MainViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m001_main_frg;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.iv_play) {
            MediaManager.getInstance().stopBG();
            MainActivity act = (MainActivity) mContext;
            act.showFragment(M002RuleFrg.TAG, null, true);
        }
    }
}
