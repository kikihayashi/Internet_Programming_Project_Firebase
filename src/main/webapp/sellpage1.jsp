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
			<div id="content_div1">歡迎來到 NTU &lt; Buy ● Sell &gt;${user.accountName}的上架商品頁面&nbsp;&nbsp;
			<a style=color:white href='Home'>( 回首頁 )</a>
			<a style=color:white href='Logout'>( 登出 )<br><br></a>
			</div>	
	<br>
		<form name="form1" method="post" action="Sell">
							
				<div align="left">商品名稱：<input name="name" type="text" id="name" required><br><br></div>
				<div align="left">上架數量：<input name="number" type="text" id="number" required><br><br></div>
				<div align="left">商品描述：<input name="discription" type="text" id="discription" required><br><br></div>
				<div align="left">單價金額：<input name="cost" type="text" id="cost" required></div>
				<%-- 顯示message的訊息 --%>			 			    
                <h3 style="color:red">${message}</h3>    
			<p>
				<label> 
				<input type="reset" name="reset" value="重設">&nbsp;&nbsp;
			    <input type="submit"name="page" value="上架商品" onclick="return confirm
			    ('確定上架嗎？\n\nNTU &lt; Buy ‧ Sell &gt;提醒您：\n\n商品上架後即無法再做更改！\n\n日後如遇買賣糾紛請自行處理，謝謝！');" >
				</label>				
			</p>
		</form>
	</div>
</body>
</html>
