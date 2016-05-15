package com.example.modelsgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by Екатерина on 14.04.2016.
 */
public class AchievementsActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_achievement);

       // mDBConnector = new DBConnector (this);
       // mListView = (ListView)findViewById(R.id.list);
        // Адаптер списка
       // mAdapter = new myListAdapter(mContext);
        // Передаем в адаптер массив объектов MyData, которые необходимо вывести
        //mAdapter.setArrayMyData(mDBConnector.selectAll());
       // mListView.setAdapter(mAdapter);

    }
}


