package com.rep.organiza.organizarep;

import com.google.gson.annotations.SerializedName;

//TODO remove this class as soon as possible, it's just to be used as a start model
public class Test {
    @SerializedName("test")
    private int test;

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }
}
