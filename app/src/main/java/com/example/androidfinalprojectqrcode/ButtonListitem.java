package com.example.androidfinalprojectqrcode;

import android.widget.Button;

import java.util.Date;

public class ButtonListitem {
    String date;

    public String getText() {
        return text;
    }

//    public void setText(String text) {
//        this.text = text;
//    }

    String text;

    public ButtonListitem(String date, Button button, String text) {
        this.date = date;
        this.button = button;
        this.text = text;
    }

    public String getDate() {
        return date;
    }

//    public void setDate(String date) {
//        this.date = date;
//    }

    public Button getButton() {
        return button;
    }

//    public void setButton(Button button) {
//        this.button = button;
//    }

    static Button button;


}
