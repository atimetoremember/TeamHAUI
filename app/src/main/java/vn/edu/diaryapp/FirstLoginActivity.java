package vn.edu.diaryapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Le Son on 06/01/2016.
 */
public class FirstLoginActivity extends Activity {
    private String namePre = "firstuse", pass;
    private Button btReg;
    private EditText etTypePass1, etTypePass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_firstlogin);
        btReg = (Button) findViewById(R.id.btReg);
        etTypePass1 = (EditText) findViewById(R.id.etTypePass1);
        etTypePass2 = (EditText) findViewById(R.id.etTypePass2);
        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etTypePass1.getText().toString().equals(etTypePass2.getText().toString())){
                    pass = etTypePass1.getText().toString();
                    saveSharedPres();
                    Intent it = new Intent(getApplication(), LoginActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    Toast.makeText(getApplication(), "Mật mã không trùng khớp", Toast.LENGTH_LONG).show();
                    etTypePass1.getText().clear();
                    etTypePass2.getText().clear();
                    etTypePass1.setFocusableInTouchMode(true);
                    etTypePass1.requestFocus();
                }
            }
        });
    }
    public void saveSharedPres() {
        SharedPreferences pre = getSharedPreferences(namePre, MODE_PRIVATE);
        SharedPreferences.Editor edit = pre.edit();
        edit.putInt("state", 1);
        edit.putString("pass", pass);
        edit.commit();
    }
}
