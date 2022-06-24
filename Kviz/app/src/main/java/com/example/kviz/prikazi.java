package com.example.kviz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import db.AppDataBase;
import db.Rec;

public class prikazi extends AppCompatActivity {

    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikazi_reci);

        list = (ListView) findViewById(R.id.listview);

        ArrayList<Rec> arrayList = new ArrayList<>();
        ArrayList<String> arrayListS = new ArrayList<>();
        arrayList = popuniListu();
        //Collections.sort(arrayList,new KomparatorPoImenu());
        for(int i = 0; i < arrayList.size(); i++){
            arrayListS.add(arrayList.get(i).id+": " + arrayList.get(i).nemacki + "-" + arrayList.get(i).srpski);
        }



        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayListS);
        list.setAdapter(arrayAdapter);

    }

    private ArrayList<Rec> popuniListu() {
        ArrayList<Rec> arrayList = new ArrayList<>();
        AppDataBase db  = AppDataBase.getDbInstance(this.getApplicationContext());
        arrayList = (ArrayList<Rec>) db.RecDao().getAllReci();
        return arrayList;
    }
}