package com.company;

public class Counter {
    private int count;

    public int getCount() {
        return count;
    }

    public void increment(){
        count++;
    }

    @Override
    public String toString() {
        return  count + "";
    }
}
