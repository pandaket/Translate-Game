package com.example.modelsgame;

/**
 * Created by Екатерина on 14.04.2016.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainPage extends AppCompatActivity {
    Button singleGame, doubleGame, achievements; //Кнопки Одиночной и Двойной игр, Достижений
    View.OnClickListener listener;               // для обработки нажатия кнопок

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

         singleGame = (Button)findViewById(R.id.btn_single_game);
         doubleGame = (Button)findViewById(R.id.btn_double_game);
         achievements = (Button)findViewById(R.id.btn_achievements);



        listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_single_game:
                        Intent singleG = new Intent(MainPage.this, SingleGameActivity.class);
                        startActivity(singleG);
                        break;
                    case R.id.btn_double_game:
                        Intent doubleG = new Intent(MainPage.this, DoubleGameActivity.class);
                        startActivity(doubleG);
                        break;
                    case R.id.btn_achievements:
                        Intent achieve = new Intent(MainPage.this, AchievementsActivity.class);
                        startActivity(achieve);
                        break;
                    default:
                        break;
                }

            }

        };
        // устанавливаем один обработчик для всех кнопок
        singleGame.setOnClickListener(listener);
        doubleGame.setOnClickListener(listener);
        achievements.setOnClickListener(listener);
    }
}
