package com.example.kviz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import db.AppDataBase;
import db.Rec;

public class MainActivity extends AppCompatActivity {
    Button dodaj,start,prikazi,check, pomoc;
    Button del;
    int rand;
    EditText nemackaRec;
    TextView srpskaRec, pomocni;
    ArrayList<Integer> listaIndexa;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dodaj = (Button) findViewById(R.id.dodaj);
        start = (Button) findViewById(R.id.dajrec);
        del = (Button) findViewById(R.id.delete);
        prikazi = (Button) findViewById(R.id.prikazi);
        check = (Button) findViewById(R.id.check);
        pomoc = (Button) findViewById(R.id.pomoc);



        nemackaRec = (EditText) findViewById(R.id.nemackaRec);
        srpskaRec = (TextView) findViewById(R.id.srpskaRec);
        pomocni = (TextView) findViewById(R.id.pomocni);


        nemackaRec.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    check.performClick();
                    return true;
                }
                return false;
            }
        });



        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, brisanje.class));
            }
        });

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, dodavanje.class));
            }
        });

        prikazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, prikazi.class));
            }
        });

        listaIndexa = sviIndexi();



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rand = listaIndexa.get((int)(Math.random() * (listaIndexa.size())));
                Rec r = getRec(rand);
                srpskaRec.setText(r.srpski+"");
                pomoc.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                            pomocni.setText(r.nemacki+"");
                            pomocni.setVisibility(View.VISIBLE);
                        }
                        if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                            pomocni.setText("");
                        }
                        return true;
                    }
                });

                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(r.nemacki.equals(nemackaRec.getText().toString())){
                            
                            Toast.makeText(getApplicationContext(),"TACNO", Toast.LENGTH_LONG).show();
                            nemackaRec.setText("");
                            start.performClick();
                        }else{
                            Toast.makeText(getApplicationContext(),"NETACNO, PROBAJ PONOVO", Toast.LENGTH_LONG).show();
                            nemackaRec.setText("");
                        }
                    }
                });
            }
        });

    }

    private Rec getRec(int ran) {
        AppDataBase db = AppDataBase.getDbInstance(this.getApplicationContext());
        Rec rec = db.RecDao().findById(ran);
        return rec;
    }

    private int brReci() {
        AppDataBase db = AppDataBase.getDbInstance(this.getApplicationContext());
        int br = db.RecDao().brReci();
        return br;
    }

    private ArrayList<Integer> sviIndexi(){
        ArrayList<Rec> arrayList = new ArrayList<>();
        ArrayList<Integer> arrayListIndex = new ArrayList<>();
        AppDataBase db  = AppDataBase.getDbInstance(this.getApplicationContext());
        arrayList = (ArrayList<Rec>) db.RecDao().getAllReci();
        for(int i = 0; i < arrayList.size(); i++){
            arrayListIndex.add(arrayList.get(i).id);
        }
        return arrayListIndex;
    }

}