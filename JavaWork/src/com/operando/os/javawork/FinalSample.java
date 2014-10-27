package com.operando.os.javawork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FinalSample {

    public static final List<String> NAMES;

    static {
//        NG
//        NAMES = new ArrayList<>();
//        NAMES.add("java");
//        NAMES.add("ruby");
        List<String> tmp = new ArrayList<>();
        tmp.add("java");
        tmp.add("ruby");
        NAMES = Collections.unmodifiableList(tmp); // Unmodifiable Collection
    }

    private String name;

    public void setName(final String name) {
        // name = name; // NG
        this.name = name;
    }

    public String sample(String name) {
        final String message;
        if (name == null) {
            message = "My name is Null";
        } else {
            message = "My name is " + name;
        }
        return message;
    }

    public void changeName() {
        List<String> name = NAMES;
        name.add("PHP"); // UnsupportedOperationException
    }
}
