<%--
* 
* JSTL與EL使用
* 執行此程式需要將 taglibs-standard-spec-1.2.5.jar、taglibs-standard-impl-1.2.5.jar
* 放入Web應用程式的 WEB-INF/lib 資料夾中才能執行
* 此兩檔案位於Tomcat的webapps\examples\WEB-IN\lib中。
*
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="JavaBeans.*"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NTU &lt; Buy ● Sell &gt;</title>

<link rel="stylesheet" type="text/css" href="webStyle.css">

</head>
<body id="content_body2">

	<div>

			<div id="content_div1">現在來到 NTU &lt; Buy ● Sell &gt; 註冊頁面-Page3&nbsp;&nbsp;
			<a style=color:white href='Home'>( 回首頁 )<br><br></a></div>
    <br>
				<div id="content_th2">
				帳號：${user.accountName}<br>			
				密碼：${user.password}<br>			
				姓名：${user.name}<br>			
				學號：${user.sid}<br>			
				手機：${user.phone}<br>			
				信箱：${user.mail}<br>			
				學院：${user.edu}<br>			
				學歷：${user.grade}
				</div>				
		<p>
		  <label> 
		        <a id="content_th2" href='Login'>恭喜你完成註冊，按這裡登入即可開始買東西囉！</a>    
		  </label>

		</p>
	</div>
</body>
</html>

