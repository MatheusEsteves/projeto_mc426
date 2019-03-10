package com.rep.organiza.organizarep.base;

import android.content.Context;

import com.google.gson.JsonObject;

import java.util.concurrent.atomic.AtomicBoolean;

import br.com.livelo.app.api.Services;
import retrofit2.Call;

public abstract class BasePresenter {

    private Context context;

    public BasePresenter() {
    }

    public BasePresenter(Context context) {
        this.context = context;
    }

    public final Call<?> getCall(Services typeService, JsonObject args) {
        return typeService.getCall(args);
    }

    public void retryConnection(Services service) {
    }

    public Context getContext() {
        return context;
    }
}
