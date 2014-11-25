package cn.edu.gdmec.s07131018.sqlitedemo;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText et1,et2,et3,et4;
	private TextView tv;
	private MyDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        tv = (TextView) findViewById(R.id.textView5);
        dbHelper = new MyDBHelper(this, null, null, 1);
    }
    public void QueryById(View view){
    	tv.setText("");
    	String id = et4.getText().toString();
    	People people = dbHelper.QueryById(Long.valueOf(id));
    	tv.setText(people.toString());
    }
    public void QueryAll(View view){
    	tv.setText("");
    	String string = "";
    	People people [] = dbHelper.Query();
    	for(int i = 0;i < people.length;i++){
    		string+=people[i].toString();
    	}
    	tv.setText(string);
    }
    public void DeleteAll(View view){
    	tv.setText("");
    	dbHelper.DeleteAll();
    	tv.setText("删除成功");
    }
    public void UpdateById(View view){
    	tv.setText("");
    	String name = et1.getText().toString();
    	int age = Integer.valueOf(et2.getText().toString());
    	float height = Float.valueOf(et3.getText().toString());
    	People people = new People();
    	people.name=name;
    	people.age=age;
    	people.height=height;
    	dbHelper.Update(people, Long.valueOf(et4.getText().toString()));
    	tv.setText("更新成功");
    }
    public void Create(View view){
    	tv.setText("");
    	String name = et1.getText().toString();
    	int age = Integer.valueOf(et2.getText().toString());
    	float height = Float.valueOf(et3.getText().toString());
    	People people = new People();
    	people.name=name;
    	people.age=age;
    	people.height=height;
    	if(dbHelper.Insert(people)!=-1){
    		tv.setText("插入成功");
    	}
    	else{
    		tv.setText("插入失败");
    	}
    }
    public void DeleteById(View view){
    	tv.setText("");
    	long id = Long.valueOf(et4.getText().toString());
    	dbHelper.Delete(id);
    	tv.setText("删除成功");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
