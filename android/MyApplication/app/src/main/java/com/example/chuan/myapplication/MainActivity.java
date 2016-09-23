package com.example.chuan.myapplication;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Button normal;
    private Button round;
    private Button radian;

    private ProgressDialog progress;
    private boolean isBtConnect = false;

    String address;

    BluetoothAdapter bluetoothAdapter = null;
    BluetoothDevice device = null;
    BluetoothSocket socket = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent BTIntent = getIntent();
        address = BTIntent.getExtras().getString("device_address");

        new ConnectBT().execute();

        normal = (Button)findViewById(R.id.normal);
        round = (Button)findViewById(R.id.round);
        radian = (Button)findViewById(R.id.radian);

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, normalMode.class);
                startActivity(intent);
                //finish();
            }
        });

        round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, roundMode.class);
                startActivity(intent);
            }
        });

        radian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, radianMode.class);
                startActivity(intent);
            }
        });

    }

    private class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean connectSuccess = true;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(MainActivity.this, "Connecting...", "Please wait!!!");
        }

        @Override
        protected Void doInBackground(Void... devices) {
            try {
                if(socket == null || !isBtConnect) {
                    bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    device = bluetoothAdapter.getRemoteDevice(address);
                    UUID muuid = device.getUuids()[0].getUuid();
                    socket = device.createInsecureRfcommSocketToServiceRecord(muuid);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    socket.connect();
                }
            } catch (IOException e) {
                connectSuccess = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if(!connectSuccess) {
                Toast.makeText(getApplicationContext(), "Connection Failed. Is it a SPP Bluetooth? Try again.", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Connected.", Toast.LENGTH_LONG).show();
                isBtConnect = true;
            }
            progress.dismiss();
        }

    }

}
