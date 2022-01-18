package com.example.project_camera_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAdapter extends BaseAdapter {

    Context context;
    ArrayList<DataModel> list;

    public MainAdapter(Context context, ArrayList<DataModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item,parent,false);

        setUpData(view,position);
        return view;
    }

    private void setUpData(View view, int position) {
        RelativeLayout relative_layout_id = view.findViewById(R.id.relative_layout_id);
        TextView textView = view.findViewById(R.id.txtName);
        textView.setText(list.get(position).getCode());
    }
}
