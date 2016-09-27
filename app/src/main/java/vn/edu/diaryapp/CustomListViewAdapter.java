package vn.edu.diaryapp;


import android.app.Activity;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomListViewAdapter extends ArrayAdapter {
    private Activity ct;
    private ArrayList<String> title;
    private ArrayList<String> date;

    public CustomListViewAdapter(Activity context, ArrayList<String> tittle, ArrayList<String> date) {
        super(context, R.layout.row_listview, tittle);
        this.ct = context;
        this.title = tittle;
        this.date = date;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layIn = ct.getLayoutInflater();
        View rowView = layIn.inflate(R.layout.row_listview, null, true);
        TextView tvTitleRow = (TextView) rowView.findViewById(R.id.tvTitleRow);
        TextView tvDateDiary = (TextView) rowView.findViewById(R.id.tvDateDiary);
        tvTitleRow.setText(title.get(position));
        tvDateDiary.setText(date.get(position));


        return rowView;
    }
}
