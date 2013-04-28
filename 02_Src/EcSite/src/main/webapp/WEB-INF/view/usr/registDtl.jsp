<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${f:url('/css/global.css')}"/>
</head>
<body>

<html:errors/>

<s:form>
        <html:hidden property="usrId" />
<table class="tablebg">
	<tr>
		<td> usrName </td>
		<td>
		    <html:text property="usrName" />
		</td>	
	</tr>
	<tr>
		<td> usrPwd </td>
		<td>
		    <html:text property="usrPwd" />
		</td>	
	</tr>
	<tr>
		<td> updUserId </td>
		<td>
		    <html:text property="updUserId" />
		</td>	
	</tr>
	<tr>
		<td> delFlg </td>
		<td>
		    <html:text property="delFlg" />
		</td>	
	</tr>
	<tr>
		<td> updDate </td>
		<td>
		    <html:text property="updDate" />
		</td>	
	</tr>

</table>

<input type="submit" name="update" value="UPDATE" />
</s:form>
<br/><br/>

<s:link href="/usr/">list page</s:link>

</body>
</html>