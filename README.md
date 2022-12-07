# 概要

本プログラムは、コーディングテスト向けの実行クラスを有しています。サンプルケースとなるファイルを用意して、一斉に検査することが可能です。

## 利用方法

### 正誤判定<small style="font-size:0.75em;">（Main.javaが正答か判定する）</small>
1. **/src/>Main.java** に解答プログラムを書く。
1. **/src/TestCases/** にテストケースを作成する。
    + テストケース1なら **in1.txt** に入力データを、**out1.txt** に出力データを書く。
    + テストケース数に応じて、**in<span style="color:blue;">&lt;X&gt;</span>.txt** や **out<span style="color:blue;">&lt;X&gt;</span>.txt**を削除・作成して個数を調整する。
1. **/src/<span style="color:red;">CodeChecker.java</span>** を実行する。

### デバッグ<small style="font-size:0.75em;">（Main.javaに付けたブレークポイントを利用する）</small>
1. **/src/Main.java** に解答プログラムを書く。
1. **/src/Main.java** にブレークポイントを付ける。
1. **/src/TestCases/** にテストケースを作成する。
    + テストケース1なら **in1.txt** に入力データを書く。
    + テストケース数に応じて、**in<span style="color:blue;">&lt;X&gt;</span>.txt** を削除・作成して個数を調整する。
1. **/src/<span style="color:red;">CodeRunner.java</span>** を実行する。

## 実行クラス

次の3つの実行クラスがあります。

|クラス|用途|
|-|-|
|Main|解答用|
|CodeChecker|正誤判定|
|CodeRunner|一斉実行・デバッグ用|

### Mainクラス
解答用のソースコードを書きます。クラス名は必ず**Main**にしてください。

Windows環境でJava 17以下を利用する場合は、文字コードに注意が必要です。VSCodeではUTF-8が標準となっているため、Scannerクラス等で入力するときも以下のようにUTF-8を指定してください。

    new Scanner(System.in, "UTF-8")

ただし、入力データにひらがな・カタカナ・漢字などのマルチバイト文字が含まれていないのであれば、文字コードを指定しなくても問題ありません。

### CodeCheckerクラス
実行するCodeRunnerクラスと同様にテキストファイルから入力データを取得して、一斉に処理します。その上で、用意された出力ファイルと一致するか正誤を判定して結果を表示します。

出力の検査に利用するファイルは「out1.txt」「out2.txt」のように「out<span style="color:blue;">&lt;X&gt;</span>.txt」というファイル名のものが順に選択されます。

### CodeRunnerクラス
実行するとMainクラスのmainメソッドを繰り返し処理します。このときの入力データはTestCasesフォルダ内のテキストファイルから自動入力されます。

自動入力に利用するファイルは「in1.txt」「in2.txt」のように「in<span style="color:blue;">&lt;X&gt;</span>.txt」というファイル名のものが順に選択されます。

## テストケース

**/src/TestCases/** フォルダ内のファイルは、テストケースとして用いるものです。

|ファイル|用途|
|-|-|
|in<span style="color:blue;">&lt;X&gt;</span>.txt|入力データ|
|out<span style="color:blue;">&lt;X&gt;</span>.txt|出力データ|
|.checker|正誤判定用一時ファイル|
|.error|エラー発生判定用一時ファイル|
