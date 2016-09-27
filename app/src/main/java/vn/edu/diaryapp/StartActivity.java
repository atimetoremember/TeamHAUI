package vn.edu.diaryapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RelativeLayout;

/**
 * Created by Le Son on 06/01/2016.
 */
public class StartActivity extends Activity {
    private String namePre = "firstuse";
    private int res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callSharedPres();
        if(res == 0){
            Intent itFirstLogin = new Intent(this, FirstLoginActivity.class);
            startActivity(itFirstLogin);
            finish();
        }
        else{
            Intent itLogin = new Intent(this, LoginActivity.class);
            startActivity(itLogin);
            finish();
        }
    }
    public void callSharedPres() {
        SharedPreferences pre = getSharedPreferences(namePre, MODE_PRIVATE);
        res = pre.getInt("state", 0);
    }

}
