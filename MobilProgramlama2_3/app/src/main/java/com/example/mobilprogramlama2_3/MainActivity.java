package com.example.mobilprogramlama2_3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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


        ListView list1 = (ListView) findViewById(R.id.listView);
        TextView text = (TextView) findViewById(R.id.textView);

        String[] sehirler = {"İstanbul", "Ankara", "İzmir", "Kocaeli"};
        ArrayAdapter<String> verilistesi = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,sehirler);

        list1.setAdapter(verilistesi);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i,long l) {
            Toast.makeText(MainActivity.this, verilistesi.getItem(i),
                    Toast.LENGTH_LONG).show();
                text.setText(verilistesi.getItem(i));
            }
        });
    }
}