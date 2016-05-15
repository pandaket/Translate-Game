package com.example.modelsgame;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class save_result extends AppCompatActivity {

        Button btnAdd, btCancel;
        EditText etName;
        TextView tvRec;
        DatabaseHelper sqlHelper;
        SQLiteDatabase db;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_save_result);
            Intent intent = getIntent();
            String tvRes = intent.getStringExtra("tvRec");
            sqlHelper = new DatabaseHelper(getApplicationContext());
            db = sqlHelper.getWritableDatabase();
            btnAdd = (Button) findViewById(R.id.btAdd);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              ContentValues cv = new ContentValues();
                                              cv.put(DatabaseHelper.COLUMN_NAME, etName.getText().toString());
                                              cv.put(DatabaseHelper.COLUMN_RECORD, Integer.parseInt(tvRec.getText().toString()));
                                              db.insert(DatabaseHelper.TABLE, null, cv);
                                              db.close();
                                              finish();
                                          }
                                      });
            btCancel = (Button) findViewById(R.id.btCancel);
            btCancel.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v) {
                    finish();
                }
            });

            etName = (EditText) findViewById(R.id.etName);
            tvRec = (TextView) findViewById(R.id.tvRec);
            tvRec.setText(tvRes);
            sqlHelper = new DatabaseHelper(getApplicationContext());


        }
    }
