<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>ユーザ情報一覧</title>
    <link rel="stylesheet" type="text/css" href="${f:url('/css/global.css')}"/>
</head>
<body>

<h1>ユーザ情報一覧</h1>

<html:errors/>

<table style="border:0px; background-color:#000000;" cellpadding='5px' cellspacing="1px" >

<!-- ユーザ情報ヘッダ -->
<tr style="background-color:pink">
	<th>ユーザID</th>
	<th>ユーザ名称</th>
	<th>ユーザPWD</th>
	<th>削除</th>
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
		<td>${e.delFlg == 0 ? '―' : '削除'}</td>
		<td><s:link href="detail/${e.usrId}">詳細 </s:link></td>
		<td><s:link href="registerEdit/${e.usrId}">編集</s:link></td>
		<td><s:link onclick="return confirm('delete OK?');" href="delete/${e.usrId}">削除</s:link></td>
	</tr>
</c:forEach>

</table>

</body>
</html>