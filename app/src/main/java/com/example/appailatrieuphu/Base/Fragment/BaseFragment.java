package com.example.appailatrieuphu.Base.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.appailatrieuphu.Base.OnMainCallBack;

public abstract class BaseFragment<V extends ViewBinding, B extends ViewModel> extends Fragment implements View.OnClickListener {
    protected V binding;
    protected B viewmodel;
    protected Context mContext;
    protected Object mdata;
    protected OnMainCallBack callBack;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void setCallBack(OnMainCallBack callBack) {
        this.callBack = callBack;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutId(), container, false);
        binding = initViewBinding(v);
        viewmodel = new ViewModelProvider(this).get(initViewModel());
        initView();
        return v;
    }

    protected abstract void initView();

    protected abstract V initViewBinding(View v);

    protected abstract Class<B> initViewModel();

    protected abstract int getLayoutId();


    @Override
    public void onClick(View v) {
        clickView(v);
    }

    protected void clickView(View v) {
    }

    public void setData(Object data) {
        mdata = data;
    }
}
