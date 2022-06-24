package com.example.kviz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import db.AppDataBase;
import db.Rec;

public class brisanje extends AppCompatActivity {
    EditText id;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubrisanje);


        id = (EditText) findViewById(R.id.idid);
        btn = (Button) findViewById(R.id.obr);
        id.requestFocus();


        id.setImeOptions(EditorInfo.IME_ACTION_DONE);

        id.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    obrisi(Integer.parseInt(id.getText().toString()));
                    Toast.makeText(getApplicationContext(),"OBRISANO", Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obrisi(Integer.parseInt(id.getText().toString()));
                Toast.makeText(getApplicationContext(),"OBRISANO", Toast.LENGTH_LONG).show();

            }
        });


    }

    private void obrisi(int id) {
        AppDataBase db  = AppDataBase.getDbInstance(this.getApplicationContext());

        Rec rec = new Rec();
        rec.id = id;
        db.RecDao().delete(rec);

        finish();

    }
}