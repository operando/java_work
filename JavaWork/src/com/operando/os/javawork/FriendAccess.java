package com.operando.os.javawork;

public class FriendAccess {

    private int value;

    public FriendAccess(int value) {
        this.value = value;
    }

    public void setvValue(int value) {
        if (value > 10) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void someMethod(final FriendAccess fa) {
        if (fa.value == 5 && this.value == 5) {
            //fa.value = 25; // NG
            fa.setvValue(25);// IllegalArgumentException
        }
    }
}
