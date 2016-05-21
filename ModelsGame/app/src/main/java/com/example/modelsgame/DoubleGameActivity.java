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
    private Button mButtonOpen  = null;
    private Button mButtonSend  = null;
    private Button mButtonClose = null;
    private LaptopServer mServer = null;
    private static final String LOG_TAG = "myServerApp";

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
        mNetworkMonitor = new NetworkMonitor();
        IntentFilter intentFilter = new IntentFilter();
        //указываем, какие именно события надо получить
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        //регистрируем в системе
        registerReceiver(mNetworkMonitor,intentFilter);
//-------------------
        //создаем объекты кнопок из интерфейса
        createServer_btn = (Button) findViewById(R.id.btn_create_server);
        createConnect_btn = (Button) findViewById(R.id.btn_create_client);


        /////////

        mButtonOpen = (Button) findViewById(R.id.button_open_connection);
        mButtonSend = (Button) findViewById(R.id.button_send_connection);
        mButtonClose = (Button) findViewById(R.id.button_close_connection);
        mButtonSend.setEnabled(false);
        mButtonClose.setEnabled(false);

        mButtonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        /* создаем объект для работы с сервером*/
                mServer = new LaptopServer();
        /* Открываем соединение. Открытие должно происходить в отдельном потоке от ui */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mServer.openConnection();
                    /*
                        устанавливаем активные кнопки для отправки данных
                        и закрытия соедиения. Все данные по обновлению интерфеса должны
                        обрабатывается в Ui потоке, а так как мы сейчас находимся в
                        отдельном потоке, нам необходимо вызвать метод  runOnUiThread()
                    */
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    mButtonSend.setEnabled(true);
                                    mButtonClose.setEnabled(true);
                                }
                            });
                        } catch (Exception e) {
                            Log.e(LOG_TAG, e.getMessage());
                            mServer = null;
                        }
                    }
                }).start();
            }
        });

        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mServer == null) {
                    Log.e(LOG_TAG, "Сервер не создан");
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                    /* отправляем на сервер данные */
                            mServer.sendData("Send text to server".getBytes());
                        } catch (Exception e) {
                            Log.e(LOG_TAG, e.getMessage());
                        }
                    }
                }).start();
            }
        });


        mButtonClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
        /* Закрываем соединение */
                mServer.closeConnection();
        /* устанавливаем неактивными кнопки отправки и закрытия */
                mButtonSend.setEnabled(false);
                mButtonClose.setEnabled(false);
            }
        });
        /////////

        //слушатель кнопок
        listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_create_server:
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "server!", Toast.LENGTH_SHORT);
                        toast.show();
                        //поднимаем сервер
                        Server server = Server.getServer();
                        Thread t = new Thread(server);
                        t.start();
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