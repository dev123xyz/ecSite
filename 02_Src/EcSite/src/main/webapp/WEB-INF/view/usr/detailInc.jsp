<h2>ユーザ情報(基本)</h2>

<!-- 詳細情報 -->
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
			${f:h(usrFormItem.usrName)}
			<html:hidden property="usrFormItem.usrName" />
		</td>	
	</tr>
	<tr>
		<th>ユーザPWD</th>
		<td>
			${f:h(usrFormItem.usrPwd)}
			<html:hidden property="usrFormItem.usrPwd" />
		</td>	
	</tr>
	<tr>
		<th>有効/無効</th>
		<td>
			${usrFormItem.invalidFlg == 0 ? '有効' : '無効'}
			<html:hidden property="usrFormItem.invalidFlg" />
		</td>	
	</tr>
</table>

<html:hidden property="usrFormItem.updUserId" />
<html:hidden property="usrFormItem.updDate" />
