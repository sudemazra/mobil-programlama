package com.example.bluetoothwifiandcamera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnWifi = (Button) findViewById(R.id.btnWifi);
        Button btnBluetooth = (Button) findViewById(R.id.btnBluetooth);
        Button btnCamera = (Button) findViewById(R.id.btnCamera);

        btnBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisBluetooth = new Intent(MainActivity.this, Bluetooth.class);
                startActivity(gecisBluetooth);
            }
        });

        btnWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisWifi = new Intent(MainActivity.this, Wifi.class);
                startActivity(gecisWifi);
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisCamera = new Intent(MainActivity.this, Camera.class);
                startActivity(gecisCamera);
            }
        });

        };
}
