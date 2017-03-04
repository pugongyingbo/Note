package com.it.zzb.note.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.it.zzb.note.model.Note;

import java.util.List;

/**
 * Created by zzb on 2017/2/25.
 */

public class DbManager {
    private Context context;
    private NoteDb noteDb;
    private SQLiteDatabase dbWriter;
    private SQLiteDatabase dbReader;
    private static DbManager instance;

    public DbManager(Context context){
        this.context = context;
        noteDb = new NoteDb(context);
        dbReader = noteDb.getReadableDatabase();
        dbWriter = noteDb.getWritableDatabase();
    }
    public static synchronized DbManager getInstance(Context context){
        if (instance == null){
            instance = new DbManager(context);
        }
        return instance;
    }
    //添加数据
    public void addToDb(String content,String time){
        ContentValues cv = new ContentValues();
        cv.put(NoteDb.CONTENT,content);
        cv.put(NoteDb.TIME,time);
        dbWriter.insert(NoteDb.TABLE_NAME,null,cv);
    }
    //读取数据
    public void readFromDb(List<Note> noteList){
        Cursor cursor = dbReader.query(NoteDb.TABLE_NAME,null,null,null,null,null,null);
        try {
            while (cursor.moveToNext()){
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(NoteDb.ID)));
                note.setContent(cursor.getString(cursor.getColumnIndex(NoteDb.CONTENT)));
                note.setTime(cursor.getString(cursor.getColumnIndex(NoteDb.TIME)));
                noteList.add(note);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //更新数据
    public void update(int id,String content,String time){
        ContentValues cv = new ContentValues();
        cv.put(NoteDb.ID,id);
        cv.put(NoteDb.CONTENT,content);
        cv.put(NoteDb.TIME,time);
        dbWriter.update(NoteDb.TABLE_NAME,cv,"_id= ?",new String[]{id + ""});
    }
    //删除数据
    public void delete(int id){
        dbWriter.delete(NoteDb.TABLE_NAME,"_id = ?",new String[]{id + ""});
    }
    //查询数据
    public Note search(int id){
        Cursor cursor = dbReader.rawQuery("SELECT * FROM note WHERE _id = ?", new String[]{id + ""});
        cursor.moveToFirst();
        Note note = new Note();
        note.setId(cursor.getInt(cursor.getColumnIndex(NoteDb.ID)));
        note.setContent(cursor.getString(cursor.getColumnIndex(NoteDb.CONTENT)));
        note.setTime(cursor.getString(cursor.getColumnIndex(NoteDb.TIME)));
        return note;
    }
}
