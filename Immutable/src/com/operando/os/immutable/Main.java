package com.operando.os.immutable;

public class Main {
    public static void main(String[] s) {
        ImmutableUser iu = new ImmutableUser("java", 10);
        String name = iu.getName();
        System.out.println(iu.getName());
        name = "aaa";
        System.out.println(iu.getName());
    }
}
