package com.so.storage.DTO;

public class CabiDTO {
    public String cabi_name, cabi_info;
    public int color_img;

    public CabiDTO(){}

    public CabiDTO(String cabi_name, String cabi_info, int color_img) {
        this.cabi_name = cabi_name;
        this.cabi_info = cabi_info;
        this.color_img = color_img;
    }

    public String getCabi_name() {
        return cabi_name;
    }

    public void setCabi_name(String cabi_name) {
        this.cabi_name = cabi_name;
    }

    public String getCabi_info() {
        return cabi_info;
    }

    public void setCabi_info(String cabi_info) {
        this.cabi_info = cabi_info;
    }

    public int getColor_img() {
        return color_img;
    }

    public void setColor_img(int color_img) {
        this.color_img = color_img;
    }
}
