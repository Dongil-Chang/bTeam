package com.example.myssproject02;

import java.io.Serializable;
import java.util.ArrayList;

public class Box_MainDTO implements Serializable {
    String val1 , val2 , val3;

    public Box_MainDTO(String val1, String val2, String val3, ArrayList<Box_SubDTO> items) {
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
        this.items = items;
    }



    public ArrayList<Box_SubDTO> items;

    public Box_MainDTO(ArrayList<Box_SubDTO> items) {
        this.items = items;
    }

    public ArrayList<Box_SubDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<Box_SubDTO> items) {
        this.items = items;
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

    public Box_MainDTO(String val1, String val2, String val3) {
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
    }
}
