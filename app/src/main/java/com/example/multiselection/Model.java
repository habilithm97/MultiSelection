package com.example.multiselection;

public class Model {
    String str;
    boolean isSelected;

    public Model(String str, boolean isSelected) {
        this.str = str;
        this.isSelected = isSelected;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


}
