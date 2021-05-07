package com.github.doghere.algorithm.base;

public class Index {
    private int start;
    private int stop;

    public Index(int start, int stop) {
        this.start = start;
        this.stop = stop;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public int length() {
        if (start == -1 || stop == -1) {
            return 0;
        }
        return stop - start + 1;
    }

    @Override
    public String toString() {
        return "Index{" +
                "start=" + start +
                ", stop=" + stop +
                '}';
    }
}