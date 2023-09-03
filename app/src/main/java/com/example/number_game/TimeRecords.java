package com.example.number_game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TimeRecords implements Serializable {
    int minutes;
    int seconds;

    public TimeRecords(int minutes,int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes(){
        return minutes;
    }
    public int getSeconds(){
        return seconds;
    }
}
