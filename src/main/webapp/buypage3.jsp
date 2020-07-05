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
	<div id="content_div1">恭喜您買到本商品，我們會盡速聯絡賣家發貨！<br><br></div><br>
		
    <form name="form" method="post" action="Buy">
	  <table id="content_table2">
		 <tr>
             <th id="content_th">商品名稱</th>
             <th id="content_th">賣家名稱</th>
             <th id="content_th">購買數量</th>
         </tr>
	<%--以下將商品資料，以迴圈方式輸入到表格上 --%> 
	<c:set var = "buyInfo" value = "${money}" scope = "session"/> 
	<c:set var="j" value="0"/>        
	<c:forEach var="i" begin="0" end="${fn:length(choose)-1}" step="1" varStatus="loop">  
	   <c:set var="sell" value="${sellList.get(Integer.valueOf(choose[i]))}"/>    
	   
	   <c:if test="${buy_number[i] != '0'}">
	   	<c:set var="color" value="${((j%2) == 0) ? '#E3E3E3' : '#FAFAFA'}"/>  
	   	<c:set var="j" value="${j+1}"/>
	     <tr style="background-color:${color}">
	         <%------------------------商品名稱---------------%>
		     <td><div align="center">${sell.item_name}</div></td>
		     <%------------------------賣家名稱---------------%>
		     <td><div align="center">${sell.item_seller}</div></td>	
		     <%------------------------購買數量---------------%>
		     <td><div align="center">${buy_number[i]}</div></td>			 
         </tr>  
        
       </c:if>                  
    </c:forEach>              
      </table>     
         <%-- 顯示message的訊息 --%>
         <h3 style="color:red">本次購買${message}</h3>    
		 <p>
			<label> 
			  <a style="color:black;font-family: Microsoft JhengHei" href='Home'>回首頁繼續購物！</a>
			</label>			
	    </p>
	</form>
    </div>
   </body>
 </html>