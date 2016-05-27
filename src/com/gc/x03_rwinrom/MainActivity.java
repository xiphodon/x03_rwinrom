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
		//�ڲ���д
		//File file = new File("data/data/com.gc.x03_rwinrom/info.txt");
		
		//File file = new File(getCacheDir(),"info.txt");
		//data/data/com.gc.x03_rwinrom/cache/info.txt
		//�ȴ治��ʱ��ϵͳ�п������cache�ļ��У�ͼƬ�ȿɻ����ڴ�
		
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
			//�ֽ���ת�����ַ���
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
//		//ʹ��sharedpreferences �����û��������루����ɢ��Ϣ����ֵ�Դ�ȡ��
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
    		//�ⲿ��sd����д                ֻ��·�������Ȩ��
    		//File file = new File(Environment.getExternalStorageDirectory(),"info.txt");
    		//һ��洢·��Ϊsdcard/info.txt   mmt/sdcard/info.txt   storage/sdcard/info.txt
    		// ע�⣺�ⲿ��sd����д��Ҫ��дȨ��    
    		//����android.permission.READ_EXTERNAL_STORAGE
    		//д��android.permission.WRITE_EXTERNAL_STORAGE
    		
    		//���жϣ����sd����״̬��Ϊ����״̬����ִ�����ж�д���
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
//		//ʹ��sharedpreferences �������û��������루����ɢ��Ϣ����ֵ�Դ�ȡ��
//    	SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
//    	//ȡ�ñ༭��
//    	Editor ed = sp.edit();
//    	ed.putString("name", name);
//    	ed.putString("password", password);
//    	ed.commit();
//    	**************************************************************
    	}
    	Toast.makeText(this, "��½�ɹ�", Toast.LENGTH_SHORT).show();
    }
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
