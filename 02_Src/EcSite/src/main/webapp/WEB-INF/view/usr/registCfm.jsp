<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>ユーザ情報登録確認</title>
    <link rel="stylesheet" type="text/css" href="${f:url('/css/EcSite.css')}"/>
    <link rel="stylesheet" type="text/css" href="${f:url('/css/global.css')}"/>
</head>
<body>

<h1>ユーザ情報登録確認</h1>

<html:errors/>

<s:form>

<jsp:include page="./detailInc.jsp" flush="true"/>

<p></p>
<input type="submit" name="registExe" value="登録" />

</s:form>

</body>
</html>