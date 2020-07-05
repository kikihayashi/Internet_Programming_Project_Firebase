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

<link href="webStyle.css" rel="stylesheet" type="text/css">

</head>
<body id="content_body2">
	<div>
			<div id="content_div1">歡迎來到 NTU &lt; Buy ● Sell &gt; 註冊頁面-Page1&nbsp;&nbsp;
			<a style=color:white href='Home'>( 回首頁 )<br><br></a></div>
		<br>
		<form name="form1" method="post" action="Signup">
									
                    <div id="content_th2">姓名：<input name="name" type="text" id="name" required></div>
		<br>		
					<div id="content_th2">學號：<input name="sid" type="text" id="sid" required></div>
		<br>		
					<div id="content_th2">手機：<input type="tel" id="phone" name="phone"  maxlength="10" 
                          						 pattern="[0][9][0-9]{8}" placeholder="09xxxxxxxx" required></div>
		<br>		
					<div id="content_th2">信箱：<input name="mail" type="email" id="mail" required></div>
		<br>		
					<div id="content_th2">學院：<br>
							<input type="radio" name="edu" value="文學院"required>文學院 
							<input type="radio" name="edu" value="工學院">工學院
							<input type="radio" name="edu" value="理學院">理學院
							<input type="radio" name="edu" value="醫學院">醫學院
							<input type="radio" name="edu" value="管理學院">管理學院
							<input type="radio" name="edu" value="法律學院">法律學院
							<input type="radio" name="edu" value="社會科學院">社會科學院	<br>				
							<input type="radio" name="edu" value="生命科學院">生命科學院
							<input type="radio" name="edu" value="電機資訊學院">電機資訊學院
							<input type="radio" name="edu" value="公共衛生學院">公共衛生學院
							<input type="radio" name="edu" value="生物資源暨農學院">生物資源暨農學院</div>
		<br>		
				    <div id="content_th2">學歷：
				            <select required name="grade" >
				            <option value=""></option>
		                    <option value="大一" >大一</option>	
		                    <option value="大二">大二</option>	
		                    <option value="大三">大三</option>	
		                    <option value="大四">大四</option>	
		                    <option value="碩士">碩士</option>	
		                    <option value="博士">博士</option>	
		                    <option value="教授">教授</option>			                      
		                    </select></div>
			<p>
				<label> <input id="content_th2" type="reset" name="reset" value="重設">&nbsp;&nbsp;
					<input id="content_th2" type="submit" name="page" value="下一頁"><br><br>
				</label>
			</p>
		</form>
	</div>
</body>
</html>
