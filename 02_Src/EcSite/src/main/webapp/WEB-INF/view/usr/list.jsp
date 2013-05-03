<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>ユーザ情報一覧</title>
    <link rel="stylesheet" type="text/css" href="${f:url('/css/EcSite.css')}"/>
    <link rel="stylesheet" type="text/css" href="${f:url('/css/global.css')}"/>
</head>
<body>

<s:link href="/">&gt; HOME</s:link>

<h1>ユーザ情報一覧</h1>

<html:errors/>

<s:form>
<!-- 検索項目 -->
<table>
	<tr>
		<td nowrap="nowrap">キーワード</td>
		<td nowrap="nowrap"><html:text property="usrFormItemSearch.keyword" size="30" /></td>
		<td nowrap="nowrap">キーワード条件</td>
		<td nowrap="nowrap"><html:radio property="usrFormItemSearch.keywordAndOr" value="AND" />AND</td>
		<td nowrap="nowrap"><html:radio property="usrFormItemSearch.keywordAndOr" value="OR" />OR</td>
	</tr>
	<tr>
		<td colspan="5" style="text-align:right;"><input type="submit" name="list" value="検索" /></td>
	</tr>
</table>
<p>
※キーワード検索は、スペースで区切ることで複数の単語で検索できる。 <br/>
その際、キーワード条件のAND/ORを設定することで、AND検索かOR検索かを選択できる。<br/>
また、キーワード検索の検索対象は、ユーザ情報詳細画面に表示されている項目を対象とする。<br/>
キーワード検索は、あいまい検索となっていて、また中間一致で検索される。<br/>
あいまい検索の機能を以下に列挙する。<br/>
・半角英数字記号と全角英数字記号の違いを区別しない<br/>
・半角ｶﾅと全角カナと全角ひらがなの違いを区別しない<br/>
例えば、DB値に「ユーザ名称123」がある場合、これはキーワード値「ざ名称12」に一致する。
</p>
</s:form>

<table style="border:0px; background-color:#000000;" cellpadding='5px' cellspacing="1px" >

<!-- ユーザ情報ヘッダ -->
<tr style="background-color:pink">
	<th>ユーザID</th>
	<th>ユーザ名称</th>
	<th>ユーザPWD</th>
	<th>有効/無効</th>
	<th></th>
	<th></th>
	<th></th>
</tr>

<!-- ユーザ情報ボディ -->
<c:forEach var="e" varStatus="s" items="${usrFormItemList}">
	<tr style="background-color:${s.index %2 == 0 ? 'white' : 'aqua'}">
		<td>${f:h(e.usrId)}</td>
		<td>${f:h(e.usrName)}</td>
		<td>${f:h(e.usrPwd)}</td>
		<td>${e.invalidFlg == 0 ? '有効' : '無効'}</td>
		<td><s:link href="detail/${e.usrId}">詳細 </s:link></td>
		<td><s:link href="registEdt/${e.usrId}">編集</s:link></td>
		<td><s:link onclick="return confirm('削除してもよろしいですか?');" href="delete/${e.usrId}">削除</s:link></td>
	</tr>
</c:forEach>

</table>

<p>
※最大${IcCmnConst.LIST_SHOW_NUM}件まで表示
</p>

</body>
</html>