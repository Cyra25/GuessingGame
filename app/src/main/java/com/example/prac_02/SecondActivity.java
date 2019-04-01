package com.example.prac_02;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
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
    private SharedPreferences preferences;
    private View view;
    private TextView progressMax;
    private TextView progressMin;
    private int progressMaxValue, progressMinValue;
    private TextView validOrNot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        validOrNot = (TextView) findViewById(R.id.validOrNot);
        progressMax = (TextView) findViewById(R.id.progressMax);
        progressMin = (TextView) findViewById(R.id.progressMin);

        preferences = getSharedPreferences("value", MODE_PRIVATE);

        maxSeekBar = (SeekBar) findViewById(R.id.maxSeekBar);
        minSeekBar = (SeekBar) findViewById(R.id.minSeekBar);

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("Secondary", "on start called");
        maxSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar maxSeekBar, int progress, boolean fromUser) {
                progressMax.setText("" + progress);
                preferences.edit()
                        .putInt("max value", maxSeekBar.getProgress())
                        .apply();

            }

            @Override
            public void onStartTrackingTouch(SeekBar maxSeekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar maxSeekBar) {

            }
        });


        minSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar minSeekBar, int progress, boolean fromUser) {
                progressMin.setText("" + progress);
                preferences.edit()
                        .putInt("min value", minSeekBar.getProgress())
                        .apply();


            }

            @Override
            public void onStartTrackingTouch(SeekBar minSeekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar minSeekBar) {
            }
        });

        Log.i("Secondary","on create called");
        progressMaxValue = preferences.getInt("max value", 0);
        maxSeekBar.setProgress(progressMaxValue);
        progressMinValue = preferences.getInt("min value", 0);
        minSeekBar.setProgress(progressMinValue);


    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("Secondary","on stop called");

        preferences.edit()
                .putInt("max value", maxSeekBar.getProgress())
                .apply();
        preferences.edit()
                .putInt("min value", minSeekBar.getProgress())
                .apply();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("Secondary","on destroy called");
    }

    public void handlerToMain(View view){
        progressMaxValue = preferences.getInt("max value", 0);
        maxSeekBar.setProgress(progressMaxValue);
        progressMinValue = preferences.getInt("min value", 0);
        minSeekBar.setProgress(progressMinValue);

        if (progressMinValue>=progressMaxValue){
            onStart();
            validOrNot.setText(getString(R.string.validOrNotText));
        }
        else{

            Intent intent = new Intent (this, MainActivity.class);
            startActivity(intent);

        }

    }
}