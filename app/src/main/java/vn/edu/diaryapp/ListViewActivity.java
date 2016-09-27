package vn.edu.diaryapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewActivity extends Activity {
    private CustomListViewAdapter customListViewAdapter;
    private ArrayList<Integer> iD;
    private ArrayList<String> note;
    private ArrayList<String> date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview);

        Button btAdd = (Button) findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AddNoteActivity.class);
                startActivity(it);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            Database diaryDB = new Database(this);

            ArrayList<DiaryTable> list = new ArrayList<DiaryTable>();
            list = diaryDB.getAllData();
            iD = new ArrayList<Integer>();
            note = new ArrayList<String>();
            date = new ArrayList<String>();


            for (int i = (list.size() - 1); i >= 0; i--) {

                iD.add(list.get(i).getId());
                note.add(list.get(i).getNote());
                date.add(list.get(i).getDate());


            }


        } catch (Exception e) {
            Toast.makeText(this, "Error " + e.getMessage(), Toast.LENGTH_LONG).show();

        }
        customListViewAdapter = new CustomListViewAdapter(this, note, date);
        ListView lvDiary = (ListView) findViewById(R.id.lvDiary);
        lvDiary.setAdapter(customListViewAdapter);
        lvDiary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Bundle bun = new Bundle();
                bun.putString("date", date.get(position));
                bun.putString("note", note.get(position));

                Intent it = new Intent(getApplicationContext(), ViewNoteActivity.class);
                it.putExtras(bun);
                startActivity(it);


            }
        });
        lvDiary.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ListViewActivity.this);

                dialogDelete.setTitle("Tùy chọn !");
                dialogDelete.setMessage("Tùy chọn nhật kí này?");
                dialogDelete.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle bun = new Bundle();

                        bun.putString("note", note.get(position));
                        bun.putInt("id", iD.get(position));
                        Intent it = new Intent(getApplicationContext(), EditNoteActivity.class);
                        it.putExtras(bun);
                        startActivity(it);

                        dialog.cancel();
                    }
                });
                dialogDelete.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Database diaryDB = new Database(getApplicationContext());
                        diaryDB.delete(iD.get(position));
                        iD.remove(position);
                        note.remove(position);
                        date.remove(position);
                        customListViewAdapter.notifyDataSetChanged();

                        dialog.cancel();
                    }
                });
                dialogDelete.create().show();
                return true;
            }
        });


    }
}
