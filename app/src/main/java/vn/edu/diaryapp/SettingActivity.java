package vn.edu.diaryapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingActivity extends Activity implements View.OnClickListener {
    private int iD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setting);
        RadioButton rbBackground = (RadioButton) findViewById(R.id.rbBackground);
        RadioButton rbBackground2 = (RadioButton) findViewById(R.id.rbBackground2);
        RadioButton rbBackground3 = (RadioButton) findViewById(R.id.rbBackground3);

        Button btOk = (Button) findViewById(R.id.btOk);
        Button btCancel = (Button) findViewById(R.id.btCancel);
        Intent it = getIntent();
        iD = it.getIntExtra("sendId", 1);
        if (iD == 0) {
            rbBackground.setChecked(true);


        } else {
            if (iD == 1) {
                rbBackground2.setChecked(true);
            } else {
                rbBackground3.setChecked(true);
            }
        }
        btOk.setOnClickListener(this);
        btCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        switch (v.getId()) {
            case R.id.btOk:
                int radioId = rg.getCheckedRadioButtonId();
                View radioButton = rg.findViewById(radioId);
                int index = rg.indexOfChild(radioButton);


                Intent it = getIntent();
                it.putExtra("iDBackground", index);

                setResult(1, it);
                finish();
                break;
            case R.id.btCancel:
                Intent itC = getIntent();
                itC.putExtra("iDBackground", iD);
                setResult(1, itC);
                finish();


        }
    }
}
