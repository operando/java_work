
# 6.ネストクラス

* publicな内部クラスはあまりよくない。
 * 外部向けの内部クラスをどうしても作りたい場合、staticなネストクラスを使用する

## 限定スコープ内部クラス

```java
public void test(){
    class Test {
        public void test(){
            System.out.println("test");
        }
    }
}
```

* 無名クラスは再利用できないので、あまりよくない
* 無名クラスはコンパイルすると、限定スコープ内部クラスみたいな扱い？

## staticネストクラス

* public staticなネストクラスをインスタンス化する場合、外部クラスのインスタンスは必要ない
* publicなネストクラスは外部クラスのインスタンスが必要

```java
public class Test {
    public class Test2 {
        // ....
    }

    public static class Test3 {
        // ....
    }
}

Test t = new Test();
Test2 t2 = t.new Test2();
Test3 t3 = new Test.Test3();
```

# 7.定数

## 置換定数と国際化

* ResorceBundleで置換定数を国際化
* 定数がまとまったクラスのstaticでResorceBundleから定数をLoadする

## ビットフィールド

* 整数bitの0/1で値が設定されているかどうか等を確認できる
* 省メモリ
* bit空間が有限であるため、割り当てられるものに限りがある
* 拡張性があまりよくない
* オブジェクトの内部実装を外に公開するため、カプセル化の観点からもあまりよくない

```java
public class BitF {
    public static final int JAVA = 1;
    public static final int RUBY = 2;
    public static final int PHP = 4;

    private int languages;

    public void setLanguages(int languages) {
        this.languages = languages;
    }

    public int getLanguages() {
        return languages;
    }
}

Bitf bf = new Bitf();
bf.setLanguages(BitF.JAVA | BitF.PHP); // languageにJavaとPHPをセット

// Javaがセットされているか AND演算子
if(bf.getLanguages() & BitF.JAVA > 0) {
    System.out.println("Java!");
}

// Rubyがセットされているか
if(bf.getLanguages() & BitF.RUBY > 0) {
    System.out.println("Ruby!");
}
```


