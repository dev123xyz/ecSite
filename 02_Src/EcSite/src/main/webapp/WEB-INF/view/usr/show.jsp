<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${f:url('/css/global.css')}"/>
</head>
<body>

<html:errors/>

<table class="tablebg">
	<tr>
		<td> usrName </td>
		<td>
			${f:h(usrName)}
		</td>	
	</tr>
	<tr>
		<td> usrPwd </td>
		<td>
			${f:h(usrPwd)}
		</td>	
	</tr>
	<tr>
		<td> updUserId </td>
		<td>
			${f:h(updUserId)}
		</td>	
	</tr>
	<tr>
		<td> delFlg </td>
		<td>
			${f:h(delFlg)}
		</td>	
	</tr>
	<tr>
		<td> updDate </td>
		<td>
			${f:h(updDate)}
		</td>	
	</tr>

</table>

<s:link href="edit/${usrId}"> edit </s:link>


<br/><br/>
<s:link href="/usr/">list page</s:link>
</body>
</html>