package com.example.appailatrieuphu.View.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appailatrieuphu.View.Act.MainActivity;
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
            showDialogFillName(Gravity.CENTER);
        }
    }

    private void showDialogFillName(int gravity) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.view_fillname);

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

        Button btConfirm = dialog.findViewById(R.id.bt_confirm);
        Button btCancle = dialog.findViewById(R.id.bt_cancle);
        EditText edtPlayerName = dialog.findViewById(R.id.edt_player_name);
        btCancle.setOnClickListener(view -> {
            dialog.dismiss();
            doCancle();
        });

        btConfirm.setOnClickListener(view -> {
            String playerName = edtPlayerName.getText().toString();
            if (playerName.trim().isEmpty()) {
                Toast.makeText(mContext, "Tên người chơi đang trống!!", Toast.LENGTH_SHORT).show();
                return;
            }
            doConfirm();
            dialog.dismiss();
            Log.i(TAG, "PlayerName:" + playerName.toString());
        });

        dialog.show();

    }

    private void doConfirm() {
        MediaManager.getInstance().stopPlayGame();
        MediaManager.getInstance().stopBG();
        MainActivity act = (MainActivity) mContext;
        act.showFragment(M002RuleFrg.TAG, null, true);
    }

    private void doCancle() {
        MediaManager.getInstance().stopPlayGame();
    }
}


