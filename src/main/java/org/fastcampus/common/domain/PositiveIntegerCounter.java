package org.fastcampus.common.domain;

public class PositiveIntegerCounter {

    private int count;

    public PositiveIntegerCounter(int count) {
        this.count = count;
    }

    public PositiveIntegerCounter() {
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    public void increase() {
        this.count++;
    }

    public void decrease() {
        if (count <= 0) {
            return;
        }
        this.count--;
    }

}
