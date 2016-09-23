package com.example.chuan.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class bluetoothConnect extends AppCompatActivity {

    ListView bluetoothDeviceList;

    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS = "device_address";
    private ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_connect);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_connect);
        bluetoothDeviceList = (ListView)findViewById(R.id.bluetoothList);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if(myBluetooth != null) {
            if(myBluetooth.isEnabled()) {
                myBluetooth.startDiscovery();
                IntentFilter filter = new IntentFilter();
                filter.addAction(BluetoothDevice.ACTION_FOUND);
                registerReceiver(myReceiver, filter);
                pairedDevicesList();

            } else {
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, 1);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void pairedDevicesList() {
        pairedDevices = myBluetooth.getBondedDevices();

        if(pairedDevices.size() > 0) {
            for(BluetoothDevice bt : pairedDevices) {
                adapter.add(bt.getName() + "\n" + bt.getAddress());
            }
        }
        bluetoothDeviceList.setAdapter(adapter);
        bluetoothDeviceList.setOnItemClickListener(listClickListener);

    }

    private AdapterView.OnItemClickListener listClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView av, View v, int arg2, long ard3) {
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);
            Intent intent = new Intent(bluetoothConnect.this, MainActivity.class);
            intent.putExtra(EXTRA_ADDRESS, address);
            startActivity(intent);
            finish();
        }
    };

    private final BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            adapter = new ArrayAdapter<String>(bluetoothConnect.this, android.R.layout.simple_list_item_1);
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                adapter.add(device.getName() + "\n" + device.getAddress());
            }
            bluetoothDeviceList.setAdapter(adapter);
            bluetoothDeviceList.setOnItemClickListener(listClickListener);
        }
    };

}
