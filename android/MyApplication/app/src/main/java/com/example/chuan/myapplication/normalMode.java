package com.example.chuan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class normalMode extends AppCompatActivity {
    private Button front;
    private Button back;
    private Button right;
    private Button left;
    private Button end;
    private SeekBar speed;
    private int limit;

    private int way;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_mode);

        front = (Button)findViewById(R.id.front);
        back=(Button)findViewById(R.id.back);
        right = (Button)findViewById(R.id.right);
        left= (Button)findViewById(R.id.left);
        end= (Button)findViewById(R.id.end);
        speed = (SeekBar) findViewById(R.id.seekBar);


        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                way = 0;
            }
        });

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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                way = 1;
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                way = 2;
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                way = 3;
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
