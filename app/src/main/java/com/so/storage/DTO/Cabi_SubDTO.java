package com.so.storage.DTO;

import java.io.Serializable;

public class Cabi_SubDTO implements Serializable {
    String val1 , val2 , val3;
    int cnt;


    public Cabi_SubDTO(String val1, String val2, String val3, int cnt) {
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
        this.cnt = cnt;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getVal1() {
        return val1;
    }

    public void setVal1(String val1) {
        this.val1 = val1;
    }

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }

    public String getVal3() {
        return val3;
    }

    public void setVal3(String val3) {
        this.val3 = val3;
    }

    public Cabi_SubDTO(String val1, String val2, String val3) {
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
    }
}
