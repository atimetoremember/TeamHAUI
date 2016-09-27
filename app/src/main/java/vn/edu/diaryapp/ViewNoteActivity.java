package vn.edu.diaryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;


public class ViewNoteActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_viewnote);
        Intent it = getIntent();
        Bundle bun = it.getExtras();
        TextView tvDateDiary = (TextView) findViewById(R.id.tvDateDiary);
        TextView tvNote = (TextView) findViewById(R.id.tvNote);
        tvDateDiary.setText(bun.getString("date"));
        tvNote.setText(bun.getString("note"));


    }
}
