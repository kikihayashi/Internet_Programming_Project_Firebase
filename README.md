About Project
---
**開發環境：** Eclipse neon.2 release (4.6.2) \
**Servlet容器：** apache-tomcat-8.5.56

說明
---
本專案為[舊版專案(Internet_Programming_Project)](https://github.com/kikihayashi/Internet_Programming_Project)的改良版 \
不同於舊專案在本機產生TXT檔儲存使用者資料，而是使用Firebase資料庫來存放 \
即使本機關機、資料依然存在於Firebase中。

執行成果
---
本專案執行成果[可點選這裡查看](https://drive.google.com/file/d/1fH4EER6vM-MO9XTmcc3zmswce-USgTre/view?usp=sharing)

程式邏輯：
---
本專案使用MVC設計方式來模擬一個購物網站，使用者必須先"註冊"並"登入"後，才可在網頁中進行商品的"上架"與"購買" \
以下將介紹專案的每個程式功能。

**【Controller(extends HttpServlet)】**

Home：負責處理使用者進入首頁的工作，是最初程式進入點 \
Login：負責處理使用者登入的工作 \
Logout：負責處理使用者登出的工作(刪除所有cookie、HttpSession) \
Signup：負責處理使用者註冊的工作 \
UserBuy：負責處理使用者商品購買的工作 \
UserSell：負責處理使用者商品上架的工作

**【JavaBeans(implements Serializable)】**
Buy：儲存使用者"購買紀錄"資訊 \
Sell：儲存使用者"上架商品"資訊 \
User：儲存使用者"註冊"資訊

**【Model】**

CheckCookies：如果使用者有在"登入"頁面點選"自動登入"，此程式將會取得瀏覽器的cookie資料、驗證，並從Firebase中取資料 \
CheckData：此程式負責使用者註冊登入、上架商品、購買商品時，會遇到的各種驗證與計算 \
DataUpdate：當使用者"購買商品"時，"購買紀錄"、"上架商品"資料將會更動，此程式負責執行更新處理 \
InitCookies：在Home、UserSell中執行，如果使用者之前有在"登入"頁面點選"自動登入"，瀏覽器直接進入(Home、UserSell)時，可維持登入狀態 \
MyDataBase：負責Firebase資料庫的初始化、存入、取出、移除動作(部分方法是泛型方法) \
SetToJavaBeans：將使用者的"註冊資料"、"上架商品"、"購買紀錄"資料存成JavaBeans格式

**【View】**


Source
---
**客製Dialog：**\
https://medium.com/@makkenasrinivasarao1/android-custom-dialog-with-list-of-items-ba1ab0e78e16

**GridLayoutManager使用：**\
https://www.journaldev.com/13792/android-gridlayoutmanager-example

**Mobile Vision Text API使用：**\
https://codelabs.developers.google.com/codelabs/mobile-vision-ocr/#0 \
https://www.luoow.com/dc_tw/100377642 \
https://www.youtube.com/watch?v=rXvtNlX_5E0 \
https://www.youtube.com/watch?v=xoTKpstv9f0
