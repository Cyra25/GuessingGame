package com.example.prac_02;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private SeekBar maxSeekBar;
    private SeekBar minSeekBar;
    private SharedPreferences maxValue;
    private SharedPreferences minValue;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        maxValue = getSharedPreferences("max value", MODE_PRIVATE);
        minValue = getSharedPreferences("min value", MODE_PRIVATE);
        maxSeekBar = (SeekBar) findViewById(R.id.maxSeekBar);
        maxSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar maxSeekBar, int progress, boolean fromUser) {
                maxValue.edit().putInt("Max value", progress).apply();


            }

            @Override
            public void onStartTrackingTouch(SeekBar maxSeekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar maxSeekBar) {

            }
        });

        minSeekBar = (SeekBar) findViewById(R.id.minSeekBar);
        minSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar minSeekBar, int progress, boolean fromUser) {
                minValue.edit().putInt("Min value", progress).apply();


            }

            @Override
            public void onStartTrackingTouch(SeekBar minSeekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar minSeekBar) {

            }
        });

        Log.i("Secondary","on create called");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("Secondary", "on start called");
        int progress = maxValue.getInt("max value", 0);
        maxSeekBar.setProgress(progress);
        int progress2 = minValue.getInt("min value", 0);
        minSeekBar.setProgress(progress2);
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("Secondary","on stop called");

        maxValue.edit()
                .putInt("max value", maxSeekBar.getProgress())
                .apply();
        minValue.edit()
                .putInt("min value", minSeekBar.getProgress())
                .apply();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("Secondary","on destroy called");
    }

    public void handlerToMain(View view){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
}