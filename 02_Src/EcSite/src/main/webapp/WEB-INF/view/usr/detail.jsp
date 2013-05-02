<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>ユーザ情報詳細</title>
    <link rel="stylesheet" type="text/css" href="${f:url('/css/EcSite.css')}"/>
    <link rel="stylesheet" type="text/css" href="${f:url('/css/global.css')}"/>
</head>
<body>

<h1>ユーザ情報詳細</h1>

<html:errors/>

<s:form>

<jsp:include page="./detailInc.jsp" flush="true"/>

<h2>ユーザ情報(注文履歴)</h2>

<table class="tablebg" cellpadding='5px' cellspacing="1px" >
<!-- ユーザ情報(注文履歴)ヘッダ -->
	<tr>
		<th>注文ID</th>
		<th>注文日時</th>
		<th>注文商品名称</th>
		<th>注文数量</th>
		<th>注文単価(円)</th>
	</tr>

<!-- ユーザ情報(注文履歴)ボディ -->
<c:forEach var="valOrders" varStatus="sttOrders" items="${usrFormItem.ordersFormItemList}">
	<tr>
		<td rowspan="${valOrders.ordersDtlFormItemList.size()}" >${f:h(valOrders.orderId)}</td>
		<td rowspan="${valOrders.ordersDtlFormItemList.size()}" >${f:h(valOrders.orderDate)}</td>
		<c:forEach var="valOrdersDtl" varStatus="sttOrdersDtl" items="${valOrders.ordersDtlFormItemList}">
			<c:if test="${sttOrdersDtl.first == false}" >
				<tr>
			</c:if>
				<td>${f:h(valOrdersDtl.goods.goodsName)}</td>
				<td>${f:h(valOrdersDtl.orderGoodsNum)}</td>
				<td>${f:h(valOrdersDtl.orderGoodsUnitPrice)}</td>
			</tr>
		</c:forEach>
	<c:if test="${valOrders.ordersDtlFormItemList.size() == 0}" >
		</tr>
	</c:if>
</c:forEach>
</table>

</s:form>

</body>
</html>