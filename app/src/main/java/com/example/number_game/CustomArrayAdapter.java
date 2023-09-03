package com.example.number_game;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.example.number_game.R;

public class CustomArrayAdapter extends ArrayAdapter<String> {
    private Context context;
    private int resource;
    private String[] numbers;

    public CustomArrayAdapter(Context context, int resource, String[] numbers) {
        super(context, resource, numbers);
        this.context = context;
        this.resource = resource;
        this.numbers = numbers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        TextView textViewNumber = convertView.findViewById(R.id.text_view_number);
        FrameLayout frameLayout = convertView.findViewById(R.id.frame_layout);

        // Lấy màu từ mảng colorcells dựa vào vị trí position
        String[] colorcells ={
                "#8b008b",
                "#ffa500",
                "#2d2d2d",
                "#ff0000",
                "#00ff00",
                "#0000ff",
                "#ffff00",
                "#00ffff",
                "#ff00ff"
        };
        int colorIndex = position % colorcells.length;
        String backgroundColor = colorcells[colorIndex];

        // Đặt màu nền cho frameLayout
        frameLayout.setBackgroundColor(Color.parseColor(backgroundColor));

        // Đặt số vào TextView
        textViewNumber.setText(numbers[position]);

        return convertView;
    }
}
