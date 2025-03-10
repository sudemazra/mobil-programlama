package com.example.plakaeletirme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView listPlaka, listSehir;
    Button btnKaristir;
    ArrayList<Integer> plakalar;
    ArrayList<String> sehirler;
    HashMap<String, Integer> plakaMap;
    ArrayAdapter<Integer> plakaAdapter;
    ArrayAdapter<String> sehirAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       listPlaka = findViewById(R.id.listPlaka);
        listSehir = findViewById(R.id.listSehir);
        btnKaristir = findViewById(R.id.btnKaristir);

        rastgeleListelerOlustur();

        btnKaristir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rastgeleListelerOlustur();
            }
        });

         listSehir.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String secilenSehir = sehirler.get(position);
                int secilenPlaka = plakalar.get(position);

               Intent intent = new Intent(MainActivity.this, Kontrol.class);
                intent.putExtra("sehir", secilenSehir);
                intent.putExtra("plaka", secilenPlaka);
                startActivity(intent);
            }
        });
    }

    private void rastgeleListelerOlustur() {
        plakalar = new ArrayList<>();
        sehirler = new ArrayList<>();
        plakaMap = new HashMap<>();

       String[] tumSehirler = {
                "Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin", "Aydın", "Balıkesir",
                "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli",
                "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari",
                "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir",
                "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir",
                "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat",
                "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman",
                "Kırıkkale", "Batman", "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye",
                "Düzce"
        };

        for (int i = 0; i < tumSehirler.length; i++) {
            plakaMap.put(tumSehirler[i], i + 1);
        }

        ArrayList<String> karisikSehirler = new ArrayList<>(plakaMap.keySet());
        Collections.shuffle(karisikSehirler);

        for (int i = 0; i < 10; i++) {
            sehirler.add(karisikSehirler.get(i));
            plakalar.add(plakaMap.get(karisikSehirler.get(i)));
        }

        ArrayList<Integer> karisikPlakalar = new ArrayList<>(plakalar);
        Collections.shuffle(karisikPlakalar);

        for (int i = 0; i < plakalar.size(); i++) {
            plakalar.set(i, karisikPlakalar.get(i));
        }

        plakaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, plakalar);
        sehirAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sehirler);
        listPlaka.setAdapter(plakaAdapter);
        listSehir.setAdapter(sehirAdapter);
    }

}