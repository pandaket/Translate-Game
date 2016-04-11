package com.example.modelsgame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * главный класс
 */


public class MainActivity extends AppCompatActivity {


    TextView questionTextView;      // переменная для вопроса
    Button buttonFirstAnswer, buttonSecondAnswer, // эобъявление кнопок для ответов
            buttonThirdAnswer, buttonFourthAnswer;
    View.OnClickListener listener;               // для обработки нажатия кнопок

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_single);


        Random random = new Random();
        //int randomNumber = random.nextInt(100);
        final int randomNumber = 0;

        String[] questionArray = getResources().getStringArray(R.array.questions); // тут мы из ресурсов достаем
        String[] answersArray = getResources().getStringArray(R.array.answers);    // вопросы, ответы, и индексы правильных
        final int[] trueAnswers =  getResources().getIntArray(R.array.trueAnswers);// ответов, они по адресу /res/values/


        questionTextView = (TextView) findViewById(R.id.textViewQuestion); //тут нам надо переменным реальные кнопки, в xml
        buttonFirstAnswer = (Button) findViewById(R.id.buttonFirstAnswer); // файле мы для каждой кнопки id называем
        buttonSecondAnswer = (Button) findViewById(R.id.buttonSecondAnswer);// а тут мы его ищем и присваиваем методом findViewById
        buttonThirdAnswer = (Button) findViewById(R.id.buttonThirdAnswer);
        buttonFourthAnswer = (Button) findViewById(R.id.buttonFourthAnswer);



        questionTextView.setText(questionArray[randomNumber]);              //
        buttonFirstAnswer.setText(answersArray[randomNumber * 4]);
        buttonSecondAnswer.setText(answersArray[randomNumber * 4 + 1]);
        buttonThirdAnswer.setText(answersArray[randomNumber * 4 + 2]);
        buttonFourthAnswer.setText(answersArray[randomNumber * 4 + 3]);


        listener = new View.OnClickListener() {                             // листенер, который смотрит, что мы нажали,
            @Override                                                       // и чекает, правильно или нет, и в зависимости от этого
            public void onClick(View v) {                                   // говорит каким цветом покрасить
                switch (v.getId()){
                    case R.id.buttonFirstAnswer:
                        Log.d("qwe", "qwe");
                        if (trueAnswers[randomNumber] == 0)
                            buttonFirstAnswer.setBackgroundColor(Color.GREEN);
                        else
                            buttonFirstAnswer.setBackgroundColor(Color.RED);
                        break;
                    case R.id.buttonSecondAnswer:
                        if (trueAnswers[randomNumber] == 1)
                            buttonSecondAnswer.setBackgroundColor(Color.GREEN);
                        else
                            buttonSecondAnswer.setBackgroundColor(Color.RED);
                        break;
                    case R.id.buttonThirdAnswer:
                        if (trueAnswers[randomNumber] == 2)
                            buttonThirdAnswer.setBackgroundColor(Color.GREEN);
                        else
                            buttonThirdAnswer.setBackgroundColor(Color.RED);
                        break;
                    case R.id.buttonFourthAnswer:
                        if (trueAnswers[randomNumber] == 3)
                            buttonFourthAnswer.setBackgroundColor(Color.GREEN);
                        else
                            buttonFourthAnswer.setBackgroundColor(Color.RED);
                        break;
                    default:
                        break;

                }

            }
        };

        buttonFirstAnswer.setOnClickListener(listener);
        buttonSecondAnswer.setOnClickListener(listener);
        buttonThirdAnswer.setOnClickListener(listener);
        buttonFourthAnswer.setOnClickListener(listener);

    }


}
