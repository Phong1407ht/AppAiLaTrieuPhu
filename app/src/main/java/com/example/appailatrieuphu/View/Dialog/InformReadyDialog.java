package com.example.appailatrieuphu.View.Dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appailatrieuphu.View.OnMainCallBack;
import com.example.appailatrieuphu.R;

public class InformReadyDialog extends Dialog {
    private final OnMainCallBack callBack;
    public static final String KEY_BACK = "KEY_BACK";
    public static final String KEY_READY = "KEY_READY";

    public InformReadyDialog(@NonNull Context context, OnMainCallBack callBack) {
        super(context);
        setContentView(R.layout.view_ready);
        initViews();
        this.callBack = callBack;
    }

    private void initViews() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        findViewById(R.id.bt_ready).setOnClickListener(view -> {
            doReady();
        });
        findViewById(R.id.bt_back).setOnClickListener(view -> doBack());
    }

    protected InformReadyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener, OnMainCallBack callBack) {
        super(context, cancelable, cancelListener);
        this.callBack = callBack;
    }
    private void doReady(){
        callBack.callBack(null,KEY_READY);
        dismiss();
    }
    private void doBack(){
        callBack.callBack(null,KEY_BACK);
        dismiss();
    }
    public InformReadyDialog(@NonNull Context context, int themeResId, OnMainCallBack callBack) {
        super(context, themeResId);
        this.callBack = callBack;
    }
}
