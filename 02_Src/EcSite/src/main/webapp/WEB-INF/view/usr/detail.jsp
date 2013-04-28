<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>ユーザ情報詳細</title>
    <link rel="stylesheet" type="text/css" href="${f:url('/css/global.css')}"/>
</head>
<body>

<h1>ユーザ情報詳細</h1>

<html:errors/>

<s:form>

<!-- 詳細情報 -->
<table class="tablebg">
	<tr>
		<td>ユーザID</td>
		<td>
		    <bean:write name="usrFormItem" property="usrId" />
		    <html:hidden property="usrFormItem.usrId" />
		</td>
	</tr>
	<tr>
		<td>ユーザ名称</td>
		<td>
		    <html:text property="usrFormItem.usrName" />
		</td>	
	</tr>
	<tr>
		<td>ユーザPWD</td>
		<td>
		    <html:text property="usrFormItem.usrPwd" />
		</td>	
	</tr>
	<tr>
		<td>削除</td>
		<td>
		    ${usrFormItem.delFlg == 0 ? '―' : '削除'}
		</td>	
	</tr>
</table>

<input type="submit" name="update" value="UPDATE" />
</s:form>

<br/><br/>

<s:link href="/usr/">list page</s:link>

</body>
</html>