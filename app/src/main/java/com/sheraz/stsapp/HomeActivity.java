package com.sheraz.stsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {


    GridView androidGridView;

    String[] gridViewString = {
            "Addidas", "Nike", "Rebook", "Outfitters", "Brakeout", "Couger",
    };


    int[] gridViewImageId = {
            R.drawable.men_hoddie_new, R.drawable.men_hoddie_new, R.drawable.men_hoddie_new, R.drawable.men_hoddie_new, R.drawable.men_hoddie_new, R.drawable.men_hoddie_new
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(HomeActivity.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);


        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Toast.makeText(HomeActivity.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
            }
        });

    }
}