# Java ソースコード解析ツール cmajava

このプログラムは Java のソースコードを解析し，コードのメトリクスを収集するためのツールです．

コード内のクラス数，メソッド宣言数，メソッド呼び出し数，Javadoc の数などの様々なメトリクスを計算します．

## 前提条件

- Java 8 以上がインストールされていること
- Maven がインストールされていること

## ビルド方法

1. プロジェクトをクローンまたはダウンロードする．
2. コマンドラインから以下のコマンドを実行して，プロジェクトをビルドする．
    ```
    mvn clean install
    ```
    ビルドが成功すると，`target` ディレクトリに実行可能な jar ファイルが作成される．

## 実行方法

ビルド完了後，以下のコマンドを使用してプログラムを実行できる．
```
java -jar target/CodeMetricAnalyzer.jar <パス> [表示形式]
```
`<パス>` は解析する Java ファイルまたはディレクトリのパス．

`[表示形式]` はオプションで `table` または `csv` を指定できる(デフォルトは `table`)．

## 表示例

```
Metric                             Count
----------------------------------------
Files                                 20    // ファイル数
Blank Lines                          298    // 空白行数
Comment Lines                        693    // コメント行数
Code Lines                          1704    // コード行数
Classes                               20    // クラス数
Method Declarations                  229    // メソッド宣言数
Method Invocations                   527    // メソッド呼び出し数
Javadocs Of Method                   79     // メソッドの Javadoc 数
Total Javadocs                        94    // 総 Javadoc 数
```