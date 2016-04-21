package com.example.modelsgame;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * главный класс
 */


public class SingleGameActivity extends AppCompatActivity {


    int score = 0; // переменная для очков
    int qid = 0; //контроль количества вопросов. В каждом туре их четыре(пока что).
    int tour = 1;   // номер тура, от этого зависит,
                    // какие именно вопросы и ответы подгружаются
                    // и сколько очков начисляется.
                    // 1 - 1 тур, 2 - 2 тур, 3 - финальный
    TextView scores; //очки, отображаемые на экране
    TextView questionTextView;      // переменная для вопроса
    Button buttonFirstAnswer, buttonSecondAnswer, // эобъявление кнопок для ответов
            buttonThirdAnswer, buttonFourthAnswer;
    View.OnClickListener listener;               // для обработки нажатия кнопок
    Random random = new Random();
    int randomNumber = random.nextInt(3);


    //@TargetApi(Build.VERSION_CODES.M)
    @TargetApi(Build.VERSION_CODES.M)
    int content()//загрузка вопроса/ответов для нового вопроса
    {

        ++qid;
        randomNumber = random.nextInt(3);

        final String[] questionArray = getResources().getStringArray(R.array.questions); // тут мы из ресурсов достаем
        final String[] answersArray = getResources().getStringArray(R.array.answers);    // вопросы, ответы,
                                                                                        // и индексы правильных ответов


        scores = (TextView) findViewById(R.id.scores);
        questionTextView = (TextView) findViewById(R.id.textViewQuestion);
        buttonFirstAnswer = (Button) findViewById(R.id.buttonFirstAnswer);
        buttonSecondAnswer = (Button) findViewById(R.id.buttonSecondAnswer);
        buttonThirdAnswer = (Button) findViewById(R.id.buttonThirdAnswer);
        buttonFourthAnswer = (Button) findViewById(R.id.buttonFourthAnswer);


        questionTextView.setText(questionArray[randomNumber]);
        buttonFirstAnswer.setText(answersArray[randomNumber * 4]);
        buttonSecondAnswer.setText(answersArray[randomNumber * 4 + 1]);
        buttonThirdAnswer.setText(answersArray[randomNumber * 4 + 2]);
        buttonFourthAnswer.setText(answersArray[randomNumber * 4 + 3]);

        scores.setText(String.valueOf(score));

        return randomNumber;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_single);

        randomNumber = random.nextInt(3);

        final String[] questionArray = getResources().getStringArray(R.array.questions); // тут мы из ресурсов достаем
        final String[] answersArray = getResources().getStringArray(R.array.answers);    // вопросы, ответы, и индексы правильных
        final int[] trueAnswers = getResources().getIntArray(R.array.trueAnswers);// ответов, они по адресу /res/values/

        scores = (TextView) findViewById(R.id.scores);
        questionTextView = (TextView) findViewById(R.id.textViewQuestion);
        buttonFirstAnswer = (Button) findViewById(R.id.buttonFirstAnswer);
        buttonSecondAnswer = (Button) findViewById(R.id.buttonSecondAnswer);
        buttonThirdAnswer = (Button) findViewById(R.id.buttonThirdAnswer);
        buttonFourthAnswer = (Button) findViewById(R.id.buttonFourthAnswer);


        questionTextView.setText(questionArray[randomNumber]);
        buttonFirstAnswer.setText(answersArray[randomNumber * 4]);
        buttonSecondAnswer.setText(answersArray[randomNumber * 4 + 1]);
        buttonThirdAnswer.setText(answersArray[randomNumber * 4 + 2]);
        buttonFourthAnswer.setText(answersArray[randomNumber * 4 + 3]);

        scores.setText(String.valueOf(score));
        score = 0;

        listener = new View.OnClickListener() {
            public void onClick(View v) {
                // листенер, который смотрит, что мы нажали,
                // каким цветом покрасить

                switch (v.getId()) {
                    case R.id.buttonFirstAnswer:
                        if (trueAnswers[randomNumber] == 0) {
                            //buttonFirstAnswer.setBackgroundColor(Color.GREEN);
                            Toast.makeText(getApplicationContext(), "Верный ответ", Toast.LENGTH_SHORT).show();
                            switch (tour) {                              //Заготовка для туров
                                case 1:
                                    score += 2;//прибавка очков
                                    if (qid < 5) {
                                        content();
                                    } else {
                                        Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                case 2:
                                    score += 4;//прибавка очков
                                    if (qid < 5) {
                                        content();
                                    } else {
                                        Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                case 3:
                                    score += 4;//прибавка очков
                                    if (qid < 5) {
                                        content();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Игра завершена", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                default:
                                    break;
                            }
                        } else

                        {
                            //buttonFirstAnswer.setBackgroundColor(Color.RED);
                            Toast.makeText(getApplicationContext(), "Неверный ответ", Toast.LENGTH_SHORT).show();
                            switch (tour) {
                                case 1:
                                    score = score - 1; //вычитание очков
                                    if (qid < 5)
                                        content();
                                    else {
                                        Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                case 2:
                                    score = score - 3; //вычитание очков
                                    if (qid < 5)
                                        content();
                                    else {
                                        Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                case 3:
                                    score = score - 5; //вычитание очков
                                    if (qid < 5)
                                        content();
                                    else {
                                        Toast.makeText(getApplicationContext(), "Игра завершена", Toast.LENGTH_SHORT).show();

                                    }
                                    break;
                                default:
                                    break;
                            }
                        }

                        break;
                    case R.id.buttonSecondAnswer:
                        if (trueAnswers[randomNumber] == 1) {
                            Toast.makeText(getApplicationContext(), "Верный ответ", Toast.LENGTH_SHORT).show();
                            //buttonSecondAnswer.setBackgroundColor(Color.GREEN);
                            switch (tour) {
                                case 1:
                                    score += 2;
                                    if (qid < 5)
                                        content();
                                    else {
                                        Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                case 2:
                                    score += 4;//прибавка очков
                                    if (qid < 5) {
                                        content();
                                    } else {
                                        Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                case 3:
                                    score += 4;//прибавка очков
                                    if (qid < 5) {
                                        content();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Игра завершена", Toast.LENGTH_SHORT).show();

                                    }
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            //buttonSecondAnswer.setBackgroundColor(Color.RED);
                            Toast.makeText(getApplicationContext(), "Неверный ответ", Toast.LENGTH_SHORT).show();
                            switch (tour) {
                                case 1:
                                    score = score - 1;
                                    if (qid < 5)
                                        content();
                                    else {
                                        Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                case 2:
                                    score = score - 3; //вычитание очков
                                    if (qid < 5)
                                        content();
                                    else {
                                        Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                case 3:
                                    score = score - 5; //вычитание очков
                                    if (qid < 5)
                                        content();
                                    else {
                                        Toast.makeText(getApplicationContext(), "Игра завершена", Toast.LENGTH_SHORT).show();

                                    }
                                    break;
                                default:
                                    break;
                            }
                        }

                        break;
                    case R.id.buttonThirdAnswer:
                        if (trueAnswers[randomNumber] == 2) {
                            Toast.makeText(getApplicationContext(), "Верный ответ", Toast.LENGTH_SHORT).show();
                            //buttonThirdAnswer.setBackgroundColor(Color.GREEN);
                            switch (tour) {
                                case 1:
                                    score += 2;
                                    if (qid < 5)
                                        content();
                                    else {
                                        Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                case 2:
                                    score += 4;//прибавка очков
                                    if (qid < 5) {
                                        content();
                                    } else {
                                        Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                case 3:
                                    score += 4;//прибавка очков
                                    if (qid < 5) {
                                        content();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Игра завершена", Toast.LENGTH_SHORT).show();

                                    }
                                    break;
                                default:
                                    break;
                            }

                        } else {
                            //buttonThirdAnswer.setBackgroundColor(Color.RED);
                            Toast.makeText(getApplicationContext(), "Неверный ответ", Toast.LENGTH_SHORT).show();
                                 switch (tour){
                                     case 1:
                                            score = score - 1;
                                            if (qid < 5)
                                                content();
                                            else {
                                                Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                                qid = 0;
                                                ++tour;
                                                content();
                                            }
                                               break;
                                     case 2:
                                         score = score - 3; //вычитание очков
                                         if (qid < 5)
                                             content();
                                         else {
                                             Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                             qid = 0;
                                             ++tour;
                                             content();
                                         }
                                         break;
                                     case 3:
                                         score = score - 5; //вычитание очков
                                         if (qid < 5)
                                             content();
                                         else {
                                             Toast.makeText(getApplicationContext(), "Игра завершена", Toast.LENGTH_SHORT).show();

                                         }
                                         break;
                                     default:
                                     break;
                                 }
                                }

                        break;
                    case R.id.buttonFourthAnswer:
                        if (trueAnswers[randomNumber] == 3) {
                            Toast.makeText(getApplicationContext(), "Верный ответ", Toast.LENGTH_SHORT).show();
                            //buttonFourthAnswer.setBackgroundColor(Color.GREEN);
                            switch (tour){
                                case 1:
                                    score += 2;
                                    if (qid<5)
                                        content();
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                case 2:
                                    score += 4;//прибавка очков
                                    if (qid < 5) {
                                        content();
                                    } else {
                                        Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                case 3:
                                    score += 4;//прибавка очков
                                    if (qid < 5) {
                                        content();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Игра завершена", Toast.LENGTH_SHORT).show();

                                    }
                                    break;
                                default:
                                    break;
                            }

                        } else
                        {
                            //buttonFourthAnswer.setBackgroundColor(Color.RED);
                            Toast.makeText(getApplicationContext(), "Неверный ответ", Toast.LENGTH_SHORT).show();
                            switch (tour){
                                case 1:
                                    score = score - 1;
                                    if (qid<5)
                                        content();
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                case 2:
                                    score = score - 3; //вычитание очков
                                    if (qid < 5)
                                        content();
                                    else {
                                        Toast.makeText(getApplicationContext(), tour+ "тур завершен", Toast.LENGTH_SHORT).show();
                                        qid = 0;
                                        ++tour;
                                        content();
                                    }
                                    break;
                                case 3:
                                    score = score - 5; //вычитание очков
                                    if (qid < 5)
                                        content();
                                    else {
                                        Toast.makeText(getApplicationContext(), "Игра завершена", Toast.LENGTH_SHORT).show();

                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
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
