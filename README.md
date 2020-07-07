About Project
---
**開發環境：** Eclipse neon.2 release (4.6.2) \
**資料庫：** Firebase Realtime Database \
**Servlet容器：** apache-tomcat-8.5.56

說明
---
本專案為[舊版專案(Internet_Programming_Project)](https://github.com/kikihayashi/Internet_Programming_Project)的改良版 \
新增Firebase資料庫與部分Javascript、CSS語法。不同於舊專案在本機產生TXT檔，儲存使用者資料 \
而是使用Firebase資料庫來存放，不占用本機儲存空間。

執行成果
---
本專案執行成果[可點選這裡查看](https://drive.google.com/file/d/1fH4EER6vM-MO9XTmcc3zmswce-USgTre/view?usp=sharing)

程式邏輯
---
本專案使用MVC設計方式來模擬一個購物網站 \
使用者必須先"註冊"並"登入"後，才可在網頁中進行商品的"上架"與"購買" \
以下將介紹專案中每個程式的功能。

**【Controller (extends HttpServlet)】**\
Home：負責處理使用者進入"首頁"的工作，是最初程式進入點 \
Login：負責處理使用者"登入"的工作 \
Logout：負責處理使用者"登出"的工作(刪除所有cookie、HttpSession) \
Signup：負責處理使用者"註冊"的工作 \
UserBuy：負責處理使用者商品"購買"的工作 \
UserSell：負責處理使用者商品"上架"的工作

**【JavaBeans (implements Serializable)】**\
Buy：存放使用者"購買紀錄"資訊 \
Sell：存放使用者"上架商品"資訊 \
User：存放使用者"註冊"資訊

**【Model】**\
CheckCookies：如果使用者有在"登入"頁面點選"自動登入"，重啟瀏覽器進入(Login)時，將會取得瀏覽器的cookie資料，驗證後從Firebase中取資料 \
CheckData：此程式負責使用者註冊登入、上架商品、購買商品時，會遇到的各種驗證與計算 \
DataUpdate：當使用者"購買商品"時，"購買紀錄"、"上架商品"資料將會更動，此程式負責執行更新處理 \
InitCookies：在Home、UserSell中執行，如果使用者有在"登入"頁面點選"自動登入"，重啟瀏覽器進入(Home、UserSell)時，可維持登入狀態 \
MyDataBase：負責Firebase資料庫的初始化、存入、取出、移除動作(部分方法是泛型方法) \
SetToJavaBeans：將使用者的"註冊資料"、"上架商品"、"購買紀錄"資料存成JavaBeans格式

**【View】**\
addUserPage1.jsp：使用者第一個註冊頁面(設定姓名、學號、Email...等) \
addUserPage2.jsp：使用者第二個註冊頁面(設定帳號、密碼) \
addUserPage3.jsp：使用者第三個註冊頁面(顯示所有註冊資訊) \
buypage1.jsp：第一個購物頁面(顯示所有商品資訊，同時也是首頁) \
buypage2.jsp：第二個購物頁面(商品數量選擇、金額試算、確認購買) \
buypage3.jsp：第三個購物頁面(顯示本次購買商品的資訊、應付總金額) \
sellpage1.jsp：上架商品頁面 \
userInfoPage1.jsp：使用者登入頁面(輸入帳號、密碼、是否自動登入) \
userInfoPage2.jsp：使用者登入後頁面(顯示使用者個人資訊、購買紀錄) \
webStyle.css：負責將頁面格式統一化 \
sorttable.js：實現動態排序表格，參考第[三方套件](http://www.kryogenix.org/code/browser/sorttable/)

Source
---
**Maven in Eclipse：**\
https://crunchify.com/how-to-create-dynamic-web-project-using-maven-in-eclipse/ \
https://www.itread01.com/content/1541826970.html \
https://www.itread01.com/content/1543296122.html \
https://matthung0807.blogspot.com/2018/03/eclipse-maven-webdynamic-web-module-31.html \
http://www.slf4j.org/codes.html 

**Servlet/JSP：**\
https://openhome.cc/Gossip/ServletJSP/ \
https://dotblogs.com.tw/alantsai/Tags?qq=servlet \
https://dotblogs.com.tw/alantsai/Tags?qq=jsp

**Expression Language：**\
https://nanashi07.gitbooks.io/java-training/web.base/server/expression.language/ 

**Firebase：**\
https://firebase.google.com/docs/database/admin/save-data#java_9 \
https://www.youtube.com/watch?v=OvDZVV5CbQg \
https://stackoverflow.com/questions/33723139/wait-firebase-async-retrieve-data-in-android \
https://stackoverflow.com/questions/41372562/returning-a-value-in-firebase-ondatachange \
https://stackoverflow.com/questions/43576441/querying-data-from-firebase \
https://stackoverflow.com/questions/37390864/how-to-delete-from-firebase-realtime-database \
https://medium.com/firebase-developers/why-are-firebase-apis-asynchronous-callbacks-promises-tasks-e037a6654a93 \

**CountDownLatch：**\
https://ithelp.ithome.com.tw/articles/10208081 \
https://stackoverflow.com/questions/61200486/firebase-admin-sdk-on-tomcat-servlet \
https://kknews.cc/zh-tw/code/4o5z3q2.html 
