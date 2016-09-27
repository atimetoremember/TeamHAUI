package vn.edu.diaryapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainActivity extends Activity implements View.OnClickListener {
    private int[] linkDraw = new int[]{R.drawable.background, R.drawable.background2, R.drawable.background3};
    private String namePre = "background";
    private int iDBackground = 1;
    private int sendId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btLogin = (Button) findViewById(R.id.btLogin);
        Button btSetting = (Button) findViewById(R.id.btSetting);
        Button btInfor = (Button) findViewById(R.id.btInfor);
        callSharedPresForIdBackground();
        btLogin.setOnClickListener(this);
        btSetting.setOnClickListener(this);
        btInfor.setOnClickListener(this);


    }


    @Override
    protected void onResume() {
        super.onResume();

        saveSharedPres();

        callSharedPres();


    }


    public void callSharedPres() {
        SharedPreferences pre = getSharedPreferences(namePre, MODE_PRIVATE);
        int drawBackground = pre.getInt("iD", 1);
        sendId = drawBackground;

        RelativeLayout mainActivity = (RelativeLayout) findViewById(R.id.mainActivity);
        mainActivity.setBackgroundResource(linkDraw[drawBackground]);


    }

    public void callSharedPresForIdBackground() {
        SharedPreferences pre = getSharedPreferences(namePre, MODE_PRIVATE);
        int drawBackground = pre.getInt("iD", 1);
        iDBackground = drawBackground;


    }

    public void saveSharedPres() {
        SharedPreferences pre = getSharedPreferences(namePre, MODE_PRIVATE);
        SharedPreferences.Editor edit = pre.edit();
        edit.clear();


        edit.putInt("iD", iDBackground);
        edit.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btLogin:
                Intent itLogin = new Intent(this, ListViewActivity.class);
                startActivity(itLogin);
                break;
            case R.id.btSetting:
                Intent it = new Intent(getApplicationContext(), SettingActivity.class);
                it.putExtra("sendId", sendId);
                startActivityForResult(it, 1);
                break;
            case R.id.btInfor:
                Intent itInfor = new Intent(this, InforActivity.class);
                startActivity(itInfor);


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            iDBackground = data.getIntExtra("iDBackground", 1);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertConfirm = new AlertDialog.Builder(MainActivity.this);
        alertConfirm.setTitle("Thông báo !!!");
        alertConfirm.setMessage("Bạn muốn thoát nhật ký chứ?");
        alertConfirm.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertConfirm.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertConfirm.create().show();
    }
}
