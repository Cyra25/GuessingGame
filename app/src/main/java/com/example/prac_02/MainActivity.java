package com.example.prac_02;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private TextView statusText;
    private EditText guessNumber;
    private View view;
    static int secretValue;
    private Random random;

//    private TextView testing;
//    private TextView testing2;
//    private TextView testing3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("value", MODE_PRIVATE);
        statusText = (TextView) findViewById(R.id.statusText);

//        testing = (TextView) findViewById(R.id.testPref);
//        testing2 = (TextView) findViewById(R.id.testPref2);
//        testing3 = (TextView) findViewById(R.id.testPref3);


        guessNumber = (EditText) findViewById(R.id.guessNumber);
        guessNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                statusText.setText(getString(R.string.hello));
            }

            @Override
            public void onTextChanged(CharSequence string, int start, int before, int count) {
                try {
                    int value = Integer.parseInt(string.toString());
                    if (value == secretValue) {
                        statusText.setText(getString(R.string.win));
                    } else if (value < secretValue) {
                        statusText.setText(getString(R.string.small));
                    } else {
                        statusText.setText(getString(R.string.big));
                    }
                }catch (Exception e){
                    //
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void handler(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart(){
        super.onStart();

        int maxValue = preferences.getInt("max value", 0 );
        int minValue = preferences.getInt("min value", 0 );
        Random random = new Random();
        secretValue = random.nextInt((20 - 10) + 1) + minValue;
//        testing.setText("" + maxValue);
//        testing2.setText("" + minValue);
//        testing3.setText("" + secretValue);


    }

}

