package vn.edu.diaryapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Le Son on 06/01/2016.
 */
public class LoginActivity extends Activity {
    private Boolean checked, checkFirst;
    private String namePre = "firstuse", pass;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        callSharedPre();

        final CheckBox cbRememberLogin = (CheckBox) findViewById(R.id.cbRememberLogin);
        Button btLogin = (Button) findViewById(R.id.btLogin);
        final EditText etLogin = (EditText) findViewById(R.id.etLogin);
        if(checkFirst == true){
            etLogin.setText(pass);
            cbRememberLogin.setChecked(true);
        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pass.equals(etLogin.getText().toString())){

                    if(cbRememberLogin.isChecked()){
                        checked = true;
                        saveSharedPres();
                    }
                    else{
                        checked = false;
                        saveSharedPres();
                    }
                    Intent it = new Intent(getApplication(), MainActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Toast.makeText(getApplication(), "Mật mã không đúng", Toast.LENGTH_LONG).show();
                    etLogin.getText().clear();
                    etLogin.setFocusableInTouchMode(true);
                    etLogin.requestFocus();
                }
            }
        });
    }
    public void callSharedPre() {
        SharedPreferences pre = getSharedPreferences(namePre, MODE_PRIVATE);
        pass = pre.getString("pass", "");
        checkFirst = pre.getBoolean("checked", false);
    }
    public void saveSharedPres() {
        SharedPreferences pre = getSharedPreferences(namePre, MODE_PRIVATE);
        SharedPreferences.Editor edit = pre.edit();
        edit.putBoolean("checked", checked);
        edit.commit();
    }
}
