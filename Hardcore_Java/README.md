
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

# 8.データモデリング

## オブジェクト間の関係

* 〜は〜である(is a)
* 〜は〜を持つ(has a)
* 〜は〜を使用する(uses a)

## 〜は〜である(is a)の関係

* 継承
* 〜は〜の一種である とも言える

## 〜は〜を持つ(has a)の関係

* 集約
* コンポジション
 * あるオブジェクトが別のオブジェクトを集約し、そのオブジェクトのライフサイクルを制御する場合
 * あるクラスに別のクラスのオブジェクトを取り込んで扱うこと

## 〜は〜を使用する(uses a)の関係

* クラスは別のクラスを操作でき、そのクラスのサービスを使用できる


## 基本型の問題

* ラッパー型の使用を検討する
 * booleanを使った場合の成功・失敗では、そもそも操作していない時の状態が判断しにくい
 * ラッパー型にすることで、nullを操作していない状態とみなすことができる

### ラッパー型の検討

* nullになりえるプロパティだけにラッパー型を使用する
* データモデル内のすべての基本型に関するラッパークラスに変換する(全てにオブジェクトにする)
* ラッパー型を使うことにより、明確さと柔軟性が増す(推奨??)


## 制約

* 制約階層
* オブジェクト制約
* 基本型制約
* 制約の再利用
* ...Constraint
* validateメソッド
* 制約は、型ではなくその状況を基に有効性をチェックし、独特な状況が発生した場合のみに作成すべき


## 11.4種類の参照

### 強い参照

* オブジェクトをメモリ内に固定する参照
* ルートセット
 * 仮想マシンが作成・管理している参照群
 * 強い参照の経路がルートセットからあるオブジェクトまでたどれなくなると、そのオブジェクトはガーベージコレクションの対象となる


### 弱い参照

* オブジェクトをメモリ内に固定しない


### ソフト参照

* 弱い参照に類似している
* 理論的にはヒープ上のメモリが少ない場合のみに削除される点だけ異なる
* 別のヒープブロックを割り当てる前に、仮想マシンはすべての弱い参照を削除する
* それでも要求された新しい割り当てを行うのに十分なメモリが確保できないと、ソフト参照が削除される
* メモリが必要な場合はそのオブジェクトを削除できるが、必要でなければ削除されない
* ソフト参照は弱い参照のように扱われる
 * 仮想マシンがソフト参照をしっかり実装してないから？？


### ファントム参照

* すでにガーベージコレクションが行われたデータへの参照
* オブジェクトに対してガーベージコレクションが行われたことを知らせるためだけのもの


### リファレンスとリファレント

* リファレント
 * 弱い参照、ソフト参照、ファントム参照
* 参照オブジェクト自身はリファレンスと呼ばれる

