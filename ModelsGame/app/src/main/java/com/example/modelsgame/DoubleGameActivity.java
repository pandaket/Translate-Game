package com.example.modelsgame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class DoubleGameActivity extends AppCompatActivity {

    private NetworkMonitor mNetworkMonitor;
    private boolean Connect;
    String name = "";
    TextView name_player1;
    Button createServer_btn, createConnect_btn;
    View.OnClickListener listener;


    void Connect()
    {
        mNetworkMonitor = new NetworkMonitor();

        IntentFilter intentFilter = new IntentFilter();
        //указываем, какие именно события нам нужны (подключений)
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        //регестрируем в системе
        registerReceiver(mNetworkMonitor, intentFilter);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_game);
//------------------------Регистрируем доступность сети
//        mNetworkMonitor = new NetworkMonitor();
//        IntentFilter intentFilter = new IntentFilter();
//        //указываем, какие именно события надо получить
//        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        //регистрируем в системе
//        registerReceiver(mNetworkMonitor,intentFilter);
//-------------------
        //создаем объекты кнопок из интерфейса
        createServer_btn = (Button) findViewById(R.id.btn_create_server);
        createConnect_btn = (Button) findViewById(R.id.btn_create_client);



        //слушатель кнопок
        listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_create_server:
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "server!", Toast.LENGTH_SHORT);
                        toast.show();

                        break;
                    case R.id.btn_create_client:
                        Toast toast2 = Toast.makeText(getApplicationContext(),
                                "client", Toast.LENGTH_SHORT);
                        toast2.show();
                        break;
                }

            }
        };
        //узнаем имя пользователя
        getName_player1();

        //отреагируем на нажатие кнопок
        createConnect_btn.setOnClickListener(listener);
        createServer_btn.setOnClickListener(listener);
    }


public void getName_player1()
{
    //создаем EditText для того, что пользователь ввел свое имя
    final EditText text_name = new EditText(this);

    //конструкция для диалога с пользователем
    AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
    myAlert.setTitle("Надо познакомиться.")
            .setMessage("Введите свое имя: ")
            .setView(text_name)
            .setCancelable(false)
            .setNegativeButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            name = text_name.getText().toString();
                        }
                    });
    AlertDialog alert = myAlert.create();
    alert.show();
}

    @Override
    public void onStop() {
        unregisterReceiver(mNetworkMonitor);
        super.onStop();
    }

}