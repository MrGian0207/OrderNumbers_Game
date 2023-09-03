package com.example.number_game;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Spinner_Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<Languages> list;

    public Spinner_Adapter(Context context, ArrayList<Languages> list) {
        this.context = context;
        this.list = list;
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        view = inflater.inflate(R.layout.spinner_items,viewGroup,false);

        TextView txtName = view.findViewById(R.id.name_language);
        txtName.setText(list.get(i).getName());
        return view;
    }
}
