package com.os.operando.sample;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] s) {
        var a = new ArrayList<String>();
        a.add("hogehoge");
        // NG aの型はArrayList<String>。List<String>ではない
        // a = new LinkedList<String>();
        System.out.println(a);
        var aa = 10;
        // NG!! 他の型は代入できない
        // aa = "";
        // OK 同じ型なら再代入できる + finalではない
        aa = 21;
        var b = "";
        b = "b";
        System.out.println(b);
        System.out.println(aa);
        var aaa = a.stream();
        aaa.forEach(System.out::println);
        var aaaa = new int[]{1, 2, 3};
        System.out.println(aaaa);

        final var c = 1;
        // NG! finalなので
        // c = 10;

        List<String> strs = Stream.of("aa", "bb")
                .collect(Collectors.toUnmodifiableList());
        // strs.add(""); // UnsupportedOperationException
    }

    private static void test() {
        // NG!!
//        var a = null;
//        a = "";

        Runnable runner = () -> {
            System.out.println("run!");
        };
        // NG!!
        // var runner = () -> { System.out.println("run!"); };
    }

    class Test {
        // NG!! ローカル変数にしかvarは使えない
        // var a = "";
    }
}