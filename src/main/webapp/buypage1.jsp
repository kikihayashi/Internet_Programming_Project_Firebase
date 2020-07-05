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
<c:set target = "${pageContext.request}" property = "characterEncoding" value = "UTF-8"/>

<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NTU &lt; Buy ● Sell &gt;</title>

<link rel="stylesheet" type="text/css" href="webStyle.css">

<script src="sorttable.js"></script>
<style type="text/css">
/* Sortable tables */
table.sortable thead {
	background-color: #eee;
	color: #666666;
	font-weight: bold;
	cursor: default;
}
</style>

</head>
<body id="content_body">
	<form name="form" method="post" action="Buy">
	
    <c:set var="condition"  value="${('Yes' == isAuto || 'Logged_in' == state)}"/>
	<c:set var="option1"    value="${condition ? 'Hello！'+= user.accountName : '( 登入 )'}" />
	<c:set var="option2"    value="${condition ? '( 登出 )' : '( 註冊 )'}" />
	<c:set var="option2URL" value="${condition ? 'Logout' : 'Signup'}" />
	
	<%---------顯示首頁訊息 --------%>
	<div id="content_div1">歡迎來到NTU &lt; Buy ● Sell &gt;這是一個給台大生自由買賣的免費平台！&nbsp; 
		<a style=color:white href='Login'>${option1}</a> 
	    <a style=color:white href="${option2URL}">${option2}</a>
	</div>
	
	<br><br>
	
	<c:if test="${condition}">
    	<a style="color:black;font-family:Microsoft JhengHei;font-size:1.2em" href='Sell'>&nbsp;上架商品&nbsp;</a>
    </c:if>

    <%-- 顯示message的訊息 --%>
	<a style="color:red;font-family:Microsoft JhengHei;font-size:1.2em">&nbsp;${message}</a>
	  
	<br><br>
	<c:choose>
	
	<c:when test="${sellList.size() == 0}">
	    <h3 style="color:red">現在沒有任何商品了，上架商品成為第一個賣家吧！</h3>
	</c:when>
	
	<c:otherwise>
	<table class="sortable" id=content_table>
			<tr>
				<th id="content_th">&nbsp;商品編號&nbsp;</th>
				<th id="content_th"><font color="white">&nbsp;商品名稱&nbsp;</font></th>
				<th id="content_th"><font color="white">&nbsp;剩餘數量&nbsp;</font></th>
				<th id="content_th"><font color="white">&nbsp;相關描述&nbsp;</font></th>
				<th id="content_th"><font color="white">&nbsp;賣家名稱&nbsp;</font></th>
				<th id="content_th"><font color="white">&nbsp;單價金額&nbsp;</font></th>

				<c:if test="${condition}">
					<%--這裡的item_NO可以判斷使用者選了哪一些商品 --%>
					<th id="content_th"><font color="white">&nbsp;選擇商品&nbsp;</font></th>
				</c:if>
			</tr>

	<c:forEach var="i" begin="0" end="${sellList.size()-1}" step="1" varStatus="loop">
	    <c:set var="sell" value="${sellList.get(i)}"/>
			<tr>
				<%-------------------------商品編號---------------%>
				<td><div align="center">${(i+1)}</div></td>
				<%-------------------------商品名稱---------------%>
				<td><div align="center">${sell.item_name}</div></td> 
				<%-------------------------剩餘數量---------------%>
				<c:set var="numbercolor" value="${(sell.item_sell_number =='1')? 'red' : 'black'}"/>
				<td><div align="center" style="color:${numbercolor}">${sell.item_sell_number}</div></td>
				<%-------------------------相關描述---------------%>
				<td><div align="left">&nbsp;${sell.item_discription}&nbsp;</div></td>
				<%-------------------------賣家名稱---------------%>
				<td><div align="center">${sell.item_seller}</div></td>
				<%-------------------------單價金額---------------%>
				<td><div align="center">${sell.item_cost}元</div></td>
				
				<c:if test="${condition}">
					<%--這裡的item_NO可以判斷使用者選了哪一些商品 --%>
					<td><div align="center"><input type="checkbox" name="choose" value="${i}"></div></td>
				</c:if>
			</tr>
	</c:forEach>
	</table>
	
		<c:if test="${condition}">
			<p>  
				<label> 			
				    <input style="color:black;font-family: Microsoft JhengHei" type="reset" name="reset" value="重新選擇">&nbsp;&nbsp;
					<input style="color:black;font-family: Microsoft JhengHei" type="submit"name="page"  value="加入購物車">				    
				</label>
			</p>   	
		</c:if>
	</c:otherwise>
	
	</c:choose>
</form>
</body>
</html>
