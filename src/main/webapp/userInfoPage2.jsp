<%--
* 
* JSTL與EL使用
* 執行此程式需要將 taglibs-standard-spec-1.2.5.jar、taglibs-standard-impl-1.2.5.jar
* 放入Web應用程式的 WEB-INF/lib 資料夾中才能執行
* 此兩檔案位於Tomcat的webapps\examples\WEB-IN\lib中。
*
 --%>
<%@page import="Model.*" language="java"%>
<%@page import="JavaBeans.*"%>
<%@page contentType = "text/html" pageEncoding = "UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set target = "${pageContext.request}" property = "characterEncoding" value = "UTF-8"/>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NTU &lt; Buy ● Sell &gt;</title>

<link rel="stylesheet" type="text/css" href="webStyle.css">

</head>
<body id="content_body2">
	<div>
			<div id="content_div1">現在來到&nbsp;NTU &lt; Buy ● Sell &gt;&nbsp;${user.accountName} 的個人資料區&nbsp;
			<a style=color:white href='Home'>( 回首頁 )</a>
			<a style=color:white href='Logout'>( 登出 )<br><br></a></div>	                      
     <h3>您的註冊資料：</h3>
		<table>
			<tr> 
                <th id="content_th">&nbsp;姓名&nbsp;</th>
                <th id="content_th">&nbsp;學號&nbsp;</th>
                <th id="content_th">&nbsp;手機&nbsp;</th>
                <th id="content_th">&nbsp;信箱&nbsp;</th>
                <th id="content_th">&nbsp;學院&nbsp;</th>
                <th id="content_th">&nbsp;學歷&nbsp;</th>
			</tr>
			<tr>	
				<td><div  style=background-color:#FAFAFA align="center">&nbsp;${user.name}&nbsp;</div></td>				 
				<td><div  style=background-color:#FAFAFA align="center">&nbsp;${user.sid}&nbsp;</div></td>					
				<td><div  style=background-color:#FAFAFA align="center">&nbsp;${user.phone}&nbsp;</div></td>
				<td><div  style=background-color:#FAFAFA align="center">&nbsp;${user.mail}&nbsp;</div></td>			
				<td><div  style=background-color:#FAFAFA align="center">&nbsp;${user.edu}&nbsp;</div></td>			
				<td><div  style=background-color:#FAFAFA align="center">&nbsp;${user.grade}&nbsp;</div></td>
			</tr>
		</table>
		
		<c:if test="${buyList.size() != 0}">
		 	<h3>您的購買記錄：</h3>
		<table> 
			 <tr>
			    <th id="content_th">&nbsp;購買時間&nbsp;</th>		                     
                <th id="content_th">&nbsp;商品名稱&nbsp;</th>
                <th id="content_th">&nbsp;購買數量&nbsp;</th>
                <th id="content_th">&nbsp;賣家名稱&nbsp;</th>
                <th id="content_th">&nbsp;總共花費&nbsp;</th>	
			 </tr>
			
			<c:forEach var="i" begin="0" end="${buyList.size()-1}" step="1" varStatus="loop">
				<c:set var="buy"   value="${buyList.get(i)}"/>
				<c:set var="color" value="${((i%2) == 0) ? '#E3E3E3' : '#FAFAFA'}" />
			<tr style="background-color:${color}">
			    <%-------------------------購買時間---------------%>
				<td><div align="center">${buy.item_time}</div></td>
				<%-------------------------商品名稱---------------%>
				<td><div align="center">${buy.item_name}</div></td> 
				<%-------------------------購買數量---------------%>
				<td><div align="center">${buy.item_buy_number}</div></td>
				<%-------------------------賣家名稱---------------%>
				<td><div align="center">${buy.item_seller}</div></td>
				<%-------------------------總共花費---------------%>
				<td><div align="center">${buy.item_cost}元</div></td>
			</tr>	 
			</c:forEach>	
		</table>
		</c:if>       		
	</div>
</body>
</html>