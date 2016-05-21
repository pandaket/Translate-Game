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
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class DoubleGameActivity extends AppCompatActivity {

    private NetworkMonitor mNetworkMonitor;
    private boolean Connect;
    String name = "";

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
        setContentView(R.layout.activity_main_single);

        mNetworkMonitor = new NetworkMonitor();
        IntentFilter intentFilter = new IntentFilter();
        //указываем, какие именно события надо получить
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        //регистрируем в системе
        registerReceiver(mNetworkMonitor,intentFilter);
        final EditText text_name = new EditText(this);
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