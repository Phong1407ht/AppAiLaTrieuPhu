package com.example.appailatrieuphu.View.Act;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.appailatrieuphu.View.Fragment.BaseFragment;
import com.example.appailatrieuphu.View.OnMainCallBack;
import com.example.appailatrieuphu.R;

import java.lang.reflect.Constructor;

public abstract class BaseActivity<V extends ViewBinding, B extends ViewModel> extends AppCompatActivity implements View.OnClickListener, OnMainCallBack {
    protected V binding;
    protected B viewmodel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = LayoutInflater.from(this).inflate(getLayoutId(), null);
        binding = initViewBiding(v);
        viewmodel = new ViewModelProvider(this).get(initViewModel());
        setContentView(v);
        initView();

    }

    protected abstract Class<B> initViewModel();

    protected abstract void initView();

    protected abstract int getLayoutId();


    protected abstract V initViewBiding(View v);

    @Override
    public void onClick(View view) {

    }
    public void showFragment(String tag, Object data, boolean isBack) {
        try {
            Class<?> clazz = Class.forName(tag);  //Trỏ vào 1 fragment class
            Constructor<?> cons = clazz.getConstructor();
            BaseFragment<?, ?> frg = (BaseFragment<?, ?>) cons.newInstance(); //Tạo ra 1 thực thể từ lớp fragment
            frg.setData(data);
            frg.setCallBack(this);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            if (isBack) {
                trans.addToBackStack(null);//go back to previous screen
            }
            trans.replace(R.id.main, frg, tag).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
