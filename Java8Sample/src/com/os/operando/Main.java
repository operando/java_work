package com.os.operando;

import com.os.operando.models.Test;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        // Optional
        Optional<String> str = Optional.ofNullable(new Test("test").getStr());
        str.ifPresent(s -> System.out.println(s));
        Optional<Integer> strLength = str.map(s -> s.length());
        strLength.ifPresent(i -> System.out.println("length : " + i));

        Optional<String> nullStr = Optional.ofNullable(new Test(null).getStr());
        nullStr.ifPresent(s -> System.out.println(s));
        System.out.println(nullStr.orElse("null"));
        Optional<Integer> nullStrLength = nullStr.map(s -> s.length());
        nullStrLength.ifPresent(i -> System.out.println("length : " + i));
        System.out.println(nullStrLength.orElse(-1));

        // str1とstr2に値が存在する場合のみ、処理結果を返す
        Optional<String> str1 = Optional.ofNullable(new Test("test1").getStr());
        Optional<String> str2 = Optional.ofNullable(new Test("test2").getStr());
        Optional<String> str3 = str1.flatMap(s -> str2.flatMap(s2 -> {
            return Optional.of(s + s2);
        }));

        // orElse/orElseGet 引数 = Optionalが値を持っていないときのデフォルト値
        System.out.println(str3.orElse("null"));
        System.out.println(str3.orElseGet(() -> "null"));
    }
}
