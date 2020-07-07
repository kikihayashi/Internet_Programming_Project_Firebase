package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/*      連接資料庫
 *      方法1：使用以下指令，需設置jsonpath(json檔案路徑)、databaseurl(Firebase專案網址路徑)
 */
//		String databaseurl = "https://user-database-2a086.firebaseio.com";
//		String jsonpath = "C:\\firebase_json\\user-database-2a086-firebase-adminsdk-rs21u-45bd5e57af.json";
//		FileInputStream serviceAccount = new FileInputStream(jsonpath);
//
//		FirebaseOptions options = new FirebaseOptions.Builder()
//													 .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//													 .setDatabaseUrl(databaseurl)
//													 .build();
//      //初始化FirebaseApp
//		FirebaseApp.initializeApp(options);
/*      
 *      方法2(官網推薦)：使用以下指令，需設置databaseurl(Firebase專案網址路徑)
 *	         然後去控制台>系統>進階系統設定>環境變數>系統變數中新增(變數名稱：GOOGLE_APPLICATION_CREDENTIALS、變數值：json檔案路徑)	
 *		json檔案是從Firebase的Settings>服務帳戶>產生新的金鑰得到
 */		
//		String databaseurl = "https://user-database-2a086.firebaseio.com";
//		FirebaseOptions options = new FirebaseOptions.Builder()
//												     .setCredentials(GoogleCredentials.getApplicationDefault())
//												     .setDatabaseUrl(databaseurl)
//												     .build();
//		//初始化FirebaseApp
//		FirebaseApp.initializeApp(options);


public class MyDataBase {
		
	public MyDataBase(String databaseurl)
	{	
		try//連接資料庫
		{
			FirebaseOptions options = new FirebaseOptions.Builder()
										 .setCredentials(GoogleCredentials.getApplicationDefault())
										 .setDatabaseUrl(databaseurl)
										 .build();
			
			FirebaseApp.initializeApp(options);//初始化FirebaseApp
		} 		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
		
	public static <T> void setToFirebase(DatabaseReference Ref, T object)
	{		
		Ref.setValueAsync(object);//如果本身沒有目錄，會自動生成
	} 
	
	public static void removeFromFirebase(DatabaseReference Ref)
	{		
		Ref.removeValueAsync();//移除此Ref目錄
	} 
	
	public static <T> void getFromFirebase(DatabaseReference Ref, String mission, Class<T> clazz, final JavaBeansCallback myCallback) 
	{			
		Ref.addListenerForSingleValueEvent(new ValueEventListener()
		{
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) 
			{	 
				List<T> javaBeansList = new ArrayList<>();
				
				switch (mission) 
				{
				case "getOne":			
					T javaBeans = (dataSnapshot != null)? dataSnapshot.getValue(clazz) : null;
					javaBeansList.add(javaBeans);
					break;

				case "getAll":		
					for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) 
					{
						javaBeansList.add(userSnapshot.getValue(clazz));
					}
					break;
				}
				myCallback.onCallback(javaBeansList);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) 
			{
				throw databaseError.toException();
			}
		});		
	}
	
	public interface JavaBeansCallback
	{
		<T> void onCallback(List<T> javaBeansList);
	}
}
