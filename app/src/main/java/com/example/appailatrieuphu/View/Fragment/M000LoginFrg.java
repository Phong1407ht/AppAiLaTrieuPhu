package com.example.appailatrieuphu.View.Fragment;

import android.view.View;
import android.widget.Toast;


import com.example.appailatrieuphu.R;
import com.example.appailatrieuphu.View.Act.MainActivity;
import com.example.appailatrieuphu.databinding.M000LoginFrgBinding;
import com.example.appailatrieuphu.viewmodel.M000MainViewModel;


public class M000LoginFrg extends BaseFragment<M000LoginFrgBinding, M000MainViewModel> {
    public static final String TAG = M001MainFrg.class.getName();
    @Override
    protected void initView() {
        binding.btLogin.setOnClickListener(this);
    }


    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.bt_login) {
            String userName = binding.edtUserName.getText().toString();
            String pass = binding.edtPass.getText().toString();
            if (userName.trim().isEmpty() || pass.trim().isEmpty()) {
                Toast.makeText(mContext, "Username or password is Empty!!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if(v.getId() == R.id.tv_sign_up){
            MainActivity act = (MainActivity) mContext;
            act.showFragment(M000SignUpFrg.TAG, null, true);
        }
    }

    @Override
    protected M000LoginFrgBinding initViewBinding(View v) {
        return M000LoginFrgBinding.bind(v);
    }

    @Override
    protected Class<M000MainViewModel> initViewModel() {
        return M000MainViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m000_login_frg;
    }
}
