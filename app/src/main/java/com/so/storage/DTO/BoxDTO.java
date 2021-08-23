package com.so.storage.DTO;


public class BoxDTO {
    public String box_name, box_info;
    public int color_img;

    public BoxDTO(){}

    public BoxDTO(String box_name, String box_info, int color_img) {
        this.box_name = box_name;
        this.box_info = box_info;
        this.color_img = color_img;
    }

    public String getBox_name() {
        return box_name;
    }

    public void setBox_name(String box_name) {
        this.box_name = box_name;
    }

    public String getBox_info() {
        return box_info;
    }

    public void setBox_info(String box_info) {
        this.box_info = box_info;
    }

    public int getColor_img() {
        return color_img;
    }

    public void setColor_img(int color_img) {
        this.color_img = color_img;
    }
}
