package vn.edu.diaryapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AddNoteActivity extends Activity implements View.OnClickListener {
    EditText etAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_addnote);
        etAddNote = (EditText) findViewById(R.id.etAddNote);
        Button btFinish = (Button) findViewById(R.id.btFinish);
        Button btCancel = (Button) findViewById(R.id.btCancell);
        btFinish.setOnClickListener(this);
        btCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btFinish:
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String date = sdf.format(cal.getTime());
                Database diaryDB = new Database(this);
                diaryDB.insert(etAddNote.getText().toString(), date);
                finish();
                break;
            case R.id.btCancell:
                finish();
        }
    }
}
