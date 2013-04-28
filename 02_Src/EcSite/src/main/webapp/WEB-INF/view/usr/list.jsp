<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${f:url('/css/global.css')}"/>
</head>
<body>

<html:errors/>


<table border="1">
<tr style="background-color:pink">

	<th>usrName</th>
	<th>usrPwd</th>
	<th>updUserId</th>
	<th>delFlg</th>
	<th>updDate</th>
<th></th><th></th><th></th>
</tr>

<c:forEach var="e" varStatus="s" items="${usrItems}">
	<tr style="background-color:${s.index %2 == 0 ? 'white' : 'aqua'}">
		<td>
			${f:h(e.usrName)}
		</td>
		<td>
			${f:h(e.usrPwd)}
		</td>
		<td>
			${f:h(e.updUserId)}
		</td>
		<td>
			${f:h(e.delFlg)}
		</td>
		<td>
			${f:h(e.updDate)}
		</td>
		<td><s:link href="show/${e.usrId}"> show </s:link></td>
		<td><s:link href="edit/${e.usrId}"> edit </s:link></td>
		<td><s:link onclick="return confirm('delete OK?');" href="delete/${e.usrId}">delete</s:link></td>
	</tr>
</c:forEach>

</table>

<s:link href="create"> create new Object </s:link>
</body>
</html>