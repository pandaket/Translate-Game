package com.example.modelsgame;

/**
 * Created by Екатерина on 14.05.2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBConnector {

    // Данные базы данных и таблиц
    private static final String DATABASE_NAME = "results.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Achievements";

    // Название столбцов
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_RESULT = "Result";


    // Номера столбцов
    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_NAME = 1;
    private static final int NUM_COLUMN_RESULT = 2;


    private SQLiteDatabase mDataBase;

    public DBConnector(Context context) {
        // открываем (или создаем и открываем) БД для записи и чтения
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }
    // Метод добавления строки в БД
    public long insert(RecordsConstruct md) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME, md.getName());
        cv.put(COLUMN_RESULT, md.getResult());
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    // Метод редактирования строки в БД
    public int update(RecordsConstruct md) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME, md.getName());
        cv.put(COLUMN_RESULT, md.getResult());
        return mDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[] {String.valueOf(md.getID()) });
    }

    // Метод удаления всех записей из БД
    public int deleteAll() {
        return mDataBase.delete(TABLE_NAME, null, null);
    }

    // Метод удаления записи
    public void delete(int id) {
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }

    // Метод выборки одной записи
    public RecordsConstruct select(int id) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, COLUMN_NAME);

        mCursor.moveToFirst();

        String name = mCursor.getString(NUM_COLUMN_NAME);
        String result = mCursor.getString(NUM_COLUMN_RESULT);
        mCursor.close();
        return new RecordsConstruct(id, name, result);
    }

    // Метод выборки всех записей
    public ArrayList<RecordsConstruct> selectAll() {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, COLUMN_NAME);

        ArrayList<RecordsConstruct> arr = new ArrayList<RecordsConstruct>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                int id = mCursor.getInt(NUM_COLUMN_ID);
                String name = mCursor.getString(NUM_COLUMN_NAME);
                String result = mCursor.getString(NUM_COLUMN_RESULT);
                arr.add(new RecordsConstruct(id, name, result));
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        return arr;
    }

    // Класс для создания БД
    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);	}
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_RESULT + " TEXT ); ";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
