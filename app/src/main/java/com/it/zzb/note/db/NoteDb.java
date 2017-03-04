package com.it.zzb.note.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zzb on 2017/2/23.
 */

public class NoteDb extends SQLiteOpenHelper {

    public static final String TABLE_NAME ="Note";
    public static  final String CONTENT ="content";
    public static final  String ID ="_id";
    public static final String TIME ="time";
    public static final int VERSION=1;

    public NoteDb(Context context){
        super(context,"note",null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CONTENT +" TEXT NOT NULL,"
                + TIME +" TEXT NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
