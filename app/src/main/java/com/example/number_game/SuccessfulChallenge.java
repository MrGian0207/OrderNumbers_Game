package com.example.number_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SuccessfulChallenge extends AppCompatActivity {
    TextView records;
    TextView  startAgain;
    List<TimeRecords> Records_List = new ArrayList<>();
    private RecordAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_challenge);

        Intent intent_Records = getIntent();
        Records_List = (ArrayList<TimeRecords>) intent_Records.getSerializableExtra("Record_List");
        TimeRecords Record = Records_List.get(Records_List.size()-1);
        List<TimeRecords> OrderRecords = Records_List;
        if(OrderRecords.size() >= 2) {
            Collections.sort(OrderRecords, new Comparator<TimeRecords>() {
            @Override
            public int compare(TimeRecords record1, TimeRecords record2) {
                // Xác định cách so sánh giữa các đối tượng TimeRecords
                int totalSeconds1 = record1.getMinutes() * 60 + record1.getSeconds();
                int totalSeconds2 = record2.getMinutes() * 60 + record2.getSeconds();
                // So sánh dựa trên thời gian tích lũy (tính bằng giây)
                return totalSeconds1 - totalSeconds2;
            }
        });
            int index = 1;
            for (TimeRecords List_Temp: OrderRecords) {
                if (List_Temp.getMinutes() == Record.getMinutes() && List_Temp.getSeconds() == Record.getSeconds()) {break;}
                else index++;
            }
            TextView ranking = findViewById(R.id.Ranking);
            ranking.setText(String.valueOf(index));
        }
        else {
            TextView ranking = findViewById(R.id.Ranking);
            ranking.setText("1");
        }

        startAgain = findViewById(R.id.StartAgain_Success);
        startAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccessfulChallenge.this,MainActivity.class);
                intent.putExtra("Record_List_Success",(Serializable) Records_List);

                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecordAdapter(OrderRecords);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}