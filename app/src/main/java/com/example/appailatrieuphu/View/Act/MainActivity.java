package com.example.appailatrieuphu.View.Act;

import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.appailatrieuphu.View.Fragment.M001MainFrg;
import com.example.appailatrieuphu.View.OnMainCallBack;
import com.example.appailatrieuphu.MediaManager;
import com.example.appailatrieuphu.R;
import com.example.appailatrieuphu.databinding.ActivityMainBinding;
import com.example.appailatrieuphu.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    public static final String TAG = MainActivity.class.getName();

    @Override
    protected Class<MainViewModel> initViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected void initView() {
        initDB();
    }

    private void initDB() {
        new Thread(() -> {
            try {
                runOnUI((data, key) -> gotoMainScreen());
            } catch (Exception e) {
                runOnUI((data, key) -> showAlert());
            }
        }).start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MediaManager.getInstance().pauseSong();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MediaManager.getInstance().playSong();
    }

    private void gotoMainScreen() {
        new Handler().postDelayed(() -> {
            binding.ivLogo.setVisibility(View.GONE);
            binding.progressLoading.setVisibility(View.GONE);
            showFragment(M001MainFrg.TAG, null, false);
        }, 2000);
    }

    private void showAlert() {
        Toast.makeText(this, "Không lấy được giữ liệu câu hỏi", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void runOnUI(OnMainCallBack callBack) {
        runOnUiThread(() -> callBack.callBack(null, null));
    }

    @Override
    protected ActivityMainBinding initViewBiding(View v) {
        return ActivityMainBinding.bind(v);
    }

    @Override
    public void callBack(Object data, String key) {

    }
}
