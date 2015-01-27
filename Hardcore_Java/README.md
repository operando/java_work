
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

## オプション定数

* 整数オプション定数で連番にする場合、それを使用するメソッド等はMIN/MAXの範囲チェックをしたほうがよい
 * しかし、途中連番がなくなったり、新しく定数を追加した際に修正が大変
 * 解決策として定数オブジェクトまたはEnumを使用する

## 定数オブジェクト

* finalクラスのfinalインスタンスで定数を宣言する
* それにより、範囲外(対象外)の定数定義を阻止する
* 検索する際に、定数名で検索可能
* 整数オプション定数と違い、定数追加/削除時にロジックに対する修正はいらない
* 整数オプション定数ならコンパイル時に値に置き換えられる
* オブジェクト定数の場合、プログラム実行時に多少のオーバーヘッドがある
 * 数ミリ秒なら大きな問題にはならない
* static finalなオブジェクト定数なら==比較可能

```java
public final class Test {
    public static final Test NAME_JAVA = new Test("Java");
    public static final Test NAME_RUBY = new Test("Ruby");
    public static final Test NAME_PHP = new Test("PHP");

    private final String name;

    private Test(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class Language {
    private Test test;

    public void setTest(final Test test) {
        // 対象外の定数は渡されない
        this.test = test;
    }

    public Test getTest() {
        return test;
    }
}

Language l = new Language();
l.setTest(Test.NAME_JAVA);

// メモリ比較可能。staticなのでセットした値と定数宣言されているメモリは同じはす
if (l.getTest == Test.NAME_JAVA) {
    
}
```




