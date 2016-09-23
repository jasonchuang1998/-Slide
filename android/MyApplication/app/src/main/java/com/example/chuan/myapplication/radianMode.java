package com.example.chuan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class radianMode extends AppCompatActivity {

    private Button start;
    private Button end;
    private EditText degee;
    private SeekBar speed;
    private int limit;
    int radin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radian_mode);


        start = (Button)findViewById(R.id.end);
        end = (Button)findViewById(R.id.end);
        degee = (EditText) findViewById(R.id.degee);
        speed =(SeekBar) findViewById(R.id.seekBar3);

        speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                limit = progress ;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radin = Integer.getInteger(degee.getText().toString());
                if(radin > 120) {
                    Toast.makeText(getApplicationContext(), "輸入在0~120之間", Toast.LENGTH_LONG).show();
                } else {
                    //傳到arduino
                }
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}
