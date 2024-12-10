package com.example.appailatrieuphu.View.Fragment;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.appailatrieuphu.R;
import com.example.appailatrieuphu.View.Act.MainActivity;
import com.example.appailatrieuphu.databinding.SignUpFrgBinding;
import com.example.appailatrieuphu.viewmodel.M000MainViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

public class M000SignUpFrg extends BaseFragment<SignUpFrgBinding, M000MainViewModel> {
    public static final String TAG = M001MainFrg.class.getName();

    @Override
    protected void initView() {
        binding.btSignUp.setOnClickListener(this);
    }


    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.bt_sign_up) {
            String userName = binding.edtUserName.getText().toString().trim();
            String pass = binding.edtPass.getText().toString().trim();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(userName, pass)
                    .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                goToMainFragment();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(mContext, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void goToMainFragment() {
        MainActivity act = (MainActivity) mContext;
        act.showFragment(M001MainFrg.TAG, null, true);
    }

    @Override
    protected SignUpFrgBinding initViewBinding(View v) {
        return SignUpFrgBinding.bind(v);
    }

    @Override
    protected Class<M000MainViewModel> initViewModel() {
        return M000MainViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.sign_up_frg;
    }
}
