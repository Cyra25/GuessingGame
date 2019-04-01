//take the value from second activity to prevent the crash


package com.example.prac_02;

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
    private SharedPreferences maxValue;
    private SharedPreferences minValue;
    private TextView statusText;
    private EditText guessNumber;
    private View view;
    static int secretValue;
    private Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maxValue = getSharedPreferences("max value", MODE_PRIVATE);
        minValue = getSharedPreferences("min value", MODE_PRIVATE);
        statusText = (TextView) findViewById(R.id.statusText);

        Random random = new Random();
        secretValue = random.nextInt((maxValue - minValue) + 1) + maxValue;

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
                    } else if (value > secretValue) {
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


}

