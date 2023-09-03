package com.example.number_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class SuccessfulChallenge extends AppCompatActivity {
    TextView records;
    TextView  startAgain;
    List<TimeRecords> Records_List = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_challenge);

        Intent intent_Records = getIntent();
        Records_List = (ArrayList<TimeRecords>) intent_Records.getSerializableExtra("Record_List");
        records = findViewById(R.id.records);
        for (TimeRecords time: Records_List) {
            records.setText(String.format("%02d:%02d",time.getMinutes(),time.getSeconds()));
        }

        startAgain = findViewById(R.id.StartAgain_Success);
        Intent intent = new Intent(SuccessfulChallenge.this,MainActivity.class);
        startAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });


    }
}