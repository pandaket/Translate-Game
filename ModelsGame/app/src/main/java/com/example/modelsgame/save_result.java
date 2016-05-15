package com.example.modelsgame;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class save_result extends AppCompatActivity implements View.OnClickListener{

        Button btnAdd, btCancel;
        EditText etName;
        TextView tvRec;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_save_result);
            Intent intent = getIntent();
            String tvRes = intent.getStringExtra("tvRec");
            btnAdd = (Button) findViewById(R.id.btAdd);
            btnAdd.setOnClickListener(this);

            btCancel = (Button) findViewById(R.id.btCancel);
            btCancel.setOnClickListener(this);

            etName = (EditText) findViewById(R.id.etName);
            tvRec = (TextView) findViewById(R.id.tvRec);
            tvRec.setText(tvRes);
            DBConnector mDBConnector = new DBConnector (this);
        }

        @Override
        public void onClick(View v) {


            finish();
        }
    }
