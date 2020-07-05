<%--
* 
* JSTL與EL使用
* 執行此程式需要將 taglibs-standard-spec-1.2.5.jar、taglibs-standard-impl-1.2.5.jar
* 放入Web應用程式的 WEB-INF/lib 資料夾中才能執行
* 此兩檔案位於Tomcat的webapps\examples\WEB-IN\lib中。
*
 --%>
<%@page import="JavaBeans.*"%>
<%@page contentType = "text/html" pageEncoding = "UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set target = "${pageContext.request}" property = "characterEncoding" value = "UTF-8"/>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NTU &lt; Buy ● Sell &gt;</title>

<link rel="stylesheet" type="text/css" href="webStyle.css">

</head>

<body id="content_body">
	<div>
		<div id="content_div1">
			商品確認，請確認購買的商品及數量是否正確！ 
			<a style="color: white" href='Home'>( 回首頁 )</a>
			<a style="color: white" href='Logout'>( 登出 )<br><br></a>
		</div><br>
	<form name="form" method="post" action="Buy">
		<table id="content_table2">
			<tr>
				<th id="content_th">&nbsp;商品名稱&nbsp;</th>
				<th id="content_th">&nbsp;單價金額&nbsp;</th>
				<th id="content_th">&nbsp;購買數量&nbsp;</th>
			</tr>

			<%--以下將商品資料，以迴圈方式輸入到表格上 --%>
			<c:set var="money"  value=''/>
		    <c:set var="name"   value=''/>
			<c:set var="seller" value=''/>
			<c:forEach var="i" begin="0" end="${fn:length(choose)-1}" step="1" varStatus="loop">
			
			    <c:set var="sell"   value="${sellList.get(Integer.valueOf(choose[i]))}"/>
				<c:set var="money"  value="${money += sell.item_cost.concat(',')}"/>
				<c:set var="name"   value="${name += sell.item_name.concat(',')}"/>
				<c:set var="seller" value="${seller += sell.item_seller.concat(',')}"/>
				<c:set var="color"  value="${((i%2) == 0) ? '#E3E3E3' : '#FAFAFA'}" />
				<tr style="background-color:${color}">
					<%------------------------商品名稱---------------%>
					<td><div align="center">${sell.item_name}</div></td>
					<%------------------------單價金額---------------%>
					<td><div align="center">${sell.item_cost}元</div></td>
					<%------------------------購買數量---------------%>
					<td><div align="center">
							<select name="buy">
							<option value="0">0</option>
					<c:forEach var="j" begin="1" end="${sell.item_sell_number}" step="1" varStatus="loop">
						
						
						<c:choose>
						<c:when test="${buy_number != null && buy_number[i]== j}">
								<%--顯示下拉式選單(預設值)--%>
								<option selected>${j}</option>
						</c:when>
						<c:otherwise>
								<%--顯示下拉式選單--%>
								<option>${j}</option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
							</select>
						</div>
					</td>
				</tr>
				
		    </c:forEach>
		    <c:set var = "money" value = "${money}" scope = "session"/>
		    <c:set var = "name" value = "${name}" scope = "session"/>
		    <c:set var = "seller" value = "${seller}" scope = "session"/>
		</table>
         
		<%-- 顯示message的訊息 --%>
		<h3 style="color: red">${message}</h3>
		<p>
			<label> 
			   <input type="submit" name="page" value="金額試算">
			   <input type="submit" name="page" value="確定購買" onclick="return confirm
			   ('確定購買嗎？\n\nNTU &lt; Buy ‧ Sell &gt;提醒您：\n\n確定購買後即無法再做更改！\n\n如需更改請自行連繫賣家，謝謝！');">
			</label>
		</p>
	</form>
	</div>
</body>
</html>