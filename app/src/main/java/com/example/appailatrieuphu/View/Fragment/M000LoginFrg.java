package com.example.appailatrieuphu.View.Fragment;


import android.util.Log;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.example.appailatrieuphu.R;
import com.example.appailatrieuphu.View.Act.MainActivity;
import com.example.appailatrieuphu.databinding.M000LoginFrgBinding;
import com.example.appailatrieuphu.viewmodel.M000MainViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class M000LoginFrg extends BaseFragment<M000LoginFrgBinding, M000MainViewModel> {
    public static final String TAG = M000LoginFrg.class.getName();
    @Override
    protected void initView() {
        binding.btLogin.setOnClickListener(this);
        binding.tvSignUp.setOnClickListener(this);
    }


    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.bt_login) {
            String userName = binding.edtUserName.getText().toString();
            String pass = binding.edtPass.getText().toString();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            if (userName.trim().isEmpty() || pass.trim().isEmpty()) {
                Toast.makeText(mContext, "Username or password is Empty!!", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.signInWithEmailAndPassword(userName, pass)
                    .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                MainActivity act = (MainActivity) mContext;
                                act.showFragment(M001MainFrg.TAG, null, true);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(mContext, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
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
