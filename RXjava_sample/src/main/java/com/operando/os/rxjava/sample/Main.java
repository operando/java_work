package com.operando.os.rxjava.sample;

import rx.Observable;
import rx.functions.Action1;

public class Main {

    public static void main(String[] s) {
        hello("RXjava", "Sample");
    }

    public static void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("Hello " + s + "!");
            }
        });
    }
}
