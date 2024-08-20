package com.example.appailatrieuphu.Base.Fragment;

import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.appailatrieuphu.Base.Act.MainActivity;
import com.example.appailatrieuphu.MediaManager;
import com.example.appailatrieuphu.R;
import com.example.appailatrieuphu.databinding.M002RuleFrgBinding;
import com.example.appailatrieuphu.viewmodel.M002MainViewModel;

public class M002RuleFrg extends BaseFragment<M002RuleFrgBinding, M002MainViewModel> {
    public static final String TAG = M002RuleFrg.class.getName();

    @Override
    protected void initView() {
        MediaManager.getInstance().playGame(R.raw.song_rule, mediaPlayer -> {
            showReadyDialog();
        });
        binding.lnMilestone.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_left));
        binding.btHide.setOnClickListener(view -> {
            MediaManager.getInstance().stopPlayGame();
            showReadyDialog();
        });
    }

    @Override
    protected M002RuleFrgBinding initViewBinding(View v) {
        return M002RuleFrgBinding.bind(v);
    }

    @Override
    protected Class<M002MainViewModel> initViewModel() {
        return M002MainViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m002_rule_frg;
    }

    private void showReadyDialog() {
        InformReadyDialog inform = new InformReadyDialog(mContext, (data, key) -> {
            if (key.equals(InformReadyDialog.KEY_BACK)) {
                doBack();
            } else if (key.equals(InformReadyDialog.KEY_READY)) {
                doReady();
                MediaManager.getInstance().playGame(R.raw.song_ready,mediaPlayer -> {
                    MainActivity act = (MainActivity) mContext;
                    act.showFragment(M003PlayFrg.TAG, null, true);
                });
            }
        });
        inform.show();

    }

    private void doReady() {
        MediaManager.getInstance().stopPlayGame();
    }

    private void doBack() {
        MediaManager.getInstance().stopPlayGame();
        MainActivity act = (MainActivity) mContext;
        act.onBackPressed();
    }
}
