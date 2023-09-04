package com.example.number_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    
    private TextView timerTextView;
    private Button startButton;
    private Button stopButton;
    private Handler handler;
    private Runnable runnable;
    private long startTime = 0L;
    private long updatedTime = 0L;
    private long timeInMilliseconds = 0L;
    private boolean isRunning = false;
    GridView gridView;
    RandomNumberGenerator Random_Number = new RandomNumberGenerator();
    String [] number = new String[Random_Number.numberOfRandomNumbers];

    Random r = new Random();
    int index = 0;
    int number_clickTrue;
    int number_clickFalse;
    boolean isItemClickEnable;

    int seconds;
    int minutes;
    Spinner spinner;
    List<TimeRecords> Records_List = new ArrayList<>();

    ArrayList<Languages> list_languages = new ArrayList<>();
    String [] list_languages_temporary;
    int indexFor_list_languages_temporary = 0;

    Button start_button;
    OrderCells ordercells = new OrderCells();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent.hasExtra("Record_List_Success")) {
            Records_List = (ArrayList<TimeRecords>) intent.getSerializableExtra("Record_List_Success");
            // Sử dụng Records_List để cập nhật giao diện người dùng hoặc thực hiện các thay đổi khác
        }

        list_languages.add(new Languages(" "));
        list_languages.add(new Languages("EN"));
        list_languages.add(new Languages("VI"));

        list_languages_temporary = new String[list_languages.size()];
        for (Languages language: list_languages) {
            list_languages_temporary[indexFor_list_languages_temporary] = language.getName();
            indexFor_list_languages_temporary++;
        }
        spinner = findViewById(R.id.language_spinner);
        Spinner_Adapter language_adapter = new Spinner_Adapter(this, list_languages);
        spinner.setAdapter(language_adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedLang = list_languages_temporary[i];
                if (selectedLang.equals("EN")) {
                   setLocal(MainActivity.this, "en");
                    finish();
                    startActivity(getIntent());
                }else
                   if (selectedLang.equals("VI")) {
                       setLocal(MainActivity.this, "vi");
                       finish();
                       startActivity(getIntent());
                   }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        number_clickTrue = 0;
        number_clickFalse = 0;
        isItemClickEnable = false;
        RandomNumberGenerator random = new RandomNumberGenerator();
        List<Integer> Random_Numbers_List = random.randomNumbers;
        ordercells.Number_List(Random_Numbers_List);
        for(int i = 0; i<number.length; i++) {
            number[i] = String.valueOf(ordercells.Order_Number_List[i]);
        }
        ordercells.Number_List_Sorted(ordercells.Order_Number_List);


        gridView = (GridView)findViewById(R.id.grv_number);
        CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.item_number,number);
        gridView.setAdapter(adapter);



        timerTextView = findViewById(R.id.time_value);
        start_button = findViewById(R.id.start_button);
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                timeInMilliseconds = System.currentTimeMillis() - startTime;
                updatedTime = timeInMilliseconds;
                seconds = (int) (updatedTime / 1000);
                minutes = seconds / 60;
                seconds = seconds % 60;
                timerTextView.setText(String.format("%02d:%02d", minutes, seconds));
                handler.postDelayed(this, 1000); // Lặp lại mỗi 1 giây
            }
        };
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRunning) {
                    startTime = System.currentTimeMillis();
                    handler.postDelayed(runnable, 0);
                    isRunning = true;
                    isItemClickEnable = true;
                }
                if(isItemClickEnable ==  true) {
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            if(number[i].isEmpty()) {return;}
                            ordercells.setOrder_Number_List(Integer.parseInt(number[i]));
                            if (ordercells.isValid == true) {
                                number[i] = "";
                                // Cập nhật dữ liệu mới cho adapter
                                CustomArrayAdapter adapter = (CustomArrayAdapter) gridView.getAdapter();
                                adapter.notifyDataSetChanged();
                                ordercells.isValid = false;
                                number_clickTrue++;
                                if (number_clickTrue == number.length) {
                                    Intent intent = new Intent(MainActivity.this, SuccessfulChallenge.class);
                                    Records_List.add(new TimeRecords(minutes,seconds));
                                    intent.putExtra("Record_List",(Serializable)Records_List);
                                    startActivity(intent);
                                }
                            } else {
                                number_clickFalse++;
                                if (number_clickFalse > 3) {
                                    Intent intent = new Intent(MainActivity.this, FailedChallenge.class);
                                    startActivity(intent);
                                }
                            }
                        }
                    });
                }
            }
        });
    }
    @SuppressWarnings("deprecation")
    public  void setLocal(Activity activity,String langCode){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());
    }
}