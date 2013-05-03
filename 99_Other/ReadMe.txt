「EcSiteサンプル」は、インターネット上で商品を販売するWebサイトを模擬した3層Webアプリケーションである。


【システム環境】
APサーバ:Tomcat7
DBサーバ:Oracle 11g Release2 無償版
その他の環境
・Java7
・SAstruts
・Eclipse4.2

[Eclipseプラグイン]
・ERMaster
・Dolteng
・SAStrutsPlugin


【構築方法】
以下に構築方法の手順を示す。
1.githubのホームページ（https://github.com/dev123xyz/ecSite）のZIPリンクよりアプリケーションを取得し解凍する
　(ファイルサイズ30MB程度)
2.「01_DB\ReadMe(DB).txt」に従いDBを構築する
3.Tomcat Webアプリケーションマネージャ(http://localhost:8080/manager/) の
　「WARファイルの配備」より「03_War\EcSite.war」を配備する
4.ブラウザよりhttp://localhost:8080/EcSite/へアクセスする


【機能説明】
以下に機能を列挙する。
1.ユーザの登録
2.ユーザの編集
3.ユーザPWDの暗号化
4.ユーザ情報の入力値チェック
5.ユーザの削除
6.ユーザ情報のキーワード検索（あいまい検索）

