package cn.edu.gdmec.s07131018.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
	static final String DB_NAME = "PersonInfo";
	static final String TABLE_NAME = "Person";
	static int Version = 1;
	SQLiteDatabase db;
	Context context;
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_AGE = "age";
	public static final String KEY_HEIGHT = "height";

	final String Create_Table = "create table " + TABLE_NAME + "(" + KEY_ID
			+ " integer primary key autoincrement," + KEY_NAME
			+ " text not null," + KEY_AGE + " integer," + KEY_HEIGHT + " float"
			+ ")";

	public MyDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DB_NAME, factory, Version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL(Create_Table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		arg0.execSQL(sql);
		onCreate(arg0);
	}

	public long Insert(People people) {
		db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_NAME, people.name);
		contentValues.put(KEY_AGE, people.age);
		contentValues.put(KEY_HEIGHT, people.height);
		return db.insert(TABLE_NAME, null, contentValues);
	}

	public void Update(People people, long id) {
		db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_NAME, people.name);
		contentValues.put(KEY_AGE, people.age);
		contentValues.put(KEY_HEIGHT, people.height);
		db.update(TABLE_NAME, contentValues, "id=" + id, null);
	}

	public People QueryById(long id) {
		db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, "id=" + id, null, null,
				null, null);
		int count = cursor.getCount();
		if (cursor.moveToFirst() && count != 0) {
			People people = new People();
			people.id = cursor.getInt(0);
			people.name = cursor.getString(1);
			people.age = cursor.getInt(2);
			people.height = cursor.getFloat(3);
			return people;
		} else {
			return null;
		}
	}

	public People[] Query() {
		db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID, KEY_NAME,
				KEY_AGE, KEY_HEIGHT }, null, null, null, null, null);
		int count = cursor.getCount();
		if (count != 0 && cursor.moveToFirst()) {
			People peoples[] = new People[count];
			for (int i = 0; i < count; i++) {
				People people = new People();
				people.id = cursor.getInt(0);
				people.name = cursor.getString(1);
				people.age = cursor.getInt(2);
				people.height = cursor.getFloat(3);
				peoples[i] = people;
				cursor.moveToNext();
			}
			return peoples;
		} else {
			return null;
		}
	}

	public void Delete(long id) {
		db = this.getWritableDatabase();
		db.delete(TABLE_NAME, "id=" + id, null);
	}

	public void DeleteAll() {
		db = this.getWritableDatabase();
		db.delete(TABLE_NAME, null, null);
	}
}
