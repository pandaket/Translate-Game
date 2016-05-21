package com.example.modelsgame;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Гульнара on 21.05.2016.
 */


public class NetworkMonitor extends BroadcastReceiver {
    private String LOG_TAG = "myNetworkMonitor";

    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(LOG_TAG, action);
        //следит за состоянием сети
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //для получения описания состояния сети
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (!isConnected)
            return;
        boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        Log.d(LOG_TAG,"isWiFi: "+isWiFi);
        if (!isWiFi)
        {
            AlertDialog.Builder myAlert = new AlertDialog.Builder(context);
        myAlert.setTitle("Ошибка сети")
                .setMessage("Network disable :( ")
                .setCancelable(false)
                .setNegativeButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent mainPage = new Intent(context, MainPage.class);
                                context.startActivity(mainPage);
                            }
                        });
        AlertDialog alert = myAlert.create();
        alert.show();
        }

    }

}
