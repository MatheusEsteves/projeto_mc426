package com.rep.organiza.organizarep.api;

import com.google.gson.JsonObject;
import com.rep.organiza.organizarep.Constants;

import retrofit2.Call;

@SuppressWarnings("unchecked")
public enum Services {
    TEST {
        @Override
        public <T> Call<T> getCall(JsonObject args) {
            String test = args.get(Constants.TEST).getAsString();

            return (Call<T>) OrganizaRepAPI.get().test("MyNameTest");
        }
    };

    public abstract <T> Call<T> getCall(JsonObject args);

}
