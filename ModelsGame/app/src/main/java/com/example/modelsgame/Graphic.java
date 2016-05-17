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
import java.lang.Object;

/**
 * Created by Гульнара on 17.05.2016.
 */
public class Graphic extends AppCompatActivity {

    void btn_design(Button btn) //подгружаем оформление кнопки
    {
        btn.setBackgroundResource(R.drawable.button_shape);
        btn.setTextColor(Color.argb(255,99,51,254));
    }
    void btn_set_font(Button btn) //  Меняем шрифт
    {
        Typeface mycustomfont = Typeface.createFromAsset(getAssets(), "fonts/9116.otf"); //адрес файла с шрифтом
        btn.setTypeface(mycustomfont);
    }

    void button_color_draw(boolean check,Button Action_btn) //меняем цвет фона и текста нажатой кнопки
    {
        if (check)
        {
            Action_btn.setBackgroundResource(R.drawable.button_shape_green);
            Action_btn.setTextColor(Color.WHITE);
        }
        else
        {
            Action_btn.setBackgroundResource(R.drawable.button_shape_red);
            Action_btn.setTextColor(Color.WHITE);
        }
    }

}
