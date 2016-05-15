package com.example.modelsgame;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.example.modelsgame.R.drawable.button_shape;

/**
 * главный класс
 */


public class SingleGameActivity extends AppCompatActivity {

    int score = 0; // переменная для очков
    int qid = 1; //контроль количества вопросов. В каждом туре их четыре(пока что).
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
        String[] questionArray = new String[0];
        String[] answersArray = new String[0];
        if (tour == 1) {
            questionArray = getResources().getStringArray(R.array.questions); // тут мы из ресурсов достаем
            answersArray = getResources().getStringArray(R.array.answers);
        }
        if (tour == 2) {
            questionArray = getResources().getStringArray(R.array.questions2); // тут мы из ресурсов достаем
            answersArray = getResources().getStringArray(R.array.answers2);
        }
        if (tour == 3) {
            questionArray = getResources().getStringArray(R.array.questionsFinal); // тут мы из ресурсов достаем
            answersArray = getResources().getStringArray(R.array.answersFinal);
            randomNumber = 0;
        }
        Typeface mycustomfont = Typeface.createFromAsset(getAssets(), "fonts/9116.otf");

        scores = (TextView) findViewById(R.id.scores);
        questionTextView = (TextView) findViewById(R.id.textViewQuestion); //тут нам надо переменным реальные кнопки, в xml
        questionTextView.setTypeface(mycustomfont);
        buttonFirstAnswer = (Button) findViewById(R.id.buttonFirstAnswer); // файле мы для каждой кнопки id называем
        buttonFirstAnswer.setTypeface(mycustomfont);
        buttonSecondAnswer = (Button) findViewById(R.id.buttonSecondAnswer);// а тут мы его ищем и присваиваем методом findViewById
        buttonSecondAnswer.setTypeface(mycustomfont);
        buttonThirdAnswer = (Button) findViewById(R.id.buttonThirdAnswer);
        buttonThirdAnswer.setTypeface(mycustomfont);
        buttonFourthAnswer = (Button) findViewById(R.id.buttonFourthAnswer);
        buttonFourthAnswer.setTypeface(mycustomfont);

        buttonFirstAnswer.setBackgroundResource(R.drawable.button_shape);
        buttonSecondAnswer.setBackgroundResource(R.drawable.button_shape);
        buttonThirdAnswer.setBackgroundResource(R.drawable.button_shape);
        buttonFourthAnswer.setBackgroundResource(R.drawable.button_shape);

        questionTextView.setText(questionArray[randomNumber]);
        buttonFirstAnswer.setText(answersArray[randomNumber * 4]);
        buttonSecondAnswer.setText(answersArray[randomNumber * 4 + 1]);
        buttonThirdAnswer.setText(answersArray[randomNumber * 4 + 2]);
        buttonFourthAnswer.setText(answersArray[randomNumber * 4 + 3]);


        scores.setText(String.valueOf(score));

        return randomNumber;
    }

    void right1() //действия при правильном ответе в первом туре
    {

        Toast.makeText(getApplicationContext(), "Верный ответ", Toast.LENGTH_SHORT).show();
        score += 2;//прибавка очков
        if (qid < 5)
        {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    content();
                }
            }, 2000);

        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(SingleGameActivity.this);
            builder.setTitle("Тур завершен!")
                    .setMessage("Ваш текущий результат: "+ String.valueOf(score))
                    .setIcon(R.mipmap.ic_launcher)
                    .setCancelable(false)
                    .setNegativeButton("Следующий тур",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    qid = 0;
                                    ++tour;
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            content();
                                        }
                                    }, 2000);
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();

        }
    }

    void false1() //действия при неправильном ответе в первом туре
    {

        Toast.makeText(getApplicationContext(), "Неверный ответ", Toast.LENGTH_SHORT).show();
        score -= 1;//убавление очков
        if (qid < 5) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    content();
                }
            }, 2000);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(SingleGameActivity.this);
            builder.setTitle("Тур завершен!")
                    .setMessage("Ваш текущий результат: "+ String.valueOf(score))
                    .setIcon(R.mipmap.ic_launcher)
                    .setCancelable(false)
                    .setNegativeButton("Следующий тур",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    qid = 0;
                                    ++tour;
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            content();
                                        }
                                    }, 2000);
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();

        }
    }

    void right2() //действия при правильном ответе во втором туре
    {

        Toast.makeText(getApplicationContext(), "Верный ответ", Toast.LENGTH_SHORT).show();
        score += 4;//прибавка очков
        if (qid < 5) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    content();
                }
            }, 2000);

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(SingleGameActivity.this);
            builder.setTitle("Тур завершен!")
                    .setMessage("Ваш текущий результат: "+ String.valueOf(score))
                    .setIcon(R.mipmap.ic_launcher)
                    .setCancelable(false)
                    .setNegativeButton("Следующий тур",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    qid = 0;
                                    ++tour;
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            content();
                                        }
                                    }, 2000);
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();

        }
    }

    void false2() //действия при неправильном ответе во втором туре
    {

        Toast.makeText(getApplicationContext(), "Неверный ответ", Toast.LENGTH_SHORT).show();
        score -= 3;//убавление очков
        if (qid < 5) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    content();
                }
            }, 2000);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(SingleGameActivity.this);
            builder.setTitle("Тур завершен!")
                    .setMessage("Ваш текущий результат: "+ String.valueOf(score))
                    .setIcon(R.mipmap.ic_launcher)
                    .setCancelable(false)
                    .setNegativeButton("Следующий тур",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    qid = 0;
                                    ++tour;
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            content();
                                        }
                                    }, 2000);
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();

        }
    }

    void right3() //действия при правильном ответе в финале
    {

        score += 4;//прибавка очков
        Toast.makeText(getApplicationContext(), "Игра завершена", Toast.LENGTH_SHORT).show();
        Intent achieve = new Intent(SingleGameActivity.this, save_result.class);
        achieve.putExtra("tvRec", String.valueOf(score));
        startActivity(achieve);
        finish();

    }

    void false3() //действия при правильном ответе в финале
    {

        score -= 5;//убавление очков
        Toast.makeText(getApplicationContext(), "Игра завершена", Toast.LENGTH_SHORT).show();
        Intent achieve = new Intent(SingleGameActivity.this, save_result.class);
        achieve.putExtra("tvRec", String.valueOf(score));
        startActivity(achieve);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_single);

        randomNumber = random.nextInt(3);

        final String[] questionArray = getResources().getStringArray(R.array.questions); // тут мы из ресурсов достаем
        final String[] answersArray = getResources().getStringArray(R.array.answers);    // вопросы, ответы, и индексы правильных
        final int[] trueAnswers = getResources().getIntArray(R.array.trueAnswers);// ответов, они по адресу /res/values/
        final int[] trueAnswers2 = getResources().getIntArray(R.array.trueAnswers2);
        final int[] trueAnswersFinal = getResources().getIntArray(R.array.trueAnswersFinal);
        Typeface mycustomfont = Typeface.createFromAsset(getAssets(), "fonts/9116.otf");

        scores = (TextView) findViewById(R.id.scores);
        questionTextView = (TextView) findViewById(R.id.textViewQuestion); //тут нам надо переменным реальные кнопки, в xml
        questionTextView.setTypeface(mycustomfont);
        buttonFirstAnswer = (Button) findViewById(R.id.buttonFirstAnswer); // файле мы для каждой кнопки id называем
        buttonFirstAnswer.setTypeface(mycustomfont);
        buttonSecondAnswer = (Button) findViewById(R.id.buttonSecondAnswer);// а тут мы его ищем и присваиваем методом findViewById
        buttonSecondAnswer.setTypeface(mycustomfont);
        buttonThirdAnswer = (Button) findViewById(R.id.buttonThirdAnswer);
        buttonThirdAnswer.setTypeface(mycustomfont);
        buttonFourthAnswer = (Button) findViewById(R.id.buttonFourthAnswer);
        buttonFourthAnswer.setTypeface(mycustomfont);


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
                        if (tour == 1)
                        {
                            if (trueAnswers[randomNumber] == 0)
                            {
                               buttonFirstAnswer.setBackgroundColor(Color.GREEN);
                                right1();
                            }
                            else
                            {
                               buttonFirstAnswer.setBackgroundColor(Color.RED);
                                false1();
                            }
                            break;
                        }

                        if (tour == 2) {
                            if (trueAnswers2[randomNumber] == 0)
                            {
                                buttonFirstAnswer.setBackgroundColor(Color.GREEN);
                                right2();
                            }
                            else
                            {
                               buttonFirstAnswer.setBackgroundColor(Color.RED);
                                false2();
                            }
                            break;
                        }
                        if (tour == 3) {
                            if (trueAnswersFinal[randomNumber] == 1) {
                               buttonFirstAnswer.setBackgroundColor(Color.GREEN);
                                right3();
                            }
                            else
                            {
                                buttonFirstAnswer.setBackgroundColor(Color.RED);
                                false3();
                            }
                            break;
                        }
                        //break;
                    case R.id.buttonSecondAnswer:
                        if (tour == 1)
                        {
                            if (trueAnswers[randomNumber] == 1)
                            {
                                buttonSecondAnswer.setBackgroundColor(Color.GREEN);
                                right1();
                            }
                            else
                            {
                              buttonSecondAnswer.setBackgroundColor(Color.RED);
                                false1();
                            }
                            break;
                        }

                        if (tour == 2) {
                            if (trueAnswers2[randomNumber] == 1) {
                             buttonSecondAnswer.setBackgroundColor(Color.GREEN);
                                right2();
                            }
                            else
                            {
                              buttonSecondAnswer.setBackgroundColor(Color.RED);
                                false2();
                            }
                            break;
                        }
                        if (tour == 3) {
                            if (trueAnswersFinal[randomNumber] == 1) {
                               buttonSecondAnswer.setBackgroundColor(Color.GREEN);
                                right3();
                            }
                            else
                            {
                              buttonSecondAnswer.setBackgroundColor(Color.RED);
                                false3();
                            }
                            break;
                        }

                    case R.id.buttonThirdAnswer:
                        if (tour == 1)
                        {
                            if (trueAnswers[randomNumber] == 2)
                            {
                               buttonThirdAnswer.setBackgroundColor(Color.GREEN);
                                right1();
                            }
                            else
                            {
                               buttonThirdAnswer.setBackgroundColor(Color.RED);
                                false1();
                            }
                            break;
                        }

                        if (tour == 2) {
                            if (trueAnswers2[randomNumber] == 2) {
                              buttonThirdAnswer.setBackgroundColor(Color.GREEN);
                                right2();
                            }
                            else
                            {
                              buttonThirdAnswer.setBackgroundColor(Color.RED);
                                false2();
                            }
                            break;
                        }
                        if (tour == 3) {
                            if (trueAnswersFinal[randomNumber] == 2) {
                            buttonThirdAnswer.setBackgroundColor(Color.GREEN);
                                right3();
                            }
                            else
                            {
                              buttonThirdAnswer.setBackgroundColor(Color.RED);
                                false3();
                            }
                            break;
                        }
                        break;
                    case R.id.buttonFourthAnswer:
                        if (tour == 1)
                        {
                            if (trueAnswers[randomNumber] == 3)
                            {
                             buttonFourthAnswer.setBackgroundColor(Color.GREEN);
                                right1();
                            }
                            else
                            {
                              buttonFourthAnswer.setBackgroundColor(Color.RED);
                                false1();
                            }
                            break;
                        }

                        if (tour == 2) {
                            if (trueAnswers2[randomNumber] == 3) {
                              buttonFourthAnswer.setBackgroundColor(Color.GREEN);
                                right2();
                            }
                            else
                            {
                               buttonFourthAnswer.setBackgroundColor(Color.RED);
                                false2();
                            }
                            break;
                        }
                        if (tour == 3) {
                            if (trueAnswersFinal[randomNumber] == 3) {
                              buttonFourthAnswer.setBackgroundColor(Color.GREEN);
                                right3();
                            }
                            else {
                              buttonFourthAnswer.setBackgroundColor(Color.RED);
                                false3();
                            }
                            break;
                        }
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
