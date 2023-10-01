package com.example.myapplication3;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    Button mBtnAdd, mBtnRead, mBtnClear,mBtnEdit;
    EditText mEdtName, mEdtEmail,mEdtId;
    TextView readRows;
    DBHelper mDbHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnAdd = (Button) findViewById(R.id.buttonAdd);
        mBtnAdd.setOnClickListener(this::onClick);
        mBtnRead = (Button) findViewById(R.id.buttonRead);
        mBtnRead.setOnClickListener(this::onClick);
        mBtnClear = (Button) findViewById(R.id.buttonClear);
        mBtnClear.setOnClickListener(this::onClick);
        mEdtId = (EditText) findViewById(R.id.editTextId);
        mEdtName = (EditText) findViewById(R.id.editTextName);
        mEdtEmail = (EditText) findViewById(R.id.editTextEmail);
        readRows = (TextView) findViewById(R.id.textViewReadRows);
        mDbHelper = new DBHelper(this);
    }
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
// создаем объект для данных
        ContentValues cv = new ContentValues();

// получаем данные из полей ввода
        String name = mEdtName.getText().toString();
        String email = mEdtEmail.getText().toString();
        String id = mEdtId.getText().toString();
        cv.put("name", name);
        cv.put("email", email);
        cv.put("created", LocalDate.now().toString());
// подключаемся к БД
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        if (v.getId() == R.id.buttonAdd){
            Log.d(LOG_TAG, "--- Insert in mytable: ---");

            long rowID = db.insert("mytable", null, cv);
            Log.d(LOG_TAG, "row inserted, ID = " + rowID);
        }
        if (v.getId() == R.id.buttonRead){
            Log.d(LOG_TAG, "--- Rows in mytable: ---");
            Cursor c = db.query("mytable", null, null, null, null, null, null);
            if (c.moveToFirst()) {
                int idColIndex = c.getColumnIndex("id");
                int nameColIndex = c.getColumnIndex("name");
                int emailColIndex = c.getColumnIndex("email");
                int dateColIndex = c.getColumnIndex("created");
                String log ="";
                do {
                    log+=
                            "ID = " + c.getInt(idColIndex) + ", name = "
                                    + c.getString(nameColIndex) + ", email = "
                                    + c.getString(emailColIndex) + ", created by = "
                                    + c.getString(dateColIndex)
                                    + "\n";
                } while (c.moveToNext());
                readRows.setText(log);
            } else

                c.close();
        }
        if (v.getId() == R.id.buttonClear){
            Log.d(LOG_TAG, "--- Clear mytable: ---");
            int clearCount = db.delete("mytable", null, null);
            Log.d(LOG_TAG, "deleted rows count = " + clearCount);
            readRows.setText(" ");
            db.execSQL("Delete FROM mytable");
        }
        // Хотя вроде лучше всегда держать открытым
        mDbHelper.close();
    }
    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "email text,"
                    + "created text"
                    + ");");
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
        }
    }
}

