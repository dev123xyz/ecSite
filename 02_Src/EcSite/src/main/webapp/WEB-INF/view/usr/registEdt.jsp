<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>ユーザ情報登録</title>
    <link rel="stylesheet" type="text/css" href="${f:url('/css/EcSite.css')}"/>
    <link rel="stylesheet" type="text/css" href="${f:url('/css/global.css')}"/>
</head>
<body>

<h1>ユーザ情報登録</h1>

<html:errors/>

<s:form>
<table class="tablebg" cellpadding='5px' cellspacing="1px" >
	<tr>
		<th>ユーザID</th>
		<td>
			${f:h(usrFormItem.usrId)}
			<html:hidden property="usrFormItem.usrId" />
		</td>
	</tr>
	<tr>
		<th>ユーザ名称</th>
		<td>
			<html:text property="usrFormItem.usrName" />
		</td>	
	</tr>
	<tr>
		<th>ユーザPWD</th>
		<td>
			<html:password property="usrFormItem.usrPwd" value="" />
		</td>	
	</tr>
	<c:if test="${usrFormItem.usrId.length() != 0}" >
	<tr>
		<th>有効/無効</th>
		<td>
			<html:radio property="usrFormItem.invalidFlg" value="0" />有効
			<html:radio property="usrFormItem.invalidFlg" value="1" />無効
		</td>	
	</tr>
	</c:if>
</table>

<html:hidden property="usrFormItem.updUserId" />
<html:hidden property="usrFormItem.updDate" />

<p></p>
<input type="submit" name="registCfm" value="登録確認" />

</s:form>

<br/><br/>

</body>
</html>