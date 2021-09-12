package com.vedantu.counselling.data.util;

public class SequenceGenerator {
    private int start = 0;

    public int getNext() {
        return ++start;
    }
}
