package com.gc.x03_rwinrom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.x03_rwinrom.R;



public class MainActivity extends Activity {

    private EditText et_name;
	private EditText et_password;



	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        et_name = (EditText) findViewById(R.id.name);
    	et_password = (EditText) findViewById(R.id.password);
    	
    	readAccount();
    }

    
	public void readAccount(){
		//内部读写
		//File file = new File("data/data/com.gc.x03_rwinrom/info.txt");
		
		//File file = new File(getCacheDir(),"info.txt");
		//data/data/com.gc.x03_rwinrom/cache/info.txt
		//內存不足时，系统有可能清空cache文件夹，图片等可缓存在此
		
		//data/data/com.gc.x03_rwinrom/files/info.txt
		File file = new File(getFilesDir(),"info.txt");
		if(file.exists()){
				
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//字节流转换成字符流
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			try {
				String text = br.readLine();
				String[] s = text.split("##");
				et_name.setText(s[0]);
				et_password.setText(s[1]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
//		***************************************************************
//		//使用sharedpreferences 来读用户名和密码（等零散信息，键值对存取）
//		SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
//		String name = sp.getString("name", "");
//		String password = sp.getString("password", "");
//	
//	    ***************************************************************
	
	}
	
	
    public void login(View v){
    	
    	CheckBox cb = (CheckBox) findViewById(R.id.cb);
    	
    	String name = et_name.getText().toString();
    	String password = et_password.getText().toString();
    	
    	if(cb.isChecked()){
    		//外部（sd）读写                只改路径，添加权限
    		//File file = new File(Environment.getExternalStorageDirectory(),"info.txt");
    		//一般存储路径为sdcard/info.txt   mmt/sdcard/info.txt   storage/sdcard/info.txt
    		// 注意：外部（sd）读写需要读写权限    
    		//读：android.permission.READ_EXTERNAL_STORAGE
    		//写：android.permission.WRITE_EXTERNAL_STORAGE
    		
    		//加判断，如果sd卡得状态是为可用状态，则执行下列读写语句
//    		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//    			
//    		}
//    	
    		File file = new File(getFilesDir(),"info.txt");
    		FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
				fos.write((name + "##" + password).getBytes());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    	
//		**************************************************************	
//		//使用sharedpreferences 来保存用户名和密码（等零散信息，键值对存取）
//    	SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
//    	//取得编辑器
//    	Editor ed = sp.edit();
//    	ed.putString("name", name);
//    	ed.putString("password", password);
//    	ed.commit();
//    	**************************************************************
    	}
    	Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
    }
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
