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
<body id="content_body2">
	<div>
	
			<div id="content_div1">現在來到 NTU &lt; Buy ● Sell &gt; 註冊頁面-Page2&nbsp;&nbsp;
			<a style=color:white href='Home'>( 回首頁 )<br><br></a></div>
	<br>
		<form name="form1" method="post" action="Signup">

          <%-- 顯示register_message的訊息 --%>
          <h3 style="color:red">${register_message}</h3>
			
				<div id="content_th2" align="left">設定登入帳號：<input name="ID" type="text" id="ID" required></div>
		<br>	
				<div id="content_th2" align="left">設定登入密碼：<input name="PWD" type="text" id="PWD" required></div>
			<p>
				<label> 
				    <input id="content_th2" type="reset" name="reset" value="重設">&nbsp;&nbsp;
					<input id="content_th2" type="submit" name="page" value="送出"><br><br>
				</label>				
			</p>
		</form>
	</div>
</body>
</html>
