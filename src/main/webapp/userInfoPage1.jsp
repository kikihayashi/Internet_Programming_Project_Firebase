<%--
* 
* JSTL與EL使用
* 執行此程式需要將 taglibs-standard-spec-1.2.5.jar、taglibs-standard-impl-1.2.5.jar
* 放入Web應用程式的 WEB-INF/lib 資料夾中才能執行
* 此兩檔案位於Tomcat的webapps\examples\WEB-IN\lib中。
*
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NTU &lt; Buy ● Sell &gt;</title>

<link rel="stylesheet" type="text/css" href="webStyle.css">
 
</head>
<body id="content_body">
	<div>
			<div id="content_div1">歡迎來到NTU &lt; Buy ● Sell &gt;請輸入您的資料來登入&nbsp;&nbsp;
			<a style=color:white href='Home'>( 回首頁 )<br><br></a></div>
		  
		  <form name="form1" method="post" action="Login">
		  		<%-- 顯示login_message的訊息 --%>
          		<h3 style="color:red">${login_message}</h3>
          	
					<div id="content_th2">帳號：<input name="ID" type="text" id="ID"></div><br>
					<div id="content_th2">密碼：<input name="PWD" type="password" id="PWD"></div>		
			<p>
				<label id="content_th2">自動登入<input type="checkbox" name="login_set" value="auto">&nbsp;&nbsp;
				<input id="content_th2" type="submit" name="page" value="登入"><br><br>
				<a id="content_th2" href='Signup'><font color="blue">註冊新帳號</font></a>&nbsp;&nbsp;	
				</label>
			</p>
		</form>
	</div>
</body>
</html>
