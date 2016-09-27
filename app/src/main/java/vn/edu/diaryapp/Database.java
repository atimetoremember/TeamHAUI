package vn.edu.diaryapp;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import java.util.ArrayList;


public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DiaryDatabase";
    private static final int DATABASE_VERSION = 1;
    private String creatTable = "create table Diarytable ("
            + "Id integer primary key autoincrement,"
            + "Note text, "
            + "Date text);";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Cursor cur = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = 'Diarytable'", null);
        if (cur != null) {
            if (cur.getCount() > 0) {
                cur.close();
            } else {
                db.execSQL(creatTable);
            }
        } else {
            db.execSQL(creatTable);
        }


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(String note, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Note", note);
        cv.put("Date", date);
        long rt = db.insert("Diarytable", null, cv);
        db.close();
        return rt;
    }

    public void delete(int iD) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Diarytable", "Id = ?", new String[]{String.valueOf(iD)});
        db.close();
    }

    public void update(int iD, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Note", note);


        db.update("Diarytable", cv, "Id = ?", new String[]{String.valueOf(iD)});

        db.close();
    }

    public ArrayList<DiaryTable> getAllData() {
        DiaryTable diary;
        ArrayList<DiaryTable> listDiary = new ArrayList<DiaryTable>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.query("Diarytable", new String[]{"Id", "Note", "Date"}, null, null, null, null, null, null);
        if (cur != null) {
            cur.moveToFirst();
            while (cur.isAfterLast() == false) {
                diary = new DiaryTable();
                diary.setId(Integer.valueOf(cur.getString(0)));
                diary.setNote(cur.getString(1));
                diary.setDate(cur.getString(2));
                listDiary.add(diary);
                cur.moveToNext();

            }

        }

        return listDiary;
    }


}
