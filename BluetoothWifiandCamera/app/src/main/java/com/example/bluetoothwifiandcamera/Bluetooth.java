package com.example.bluetoothwifiandcamera;

import android.Manifest;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Set;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class   Bluetooth extends AppCompatActivity {

    private static final int REQUEST_BLUETOOTH_PERMISSION = 1;
    Button b1, b2, b3, b4;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bluetooth);

        b1 = (Button) findViewById(R.id.btnAc);
        b2 = (Button) findViewById(R.id.btnKapat);
        b3 = (Button) findViewById(R.id.btnEslesmis);
        b4 = (Button) findViewById(R.id.btnGorunur);

        BA = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView) findViewById(R.id.listView);

        if(BA == null ) {
            Toast.makeText(getApplicationContext(), "Bluetooth cihazda desteklenmiyor", Toast.LENGTH_SHORT).show();
            finish(); // Bluetooth desteklenmiyorsa uygulamayı kapat.
        } else {
            checkAndRequestPermissions();
        }
    }

    private void checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.BLUETOOTH,
                    android.Manifest.permission.BLUETOOTH_ADMIN,
                    android.Manifest.permission.BLUETOOTH_CONNECT
            }, REQUEST_BLUETOOTH_PERMISSION);
        } else {
            initializeBluetooth();
        }
    }

    private void initializeBluetooth() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on(v);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                off(v);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list(v);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeVisible(v);
            }
        });
    }

    public void on(View v) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
            if (!BA.isEnabled()) {
                Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnOn, 0);
                Toast.makeText(getApplicationContext(), "Bluetooth AÇILDI", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Bluetooth Zaten AÇIK", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Bluetooth açma izni verilmedi.", Toast.LENGTH_SHORT).show();
            checkAndRequestPermissions();
        }
    }

    public void off(View v) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
            if (BA.isEnabled()) {
                BA.disable();
                Toast.makeText(getApplicationContext(), "Bluetooth kapatıldı", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Bluetooth kapatma izni verilmedi.", Toast.LENGTH_SHORT).show();
            checkAndRequestPermissions();
        }
    }

    public void list(View v) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
            pairedDevices = BA.getBondedDevices();
            ArrayList<String> list = new ArrayList<>();

            for (BluetoothDevice bt : pairedDevices) {
                list.add(bt.getName() + "\n" + bt.getAddress());
            }

            if (list.size() > 0) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                lv.setAdapter(adapter);
            } else {
                Toast.makeText(getApplicationContext(), "Estestirilen cihaz yok", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Bluetooth listesini görüntülemek için izin verilmedi.", Toast.LENGTH_SHORT).show();
            checkAndRequestPermissions();
        }
    }

    public void makeVisible(View v) {
        if (ContextCompat.checkSelfPermission( this, android.Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED) {
            Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            startActivityForResult(getVisible,  0);
            Toast.makeText(getApplicationContext(),  "Cihaz görünür hale getirildi", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Cihaz görünür yapmak için izin verilmedi.", Toast.LENGTH_SHORT).show();
            checkAndRequestPermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_BLUETOOTH_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeBluetooth();
            } else {
                Toast.makeText(this,  "Bluetooth işlemi için izin verilmedi.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}