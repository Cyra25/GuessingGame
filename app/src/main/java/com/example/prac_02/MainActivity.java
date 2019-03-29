package com.example.prac_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView statusText;
    private EditText guessNumber;
    private final static int secretValue = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = (TextView)findViewById(R.id.statusText);

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

}

