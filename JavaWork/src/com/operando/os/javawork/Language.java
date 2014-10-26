package com.operando.os.javawork;

public class Language {

//    private static final String OS;
    private static final String OS = System.getProperty("os.name");

//    static {
//        OS = System.getProperty("os.name");
//    }
    private String language;

    public Language() {
        // primary constructor
        this("java");
    }

    public Language(String language) {
        this.language = language;
    }

}
