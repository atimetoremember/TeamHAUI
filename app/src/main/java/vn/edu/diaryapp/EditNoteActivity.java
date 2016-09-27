package vn.edu.diaryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditNoteActivity extends Activity implements View.OnClickListener {
    private Bundle bun;
    private EditText etAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_addnote);
        etAddNote = (EditText) findViewById(R.id.etAddNote);
        Button btFinish = (Button) findViewById(R.id.btFinish);
        Button btCancel = (Button) findViewById(R.id.btCancell);
        Intent it = getIntent();
        bun = it.getExtras();
        etAddNote.setText(bun.getString("note"));
        btFinish.setOnClickListener(this);
        btCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btFinish:
                Database diaryDB = new Database(getApplicationContext());
                diaryDB.update(bun.getInt("id"), etAddNote.getText().toString());

                finish();
                break;
            case R.id.btCancell:
                finish();
        }
    }
}
