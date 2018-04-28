# ThreadLocalを使ってトランザクションスクリプト内における重複したSQL（SELECT）発行に対する性能改善サンプル

既存のシステムが存在し、そのシステムがトランザクションスクリプトで実装されているとします。
そして、トランザクションスクリプト内における何度も同じSQL（SELECT）を発行しているロジックがあり、性能面でボトルネックになるとします。
（例えば、マスタから値を取得する処理が何度も登場するが、基本的にはWHERE句含めて全く同じで、何度もSQL（SELECT）を発行する必要が無いケースなど）

こういった問題を解消するため、トランザクションスクリプト内で完全に同じSQL（SELECT）を2回目以降発行する場合は、
1回目に発行した結果セットを保持しておき、それを使いまわすように改良することで、性能改善を図ることを目的としたサンプルプログラムです。

想定としてはWebアプリケーションのようなものをイメージしていて、Requestユーザごとにメインスレッドが起動し、
他のユーザのスレッドと競合しないように、スレッド内でグローバルに結果セットを保持することを再現しようとしています。

## サンプルプログラムの構成イメージ

| | | | | | | | | |
|:--|:--|:--|:--|:--|:--|:--|:--|:--|
|Main|→|SampleCallable [Req A]|→|SampleAction|→|SampleBiz|→|ADao|
| |→|SampleCallable [Req B]|→|SampleAction|→|SampleBiz|→|ADao|
| |→|SampleCallable [Req C]|→|SampleAction|→|SampleBiz|→|ADao|

### 補足

上記構成イメージは、以下のようなことを表しています。

- Main
 - 複数のクライアントからほぼ同時にアクセスされたことを疑似的に再現するための処理クラス
 - ExecutorServiceを使って複数スレッドを生成
- SampleCallable
 - ExecutorServiceを利用して実際に各スレッドの開始ポイントとなるクラス
 - これが１ユーザーセッション（Request）に相当するイメージ。
- SampleAction
 - 既存システムがStruts1.X系で構築されたレガシーなシステムを想定
 - Requestを受け付けるActionクラス
- SampleBiz
 - トランザクションスクリプトでの実装であるため、ビジネスロジックを表すクラス
 - 単純にDaoを呼んで、SELECTした結果のリストをログ出力している。
- ADao
 - Aテーブルに対する操作を行うDao

## 実行方法

MainクラスのmainメソッドをキックするだけでOKです。
以下の変数定義をtrue / falseで切り替えることで、ThreadLocalを利用した場合と、利用しなかった場合での処理時間の違いについて、確認できます。

`boolean enableThreadLocal = true;`