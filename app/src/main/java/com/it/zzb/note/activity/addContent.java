package com.it.zzb.note.activity;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.it.zzb.note.db.DbManager;
import com.it.zzb.note.db.NoteDb;
import com.it.zzb.note.R;
import com.it.zzb.note.model.Note;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class addContent extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private FloatingActionButton save;
    private DbManager dbManager;
    private int noteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content);
        init();

    }

    private void init(){
        dbManager = new DbManager(this);
        save = (FloatingActionButton) findViewById(R.id.save);
        editText = (EditText) findViewById(R.id.et);
        save.setOnClickListener(this);

        noteId = getIntent().getIntExtra("id",-1);
        if (noteId!=-1){
          showData(noteId);
          }
          setStatusBarColor();
    }

    //更新数据
    public void showData(int id){
        Note note = dbManager.search(id);
        editText.setText(note.getContent());
        //光标
        Spannable spannable = editText.getText();
        Selection.setSelection(spannable,editText.getText().length());

    }

    //获得时间
    public String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        String str = sdf.format(curDate);
        return str;
    }

    @Override
    public void onClick(View v) {
        String content = editText.getText().toString();
        String time = getTime();
        if (noteId == -1){
            dbManager.addToDb(content,time);
        }else{
            dbManager.update(noteId,content,time);
        }
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.finish();

    }

    //按返回键时
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.finish();
    }


     //设置状态栏同色
     public void setStatusBarColor() {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
             Window window = getWindow();
             window.setFlags(
                     WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                     WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
         }
         SystemBarTintManager tintManager = new SystemBarTintManager(this);
         tintManager.setStatusBarTintEnabled(true);
         tintManager.setTintColor(Color.parseColor("#3F51B5"));
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }
}
