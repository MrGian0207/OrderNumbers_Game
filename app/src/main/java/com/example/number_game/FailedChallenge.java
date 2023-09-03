package com.example.number_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FailedChallenge extends AppCompatActivity {
    TextView startAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failed_challenge);
        startAgain = findViewById(R.id.StartAgain_Failed);
        Intent intent = new Intent(FailedChallenge.this,MainActivity.class);

        startAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}